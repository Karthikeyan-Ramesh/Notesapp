package com.notesapp.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		String[] urlarr = request.getRequestURI().replace(":/", "").split("/");
		 long id = Long.parseLong(urlarr[3]);
		 if(id!=0) {
			 NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
			 Category result = dao.readCategory(id);
			 response.getWriter().print(result);
		 }else {
			 response.getWriter().print("Category key should not be zero");
		 }
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date date = new Date();
		Category catObj = new Category.Builder()
				.categoryName(request.getParameter("categoryName"))
				.createdBy("Admin")
				.createdDateTime(date)
				.build();
		
		
		NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
	    
	    long id = dao.createCategory(catObj);
	    
	    if(id !=0) {
	    	response.getWriter().print("Category was created successfully !");
	    }
	    else {
	    	response.getWriter().print("Failed to create Category");
	    }
	    
	}
	

	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] urlarr = request.getRequestURI().replace(":/", "").split("/");
		 long id = Long.parseLong(urlarr[3]);
		 if(id!=0) {
		 Date date = new Date();
			Category catObj = new Category.Builder()
					.categoryName(request.getParameter("categoryName"))
					.id(id)
					.modifiedBy("Admin")
					.modifiedDateTime(date)
					.build();
					
			NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
		    
			Category resObj = dao.updateCategory(catObj);
		    
		    if(resObj !=null) {
		    	response.getWriter().print("Category was updated successfully !");
		    }
		    else {
		    	response.getWriter().print("Failed to update Category");
		    }
		 }else {
			 	response.getWriter().print("Category key should not be zero");
		 }
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] urlarr = request.getRequestURI().replace(":/", "").split("/");
		 long id = Long.parseLong(urlarr[3]);
		 if(id!=0) {
			 NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
			 String result = dao.deleteCategory(id);
			 response.getWriter().print(result);
		 }else {
			 response.getWriter().print("Category key should not be zero");
		 }
	}
	
	private boolean categoryNameCheck(String categoryName) {

		return Pattern.matches("[a-zA-Z]", categoryName);  
	}
}
