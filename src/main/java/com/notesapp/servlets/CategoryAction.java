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
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date date = new Date();
		Category catObj = new Category.Builder()
				.categoryName(request.getParameter("categoryName"))
				.createdBy(request.getParameter("createdBy"))
				.createdDateTime(date)
				.build();
				
		if(Pattern.matches("[a-zA-Z]", catObj.getCategoryName())) {    //to check whether the category only contain alphabets
			
		NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
	    
	    long id = dao.createCategory(catObj);
	    
	    System.out.println(id);
	    if(id !=0) {
	    	response.getWriter().print("Category created successfully !");
	    }
	    else {
	    	response.getWriter().print("Failed to create Category");
	    }
	    
		}else {
			response.getWriter().print("Category should only contains Alphabets ");
		}
	}
	
	@Override
	public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
