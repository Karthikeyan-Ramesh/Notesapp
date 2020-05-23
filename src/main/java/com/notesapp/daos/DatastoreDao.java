package com.notesapp.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.notesapp.pojos.Category;
import com.notesapp.pojos.Notes;
import com.notesapp.pojos.Result;



public class DatastoreDao implements NotesappDao{
	
	  private DatastoreService datastore;
	  
	  private static final String CATEGORY_KIND = "categoryManagement";
	  private static final String NOTES_KIND = "noteManagement";

	  public DatastoreDao() {
	    datastore = DatastoreServiceFactory.getDatastoreService();
	  }

	@Override
	public Category createCategory(Category catObj) {
		
		Entity entityObj = new Entity(CATEGORY_KIND);
		entityObj.setProperty(Category.CATEGORY_NAME, catObj.getCategoryName());
		entityObj.setProperty(Category.CREATED_DATETIME,catObj.getCreatedDateTime());
		entityObj.setProperty(Category.CREATED_BY, catObj.getCreatedBy());
		Key catKey = datastore.put(entityObj);
		return readCategoryById(catKey.getId());
	}
	  
	@Override
	public Category updateCategory(Category catObj) {
		
		Entity entityObj = new Entity(CATEGORY_KIND,catObj.getId());
		entityObj.setProperty(Category.CATEGORY_NAME, catObj.getCategoryName());
		entityObj.setProperty(Category.CREATED_DATETIME,catObj.getCreatedDateTime());
		entityObj.setProperty(Category.CREATED_BY, catObj.getCreatedBy());
		entityObj.setProperty(Category.MODIFIED_DATETIME,catObj.getModifiedDateTime());
		entityObj.setProperty(Category.MODIFIED_BY, catObj.getModifiedBy());
		datastore.put(entityObj);
		return readCategoryById(catObj.getId());
		
	}

	
	@Override
	public boolean deleteCategory(long categoryId) {
		
		Key key = KeyFactory.createKey(CATEGORY_KIND, categoryId);  
		if(key!=null) {
		    datastore.delete(key); 
		    Filter propertyFilter = new FilterPredicate(Notes.CATEGORY_ID, FilterOperator.EQUAL, categoryId);
		  	Query query = new Query(NOTES_KIND).setFilter(propertyFilter);
		    QueryResultIterator<Entity>results = datastore.prepare(query).asQueryResultIterator();
			List<Notes> resultUsers = NotesIteration(results);
			if(resultUsers.size() != 0)
				for(int i=0;i<resultUsers.size();i++) 
					deleteNote(resultUsers.get(0).getId());
		    return true;
		}else {
		    return false;
		}
	}
	
	@Override
	public Category readCategoryById(long categoryId) {
		  Entity entity;
			try {
				entity = datastore.get(KeyFactory.createKey(CATEGORY_KIND,categoryId ));
				return entityToCategory(entity); 
			} catch (EntityNotFoundException e) {
				return null;
			}
	  }
	
	private Category entityToCategory(Entity enObj) {
		
		 return new Category.Builder()   
				 	.id(enObj.getKey().getId())
			        .categoryName((String) enObj.getProperty(Category.CATEGORY_NAME))
			        .createdDateTime((Date) enObj.getProperty(Category.CREATED_DATETIME))
			        .createdBy((String) enObj.getProperty(Category.CREATED_BY))
			        .modifiedDateTime((Date) enObj.getProperty(Category.MODIFIED_DATETIME))
			        .modifiedBy((String) enObj.getProperty(Category.MODIFIED_BY))
			        .build();
	}
	
	 public Result<Category> categoryList() {
		  	  Query query = new Query(CATEGORY_KIND).addSort(Category.CATEGORY_NAME, SortDirection.ASCENDING);
			  PreparedQuery preparedQuery = datastore.prepare(query);
			  QueryResultIterator<Entity>results = preparedQuery.asQueryResultIterator();
			  List<Category> resultUsers = categoryIteration(results);
			  return new Result<>(resultUsers);
			  
	  }
	  
	  public List<Category> categoryIteration(Iterator<Entity> results) {
		  
		    List<Category> resultUsers = new ArrayList<>();
		    while(results.hasNext())  
		      resultUsers.add(entityToCategory(results.next()));      
		    return resultUsers;
      }
	  
