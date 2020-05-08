package junitTestCases;


import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.notesapp.daos.DatastoreDao;
import com.notesapp.pojos.Category;

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
		long id = dao.createCategory(catObj);
		assertNotEquals(0, id);
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
	
		String result = dao.deleteCategory(34534346);
		Assert.assertEquals("Category was deleted successfully !", result);
	 }
	
	@Test 
	public void testDatastoreToReadCategory()throws ServletException,IOException {
	
		Category resultObj = dao.readCategory(14534);
		System.out.println(resultObj);
	 }
	 
}
