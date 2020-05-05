package daos;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class DatastoreDao implements NotesappDao{
	
	  private DatastoreService datastore;
	  
	  private static final String CATEGORY_KIND = "categoryManagement";
	  private static final String NOTES_KIND = "noteManagement";

	  public DatastoreDao() {
	    datastore = DatastoreServiceFactory.getDatastoreService();
	  }
	  
	  

}
