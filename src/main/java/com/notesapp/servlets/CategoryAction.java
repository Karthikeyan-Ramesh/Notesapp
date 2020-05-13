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

import com.notesapp.daos.DatastoreDao;
import com.notesapp.daos.NotesappDao;
import com.notesapp.pojos.Category;



@SuppressWarnings("serial")
public class CategoryAction extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
	   NotesappDao dao = new DatastoreDao();
	    this.getServletContext().setAttribute("dao", dao);
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
		String[] urlarr = request.getRequestURI().replace(":/", "").split("/");
		 long id = Long.parseLong(urlarr[3]);
		 if(id!=0) {
			 NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
			 Category resultObj = dao.readCategoryById(id);
			 JSONObject result = categoryResponse(resultObj);
			 response.setContentType("application/json");
			 response.getWriter().println(result);
			 
		 }else {
			 response.getWriter().print("Category key should not be zero");
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
						.categoryName(jsonObject.getString("categoryName"))
						.createdBy("Admin")
						.createdDateTime(date)
						.build();
				
			NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao"); 
			Category resultObj = dao.createCategory(catObj);
			JSONObject result = categoryResponse(resultObj);
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
					.categoryName(jsonObject.getString("categoryName"))
					.id(id)
					.modifiedBy("Admin")
					.modifiedDateTime(date)
					.build();
					
			 NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
			 Category resultObj = dao.updateCategory(catObj);
			 JSONObject result = categoryResponse(resultObj);
			 response.setContentType("application/json");
			 response.getWriter().println(result);
		   
		 }catch (Exception e) { }
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String[] urlarr = request.getRequestURI().replace(":/", "").split("/");
			long id = Long.parseLong(urlarr[3]);
			if(id!=0) {
				NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
				boolean isDeleted = dao.deleteCategory(id);
		    	JSONObject result = new JSONObject();
		    	result.put("Success",isDeleted );
				response.setContentType("application/json");
				response.getWriter().println(result);
			 }else {
				 response.getWriter().print("Category key should not be zero");
			 }
			
		}catch(Exception e) {}
	}
	
	private JSONObject categoryResponse(Category resultObj) {

		JSONObject result = new JSONObject();
    	try {
			result.put("id", resultObj.getId());
			result.put("categoryName", resultObj.getCategoryName());
	    	result.put("createdBy", resultObj.getCreatedBy());
	    	result.put("CreatedDatetime", resultObj.getCreatedDateTime());
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return result; 
	}
}