	  public Result<Notes> categoryBasedNotesList(long categoryId) {
		  Filter propertyFilter = new FilterPredicate(Notes.CATEGORY_ID, FilterOperator.EQUAL, categoryId);
	  	  Query query = new Query(NOTES_KIND).setFilter(propertyFilter).addSort(Notes.CREATED_DATETIME, SortDirection.ASCENDING);
		  PreparedQuery preparedQuery = datastore.prepare(query);
		  QueryResultIterator<Entity>results = preparedQuery.asQueryResultIterator();
		  List<Notes> resultUsers = NotesIteration(results);
		  return new Result<>(resultUsers);
		  
     }
	  
	  public Result<Notes> NotesList() {
	  	  Query query = new Query(NOTES_KIND).addSort(Notes.CREATED_DATETIME, SortDirection.ASCENDING);
		  PreparedQuery preparedQuery = datastore.prepare(query);
		  QueryResultIterator<Entity>results = preparedQuery.asQueryResultIterator();
		  List<Notes> resultUsers = NotesIteration(results);
		  return new Result<>(resultUsers);	  
     }
	  
	  
	  public List<Notes> NotesIteration(Iterator<Entity> results) {
		  
		    List<Notes> resultUsers = new ArrayList<>();
		    while(results.hasNext())  
		      resultUsers.add(entityToNotes(results.next()));      
		    return resultUsers;
    }

	@Override
	public Notes createNote(Notes noteObj) {

		Entity entityObj = new Entity(NOTES_KIND);
		entityObj.setProperty(Notes.Note_NAME,noteObj.getNoteName());
		entityObj.setProperty(Notes.Note_Description,noteObj.getNoteDescription());
		entityObj.setProperty(Notes.CATEGORY_ID,noteObj.getCategoryId().getId());
		entityObj.setProperty(Notes.CREATED_BY,noteObj.getCreatedBy());
		entityObj.setProperty(Notes.CREATED_DATETIME,noteObj.getCreatedDateTime());
		Key noteKey = datastore.put(entityObj);
		return readNote(noteKey.getId());
	}
	
	@Override
	public Notes updateNote(Notes noteObj) {
		
		Entity entityObj = new Entity(NOTES_KIND,noteObj.getId());
		entityObj.setProperty(Notes.Note_NAME,noteObj.getNoteName());
		entityObj.setProperty(Notes.Note_Description,noteObj.getNoteDescription());
		entityObj.setProperty(Notes.CATEGORY_ID,noteObj.getCategoryId().getId());
		entityObj.setProperty(Notes.MODIFIED_BY, noteObj.getModifiedBy());
		entityObj.setProperty(Notes.MODIFIED_DATETIME,noteObj.getModifiedDateTime());
		datastore.put(entityObj);
		return readNote(noteObj.getId());
	}
	
	@Override
	public boolean deleteNote(long noteId) {
		
		Key key = KeyFactory.createKey(NOTES_KIND, noteId);  
		if(key!=null) {
		    datastore.delete(key);   
		    return true;
		}else {
		    return false;
		}
	}
	
	public Notes readNote(long noteId) {
		  Entity entity;
			try {
				entity = datastore.get(KeyFactory.createKey(NOTES_KIND,noteId ));
				return entityToNotes(entity); 
			} catch (EntityNotFoundException e) {
				return null;
			}
	  }
	
	private Notes entityToNotes(Entity enObj) {

		Category catObj = new Category.Builder()
				.id((Long) enObj.getProperty(Notes.CATEGORY_ID))
				.build();
		return new Notes.Builder()    
				.id(enObj.getKey().getId())
		        .noteName((String) enObj.getProperty(Notes.Note_NAME))
		        .noteDescription((String) enObj.getProperty(Notes.Note_Description))
		        .categoryId(catObj)
		        .createdDateTime((Date) enObj.getProperty(Notes.CREATED_DATETIME))
		        .createdBy((String) enObj.getProperty(Notes.CREATED_BY))
		        .modifiedDateTime((Date) enObj.getProperty(Notes.MODIFIED_DATETIME))
		        .modifiedBy((String) enObj.getProperty(Notes.MODIFIED_BY))
		        .build();
	}
	
}
