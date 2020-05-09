package junitTestCases;

import static org.mockito.Mockito.when;

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

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.notesapp.daos.DatastoreDao;
import com.notesapp.daos.NotesappDao;
import com.notesapp.servlets.NotesAction;

public class NotesActionTest {

	private static final String URL = "http://local/note/";
	private static final LocalServiceTestHelper helper = new
			 LocalServiceTestHelper( new LocalDatastoreServiceTestConfig());
	private NotesAction noteObj;
	private NotesappDao dao;
	@Mock
	HttpServletRequest request;
	
	@Mock
	MockHttpServletResponse response;
	 
	@Before
	public void setUp() throws Exception {
		  helper.setUp();
		  MockitoAnnotations.initMocks(this);
		  noteObj = new NotesAction();
		  dao = new DatastoreDao();
		  response = new MockHttpServletResponse();
		  ServletContext servletContext=Mockito.mock(ServletContext.class);
		  when(servletContext.getAttribute("dao")).thenReturn(dao);
		  ServletConfig servletConfig=Mockito.mock(ServletConfig.class);
		  when(servletConfig.getServletContext()).thenReturn(servletContext);
		  noteObj.init(servletConfig);
		  
	 }
	
		/*
		 * @ParameterizedTest
		 * 
		 * @ValueSource(longs = {524246,4524,45,4,345,34,534,534,5})
		 */
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
	 public void testToCreateNote() throws Exception {
		 when(request.getRequestURI()).thenReturn(URL);
		 when(request.getParameter("noteName")).thenReturn("Sports");
		 when(request.getParameter("noteDescription")).thenReturn("Sports was Interesting");
		 when(request.getParameter("categoryId")).thenReturn("3343");
		 noteObj.doPost(request, response);
		 Assert.assertEquals("Note was created successfully !", response.getWriterContent().toString()); 
	 }
	  
	 @Test 
	 public void testToUpdateNote() throws Exception {
		 url();
		 when(request.getParameter("noteName")).thenReturn("Sports");
		 when(request.getParameter("noteDescription")).thenReturn("Sports was Interesting");
		 when(request.getParameter("categoryId")).thenReturn("3343");
		 
		 noteObj.doPut(request, response);
		 Assert.assertEquals("Note was updated successfully !", response.getWriterContent().toString()); 
     }
	 
	 @Test
	 public void testToReadNote() throws Exception {
			url();
			noteObj.doGet(request, response);
		}
	 
	 
	 @Test
	 public void testToDeleteNote() throws Exception {
		url();
		noteObj.doDelete(request, response);
		Assert.assertEquals("Note was deleted successfully !", response.getWriterContent().toString());
	}

}


