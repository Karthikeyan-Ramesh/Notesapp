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
	<div id="Category">
		<input type="text" name="category" id="category" Placeholder="Category Name"/><br/>
		<p id="error" class="red"></p>
		<button id="categorySubmit" onclick="addcategory()" class="button">Create</button>
	</div>
	<script src="Notesapp-FrontEnd/js/src/category.js"></script>
</body>
</html>