package junitTestCases;

import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.StringReader;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
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
		 JSONObject jb = new JSONObject();
			try {
				jb.put("noteName", "Sports");
				jb.put("noteDescription", "Sports was Interesting");
				jb.put("categoryId", 3343);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		 when(request.getReader()).thenReturn(new BufferedReader(new StringReader(jb.toString())));
		 noteObj.doPost(request, response);
		 Assert.assertEquals("application/json", response.getContentType());
	 }
	  
	 @Test 
	 public void testToUpdateNote() throws Exception {
		 url();
		 JSONObject jb = new JSONObject();
			try {
				jb.put("noteName", "Sports");
				jb.put("noteDescription", "Sports was very Interesting");
				jb.put("categoryId", 3343);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		 when(request.getReader()).thenReturn(new BufferedReader(new StringReader(jb.toString())));
		 noteObj.doPut(request, response);
		 Assert.assertEquals("application/json", response.getContentType());
     }
	 
	 @Test
	 public void testToReadNote() throws Exception {
			url();
			noteObj.doGet(request, response);
			//Assert.assertEquals("application/json", response.getContentType());
		}
	 
	 
	 @Test
	 public void testToDeleteNote() throws Exception {
		url();
		noteObj.doDelete(request, response);
		Assert.assertEquals("application/json", response.getContentType());
	}

}


