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

import com.notesapp.daos.DatastoreDao;
import com.notesapp.daos.NotesappDao;
import com.notesapp.pojos.Category;
import com.notesapp.pojos.Result;



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
		 long id = Long.parseLong(urlarr[2]);
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
			if(resultObj.getCategoryName().equals(jsonObject.getString("categoryName"))) {
				Result<Category>results = dao.categoryList();
				JSONObject result = categoryListResponse(results.result);
				response.setContentType("application/json");
				response.getWriter().println(result);	
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
			 Category byId = dao.readCategoryById(id);
			 
			 byId = new Category.Builder()
					 .categoryName(jsonObject.getString("categoryName"))
					 .id(id)
					 .createdBy(byId.getCreatedBy())
					 .createdDateTime(byId.getCreatedDateTime())
					 .modifiedBy("Admin")
					 .modifiedDateTime(date)
					 .build();
			 
			 Category resultObj = dao.updateCategory(byId);
			 if(resultObj.getCategoryName().equals(jsonObject.getString("categoryName"))) {
					Result<Category>results = dao.categoryList();
					JSONObject result = categoryListResponse(results.result);
					response.setContentType("application/json");
					response.getWriter().println(result);	
			}
		   
		 }catch (Exception e) { }
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String[] urlarr = request.getRequestURI().replace(":/", "").split("/");
			long id = Long.parseLong(urlarr[2]);
			if(id!=0) {
				NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
				boolean isDeleted = dao.deleteCategory(id);
				if(isDeleted) {
				Result<Category>results = dao.categoryList();
				JSONObject result = categoryListResponse(results.result);
				response.setContentType("application/json");
				response.getWriter().println(result);	
				}
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
	    	result.put("createdDatetime", resultObj.getCreatedDateTime());
	    	result.put("modifiedBy", resultObj.getModifiedBy());
	    	result.put("modifiedDatetime", resultObj.getModifiedDateTime());
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return result; 
	}
	
	private JSONObject categoryListResponse(List<Category> list) {

		JSONObject result = new JSONObject();
    	try {
    		for(int i=0;i<list.size();i++) {
    	    JSONObject res = new JSONObject();
    	    res.put("id", list.get(i).getId());
    	    res.put("categoryName", list.get(i).getCategoryName());
    	    res.put("createdBy", list.get(i).getCreatedBy());
    	    res.put("createdDatetime", list.get(i).getCreatedDateTime());
    	    res.put("modifiedBy", list.get(i).getModifiedBy());
    	    res.put("modifiedDatetime", list.get(i).getModifiedDateTime());
    	    result.put(i+"", res);
    		}
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return result; 
	}
}
