package com.notesapp.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.notesapp.daos.NotesappDao;
import com.notesapp.pojos.Category;
import com.notesapp.pojos.Notes;

@SuppressWarnings("serial")
public class NotesAction extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			 String[] urlarr = request.getRequestURI().replace(":/", "").split("/");
			 long id = Long.parseLong(urlarr[3]);
			 NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
			 Notes resObj = dao.readNote(id);
			 JSONObject result = noteResponse(resObj);
			 response.setContentType("application/json");
			 response.getWriter().print(result);
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
			JSONObject result = noteResponse(resObj);
			response.setContentType("application/json");
			response.getWriter().println(result);
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
			long id = Long.parseLong(urlarr[3]);
			 
			Date date = new Date();
			JSONObject jsonObject = new JSONObject(jb.toString());
			Category catObj = new Category.Builder()
							.id(Long.parseLong(jsonObject.getString("categoryId")))
							.build();
			Notes noteObj = new Notes.Builder()
							.id(id)
							.noteName(jsonObject.getString("noteName"))
							.noteDescription(jsonObject.getString("noteDescription"))
							.categoryId(catObj)
							.modifiedBy("Admin")
							.modifiedDateTime(date)
							.build();
						
				NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
				Notes resObj = dao.updateNote(noteObj);
				JSONObject result = noteResponse(resObj);
				response.setContentType("application/json");
				response.getWriter().println(result);
				
		}catch(Exception e) {}
			    
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String[] urlarr = request.getRequestURI().replace(":/", "").split("/");
			long id = Long.parseLong(urlarr[3]);
			NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
		 	boolean isDeleted = dao.deleteNote(id);
	    	JSONObject result = new JSONObject();
	    	result.put("Success",isDeleted );
			response.setContentType("application/json");
			response.getWriter().println(result);
		}catch(Exception e) {}
	}
	
	private JSONObject noteResponse(Notes resultObj) {

		JSONObject result = new JSONObject();
    	try {
			result.put("id", resultObj.getId());
			result.put("noteName", resultObj.getNoteName());
			result.put("noteDescription", resultObj.getNoteDescription());
			result.put("categoryId", resultObj.getCategoryId().getId());
	    	result.put("createdBy", resultObj.getCreatedBy());
	    	result.put("CreatedDatetime", resultObj.getCreatedDateTime());
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return result; 
	}
}
