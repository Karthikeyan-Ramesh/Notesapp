<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Notes</title>
<link rel="stylesheet" href="Notesapp-FrontEnd/css/style.css">
</head>
<body class="body-contents" onload = "categoryListOnload()">
<h1 id="logoHead">NotesApp</h1>
<div id="categoryList">
</div>
<%-- <select id="categoryId" name="categoryId" class="categorydropdown shadow" onchange="notesbox()">
	<option value="0">-- Select Category --</option>
<c:forEach items="${categoryList}" var="category">  
     <option value='<c:out value="${category.id}"/>'><c:out value="${category.categoryName}"/></option>
</c:forEach>
</select><br/> --%>
<div id="notesbox">
</div>
<script src="Notesapp-FrontEnd/js/src/notes.js"></script>
</body>
</html>