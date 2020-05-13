package junitTestCases;


import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.notesapp.daos.DatastoreDao;
import com.notesapp.pojos.Category;
import com.notesapp.pojos.Notes;

import javax.servlet.ServletException;

import org.junit.After;
import org.junit.Assert;
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
	public void testDatastoreToCreateCategory()throws ServletException,IOException {
	
		Category catObj = new Category.Builder()
				.categoryName("Sports")
				.createdBy("Admin")
				.build();
		Category resObj = dao.createCategory(catObj);
		assertNotEquals(null, resObj);
		Assert.assertEquals("Sports", resObj.getCategoryName());
	 }
	
	@Test 
	public void testDatastoreToUpdateCategory()throws ServletException,IOException {
	
		Category catObj = new Category.Builder()
				.categoryName("works")
				.id(23234356)
				.modifiedBy("Admin")
				.build();
		Category resObj = dao.updateCategory(catObj);
		assertNotEquals(null, resObj);
		Assert.assertEquals("works", resObj.getCategoryName());
	 }
	
	@Test 
	public void testDatastoreToDeleteCategory()throws ServletException,IOException {
	
		boolean isDeleted = dao.deleteCategory(1);
		Assert.assertEquals(true, isDeleted);
	 }
	
	@Test 
	public void testDatastoreToReadCategory()throws ServletException,IOException {
	
		Category resultObj = dao.readCategoryById(1);
		Assert.assertEquals(null, resultObj);
	 }
	 
	@Test 
	public void testDatastoreToCreateNotes()throws ServletException,IOException {
	
		Category catObj = new Category.Builder()
				.id(1)
				.build();
		Notes noteObj = new Notes.Builder()
				.noteName("Morning")
				.noteDescription("excersice")
				.categoryId(catObj)
				.createdBy("Admin")
				.build();
		Notes resObj = dao.createNote(noteObj);
		assertNotEquals(null, resObj);
		Assert.assertEquals("excersice", resObj.getNoteDescription());
	 }
	
	@Test 
	public void testDatastoreToUpdateNotes()throws ServletException,IOException {
	
		
		Category catObj = new Category.Builder()
				.id(1)
				.build();
		Notes noteObj = new Notes.Builder()
				.noteName("Evening")
				.noteDescription("excersice")
				.categoryId(catObj)
				.id(436346)
				.createdBy("Admin")
				.build();
		Notes resultObj = dao.updateNote(noteObj);
		assertNotEquals(null, resultObj);
		Assert.assertEquals("Evening", resultObj.getNoteName());
	 }
	
	@Test 
	public void testDatastoreToDeleteNotes()throws ServletException,IOException {
	
		boolean isDelete = dao.deleteNote(1);
		Assert.assertEquals(true, isDelete);
	 }
	
	@Test 
	public void testDatastoreToReadNote()throws ServletException,IOException {
	
		Notes resultObj = dao.readNote(1);
		Assert.assertEquals(null, resultObj);
		
	 }
}
