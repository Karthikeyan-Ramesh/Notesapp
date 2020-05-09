package com.notesapp.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.notesapp.daos.NotesappDao;
import com.notesapp.pojos.Category;
import com.notesapp.pojos.Notes;

@SuppressWarnings("serial")
public class NotesAction extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] urlarr = request.getRequestURI().replace(":/", "").split("/");
		 long id = Long.parseLong(urlarr[3]);
		 if(id!=0) {
			 NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
			 Notes result = dao.readNote(id);
			 response.getWriter().print(result);
		 }else {
			 response.getWriter().print("Note key should not be zero");
		 }
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date date = new Date();
		Category catObj = new Category.Builder()
				.id(Long.parseLong(request.getParameter("categoryId")))
				.build();
		Notes noteObj = new Notes.Builder()
				.noteName(request.getParameter("noteName"))
				.noteDescription(request.getParameter("noteDescription"))
				.categoryId(catObj)
				.createdDateTime(date)
				.createdBy("Admin")
				.build();
		NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
	    
	    long id = dao.createNote(noteObj);
	    
	    if(id !=0) {
	    	response.getWriter().print("Note was created successfully !");
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
						.id(Long.parseLong(request.getParameter("categoryId")))
						.build();
				Notes noteObj = new Notes.Builder()
						.id(id)
						.noteName(request.getParameter("noteName"))
						.noteDescription(request.getParameter("noteDescription"))
						.categoryId(catObj)
						.createdDateTime(date)
						.createdBy("Admin")
						.build();
					
			NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
		    
			Notes resObj = dao.updateNote(noteObj);
		    
		    if(resObj !=null) {
		    	response.getWriter().print("Note was updated successfully !");
		    }
		    else {
		    	response.getWriter().print("Failed to update Category");
		    }
		 }else {
			 	response.getWriter().print("Note key should not be zero");
		 }
	}

	@Override
	public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] urlarr = request.getRequestURI().replace(":/", "").split("/");
		 long id = Long.parseLong(urlarr[3]);
		 if(id!=0) {
			 NotesappDao dao = (NotesappDao) this.getServletContext().getAttribute("dao");
			 String result = dao.deleteNote(id);
			 response.getWriter().print(result);
		 }else {
			 response.getWriter().print("Note key should not be zero");
		 }
	}
}
