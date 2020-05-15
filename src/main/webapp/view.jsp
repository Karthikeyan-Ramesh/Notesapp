<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Notesapp-FrontEnd/css/style.css">

</head>
<body class="body-contents">
	<div id="form" >
		<input type="text" name="category" id="category" Placeholder="Category Name" onkeypress="return isName(event)" /><br />
		<p id="error" class="red"></p>
		<button id="categorySubmit" onclick="addcategory()" class="button">Save</button>
	</div>
	<div id="category_list">
	
	</div>
	<div id="popup hidden">
	<!-- <h1>Are you sure want to Delete the Category</h1> -->
	</div>
	<script src="Notesapp-FrontEnd/js/src/category.js"></script>
</body>
</html>