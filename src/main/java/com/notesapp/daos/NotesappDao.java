package com.notesapp.daos;

import com.notesapp.pojos.Category;
import com.notesapp.pojos.Notes;

public interface NotesappDao {

	Category createCategory(Category catObj);
	
	Category updateCategory(Category catObj);
	
	boolean deleteCategory(long categoryId);

	Category readCategoryById(long categoryId);
	
	
	Notes createNote(Notes noteObj);
	
	Notes updateNote(Notes noteObj);
	
	boolean deleteNote(long noteId);
	
	Notes readNote(long noteId);
}
