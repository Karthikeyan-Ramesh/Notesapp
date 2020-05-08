package junitTestCases;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.notesapp.daos.DatastoreDao;
import com.notesapp.daos.NotesappDao;
import com.notesapp.servlets.CategoryAction;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class CategoryActionTest {

	private static final String URL = "http://local/";
	private static final LocalServiceTestHelper helper = new
			 LocalServiceTestHelper( new LocalDatastoreServiceTestConfig());
	private CategoryAction catActObj;
	private NotesappDao dao;
	@Mock
	HttpServletRequest request;
	
	@Mock
	MockHttpServletResponse response;
	 
	@Before
	public void setUp() throws Exception {
		  helper.setUp();
		  MockitoAnnotations.initMocks(this);
		  catActObj = new CategoryAction();
		  dao = new DatastoreDao();
		  response = new MockHttpServletResponse();
		  ServletContext servletContext=Mockito.mock(ServletContext.class);
		  when(servletContext.getAttribute("dao")).thenReturn(dao);
		  ServletConfig servletConfig=Mockito.mock(ServletConfig.class);
		  when(servletConfig.getServletContext()).thenReturn(servletContext);
		  catActObj.init(servletConfig);
		  
	 }
	
	@After
	 public void done (){
		 helper.tearDown(); 
	 }
	
	@Test
	public void testToCreateCategory() throws Exception {
		when(request.getRequestURI()).thenReturn(URL+"category");
		when(request.getParameter("categoryName")).thenReturn("Sports");
		when(request.getParameter("createdBy")).thenReturn("Admin");
			
		
		catActObj.doPost(request, response);
		Assert.assertEquals("Category was created successfully !", response.getWriterContent().toString());
	}
	
	@Test
	public void testToUpdateCategory() throws Exception {
		when(request.getRequestURI()).thenReturn(URL+"category/6454565");
		when(request.getParameter("categoryName")).thenReturn("DailyWalk");
		when(request.getParameter("modifiedBy")).thenReturn("Admin");
			
		catActObj.doPut(request, response);
		Assert.assertEquals("Category was updated successfully !", response.getWriterContent().toString());
	}
	
	@Test
	public void testToDeleteCategory() throws Exception {
		when(request.getRequestURI()).thenReturn(URL+"category/6454565");
			
		catActObj.doDelete(request, response);
		Assert.assertEquals("Category was deleted successfully !", response.getWriterContent().toString());
	}

}
