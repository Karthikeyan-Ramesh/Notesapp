package com.notesapp.daos;

import com.notesapp.pojos.Category;

public interface NotesappDao {

	long createCategory(Category catObj);
	
	Category updateCategory(Category catObj);
	
	String deleteCategory(long id);

	Category readCategory(long categoryId);
	
}
