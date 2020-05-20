package com.notesapp.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.notesapp.daos.NotesappDao;
import com.notesapp.pojos.Category;
import com.notesapp.pojos.Notes;
import com.notesapp.pojos.Result;

@SuppressWarnings("serial")
public class NotesAction extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			 String[] urlarr = request.getRequestURI().replace(":/", "").split("/");
			 long id = Long.parseLong(urlarr[2]);
			 NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
			 Result<Notes>results = dao.categoryBasedNotesList(id);
			 if(results.result.size()!=0) {
				 JSONObject result = notesListResponse(results.result);
				 response.setContentType("application/json");
				 response.getWriter().println(result);
			 }else {
				 response.setContentType("application/json");
				 JSONObject res = new JSONObject();
				 res.put("listSize", 0);
				 response.getWriter().println(res);
			 }
		}catch(Exception e) {}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			StringBuffer jb = new StringBuffer();
			String line = null;
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		     jb.append(line);
		  
		    Date date = new Date();
			JSONObject jsonObject = new JSONObject(jb.toString());
			Category catObj = new Category.Builder()
					.id(Long.parseLong(jsonObject.getString("categoryId")))
					.build();
			Notes noteObj = new Notes.Builder()
					.noteName(jsonObject.getString("noteName"))
					.noteDescription(jsonObject.getString("noteDescription"))
					.categoryId(catObj)
					.createdDateTime(date)
					.createdBy("Admin")
					.build();
			NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
			Notes resObj = dao.createNote(noteObj);
			if(resObj.getNoteName().equals(jsonObject.getString("noteName"))) {
				Result<Notes>results = dao.categoryBasedNotesList(resObj.getCategoryId().getId());
				 if(results.result.size()!=0) {
					 JSONObject result = notesListResponse(results.result);
					 response.setContentType("application/json");
					 response.getWriter().println(result);
				 }else {
					 response.setContentType("application/json");
					 JSONObject res = new JSONObject();
					 res.put("listSize", 0);
					 response.getWriter().println(res);
				 }
			}
		}catch(Exception e) {}
	    
	}
	
	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			StringBuffer jb = new StringBuffer();
			String line = null;
		    BufferedReader reader = request.getReader();
		    while ((line = reader.readLine()) != null)
		     jb.append(line);
		 
			String[] urlarr = request.getRequestURI().replace(":/", "").split("/");
			long id = Long.parseLong(urlarr[2]);
			 
			Date date = new Date();
			JSONObject jsonObject = new JSONObject(jb.toString());
			
			NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
			Notes noteObj = dao.readNote(id);
			Category catObj = new Category.Builder()
							.id(Long.parseLong(jsonObject.getString("categoryId")))
							.build();
			noteObj = new Notes.Builder()
							.id(id)
							.noteName(jsonObject.getString("noteName"))
							.noteDescription(jsonObject.getString("noteDescription"))
							.categoryId(catObj)
							.createdBy(noteObj.getCreatedBy())
							.createdDateTime(noteObj.getCreatedDateTime())
							.modifiedBy("Admin")
							.modifiedDateTime(date)
							.build();
						
				
			Notes resObj = dao.updateNote(noteObj);
			if(resObj.getNoteName().equals(jsonObject.getString("noteName"))) {
				Result<Notes>results = dao.categoryBasedNotesList(resObj.getCategoryId().getId());
				 if(results.result.size()!=0) {
					 JSONObject result = notesListResponse(results.result);
					 response.setContentType("application/json");
					 response.getWriter().println(result);
				 }else {
					 response.setContentType("application/json");
					 JSONObject res = new JSONObject();
					 res.put("listSize", 0);
					 response.getWriter().println(res);
				 }
			}
				
		}catch(Exception e) {}
			    
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String[] urlarr = request.getRequestURI().replace(":/", "").split("/");
			long id = Long.parseLong(urlarr[2]);
			NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
			Notes noteObj = dao.readNote(id);
		 	boolean isDeleted = dao.deleteNote(id);
		 	Result<Notes>results = dao.categoryBasedNotesList(noteObj.getCategoryId().getId());
			 if(results.result.size()!=0) {
				 JSONObject result = notesListResponse(results.result);
				 response.setContentType("application/json");
				 response.getWriter().println(result);
			 }else {
				 response.setContentType("application/json");
				 JSONObject res = new JSONObject();
				 res.put("listSize", 0);
				 res.put("Success", isDeleted);
				 response.getWriter().println(res);
			 }
		}catch(Exception e) {}
	}
	
	
	private JSONObject notesListResponse(List<Notes> list) {

		JSONObject result = new JSONObject();
    	try {
    		for(int i=0;i<list.size();i++) {
    	    JSONObject res = new JSONObject();
    	    res.put("id", list.get(i).getId());
    	    res.put("noteName", list.get(i).getNoteName());
    	    res.put("noteDescription", list.get(i).getNoteDescription());
    	    result.put(i+"", res);
    		}
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return result; 
	}
}
