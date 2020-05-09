package com.notesapp.daos;

import com.notesapp.pojos.Category;
import com.notesapp.pojos.Notes;

public interface NotesappDao {

	long createCategory(Category catObj);
	
	Category updateCategory(Category catObj);
	
	String deleteCategory(long categoryId);

	Category readCategory(long categoryId);
	
	
	long createNote(Notes noteObj);
	
	Notes updateNote(Notes noteObj);
	
	String deleteNote(long noteId);
	
	Notes readNote(long noteId);
}
