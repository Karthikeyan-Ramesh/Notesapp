package com.notesapp.daos;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.notesapp.pojos.Category;



public class DatastoreDao implements NotesappDao{
	
	  private DatastoreService datastore;
	  
	  private static final String CATEGORY_KIND = "categoryManagement";
	  private static final String NOTES_KIND = "noteManagement";

	  public DatastoreDao() {
	    datastore = DatastoreServiceFactory.getDatastoreService();
	  }

	@Override
	public long createCategory(Category catObj) {
		
		Entity entityObj = new Entity(CATEGORY_KIND);
		entityObj.setProperty(Category.CATEGORY_NAME, catObj.getCategoryName());
		entityObj.setProperty(Category.CREATED_BY, catObj.getCreatedBy());
		Key catKey = datastore.put(entityObj);
		return catKey.getId();
	}
	  
	  
 
}
