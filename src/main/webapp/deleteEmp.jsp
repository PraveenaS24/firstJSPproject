<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Employee</title>
</head>
<body>
<form action="RemoveEmployeeServlet" method="post">
<center>
<h2>Delete Employee</h2>
			<div>
				ID: <input type='text' name='emp_id'>
			</div>
			<div>
			<br>
				<input type='submit' value='Delete Employee' name='submit'>
			</div>
			</center>
			</form>
</body>
</html>