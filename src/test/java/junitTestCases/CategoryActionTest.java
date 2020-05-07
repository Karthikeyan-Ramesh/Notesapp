package junitTestCases;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
		  
	 }
	
	@After
	 public void done (){
		 helper.tearDown(); 
	 }
	
	@Test
	public void testToCreateCategory() throws Exception {
		when(request.getRequestURI()).thenReturn(URL+"category/add");
		when(request.getParameter("categoryName")).thenReturn("Spor1ts");
		when(request.getParameter("createdBy")).thenReturn("Admin");
		
		  dao = new DatastoreDao();
		  ServletContext servletContext=Mockito.mock(ServletContext.class);
		  when(servletContext.getAttribute("dao")).thenReturn(dao);
		  ServletConfig servletConfig=Mockito.mock(ServletConfig.class);
		  when(servletConfig.getServletContext()).thenReturn(servletContext);
		  catActObj.init(servletConfig);
			
		response = new MockHttpServletResponse();
		catActObj.doPost(request, response);
		Assert.assertEquals("Category created successfully !", response.getWriterContent().toString());
	}

}
