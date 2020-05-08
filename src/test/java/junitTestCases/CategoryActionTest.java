package junitTestCases;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.notesapp.daos.DatastoreDao;
import com.notesapp.daos.NotesappDao;
import com.notesapp.servlets.CategoryAction;

public class CategoryActionTest {

	private static final String URL = "http://local/category/";
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
	
	@ParameterizedTest
	@ValueSource(longs = {524246,4524,45,4,345,34,534,534,5})
	public void id() {
		
	}
	public void url() throws Exception {
		when(request.getRequestURI()).thenReturn(URL+"34534534");
	}
	
	@After
	 public void done (){
		 helper.tearDown(); 
	 }
	
	 @Test 
	 public void testToCreateCategory() throws Exception {
		 when(request.getRequestURI()).thenReturn(URL);
		 when(request.getParameter("categoryName")).thenReturn("Sports");
		 catActObj.doPost(request, response);
		 Assert.assertEquals("Category was created successfully !", response.getWriterContent().toString()); 
	 }
	  
	 @Test 
	 public void testToUpdateCategory() throws Exception {
		 url();
		 when(request.getParameter("categoryName")).thenReturn("DailyWalk");
		 catActObj.doPut(request, response);
		 Assert.assertEquals("Category was updated successfully !", response.getWriterContent().toString()); 
     }
	 
	 @Test
	 public void testToReadCategory() throws Exception {
			url();
			catActObj.doDelete(request, response);
			System.out.println(response.getWriterContent().toString());
		}
	 
	 
	 @Test
	 public void testToDeleteCategory() throws Exception {
		url();
		catActObj.doDelete(request, response);
		Assert.assertEquals("Category was deleted successfully !", response.getWriterContent().toString());
	}

}

