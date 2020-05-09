<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Notesapp</title>
			<style>
				.button {
				  background-color:#2d2e30;
				  border: white;
				  border-style: solid;
				  color: white;
				  padding: 24px 32px;
				  text-align: center;
				  text-decoration: none;
				  display: inline-block;
				  font-size: 22px;
				  margin: 4px 2px;
				  cursor: pointer;
				  border-radius:6px;
				}
				.head{
					text-align:center;
				}
			</style>
 	</head>
 	<body style="background-color:#2d2e30">
		<div class="head">
			<h1 style="margin-top:100px;font-size: 42px;color:white">NotesApp</h1>
			<a href="/addcategory" style="margin-top:100px;" class="button">Create Category</a>
			<a href="/note" style="margin-top:100px;" class="button">Create Reminder Notes</a>
		</div>
 	</body>
</html>