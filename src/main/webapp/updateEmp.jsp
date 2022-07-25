<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Employee</title>
</head>
<body>
<form action="ModifyEmployeeServlet" method="post">

		<center>
			<h2>Update Employee</h2>
			<table>
				<tbody>
					<tr>
						<div>
							<td>ID:</td>
							<td><input type='text' name='id'></td>
						</div>
					</tr>
					<tr>

						<div>
							<!--  key should be in lower case -->
							<td>First Name:</td>
							<td><input type='text' name='fname'></td>
						</div>
					</tr>
					<tr>
						<div>
							<!--  key should be in lower case -->
							<td>Last Name:</td>
							<td><input type='text' name='lname'></td>
						</div>
					</tr>
					<div>
						<tr>
							<td>Email:</td>
							<td><input type='text' name='email'></td>
					</div>
					</tr>
					<tr>
						<div>
							<td>Job_Id:</td>
							<td><input type='text' name='jobid'></td>
						</div>
					</tr>
					<tr>
						<div>
							<td>Hire Date:</td>
							<td><input type='text' name='hdate'></td>
						</div>
					</tr>
					<tr>
						<div>
							<td>Salary:</td>
							<td><input type='text' name='salary'></td>
						</div>
					</tr>
					<tr rowspan=2>
						<div>
							<td><input type='submit' value='Update Employee' name='submit'></td>
						</div>
						<div>
</body>
</body>
</html>