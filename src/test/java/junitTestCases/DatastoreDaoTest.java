package junitTestCases;


import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.notesapp.daos.DatastoreDao;
import com.notesapp.pojos.Category;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class DatastoreDaoTest {

	
	 private static final LocalServiceTestHelper helper = new
	 LocalServiceTestHelper( new LocalDatastoreServiceTestConfig());
	
	 private DatastoreDao dao;
	 

	
	 @Before
	 public void setup(){ 
		 helper.setUp(); 
		 dao = new DatastoreDao();
	 }

	 @After
	 public void done (){
		 helper.tearDown(); 
	 }


	@Test 
	public void testDatastore()throws ServletException,IOException {
	
		Category catObj = new Category.Builder()
				.categoryName("Sports")
				.createdBy("Admin")
				.build();
		long id = dao.createCategory(catObj);
		assertNotEquals(0, id);
	 }
	 
}
