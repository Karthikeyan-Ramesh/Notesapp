package junitTestCases;

import static org.mockito.Mockito.when;

import java.io.IOException;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.notesapp.daos.DatastoreDao;
import com.notesapp.pojos.Category;
import com.notesapp.servlets.CategoryAction;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
public class DatastoreDaoTest {

	
	 private static final LocalServiceTestHelper helper = new
	 LocalServiceTestHelper( new LocalDatastoreServiceTestConfig());
	
	 private Category catObj;
	 private CategoryAction catAct;
	 private DatastoreDao dao;
	 
	 @Mock
	 HttpServletRequest request;
	 @Mock
	 MockHttpServletResponse response;
	
	 @Before
	 public void setup(){ 
		 MockitoAnnotations.initMocks(this);
		 helper.setUp(); 
		 dao = new DatastoreDao();
	 }

	 @After
	 public void done (){
		 helper.tearDown(); 
	 }


	@Test 
	public void testDatastore()throws ServletException,IOException {
	
		when(request.getRequestURI()).thenReturn("http://local/category/add");
		when(request.getParameter("categoryName")).thenReturn("Sports");
		when(request.getParameter("createdBy")).thenReturn("Admin");
		response = new MockHttpServletResponse();
		catAct.doPost(request, response);
		Assert.assertEquals("Category created successfully !", response.getWriterContent().toString());
	 }
	 
}
