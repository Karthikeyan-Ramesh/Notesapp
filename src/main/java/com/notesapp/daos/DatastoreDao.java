package com.notesapp.daos;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
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
	  
	@Override
	public Category updateCategory(Category catObj) {
		
		Entity entityObj = new Entity(CATEGORY_KIND,catObj.getId());
		entityObj.setProperty(Category.CATEGORY_NAME, catObj.getCategoryName());
		entityObj.setProperty(Category.MODIFIED_BY, catObj.getModifiedBy());
		datastore.put(entityObj);
		
		Entity resultObj;
		try {
			resultObj = datastore.get(KeyFactory.createKey(CATEGORY_KIND, catObj.getId()));
			return entityToCategory(resultObj);
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	private Category entityToCategory(Entity enObj) {
		
		 return new Category.Builder()                       
			        .categoryName((String) enObj.getProperty(Category.CATEGORY_NAME))
			        .id(enObj.getKey().getId())
			        .build();
	}
	
	public String deleteCategory(long id) {
		
		Key key = KeyFactory.createKey(CATEGORY_KIND, id);  
		if(key!=null) {
		    datastore.delete(key);   
		    return "Category was deleted successfully !";
		}else {
		    return "Failed to delete Category";
		}
	}
	  
 
}
