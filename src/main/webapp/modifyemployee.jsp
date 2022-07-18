<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modify Employee</title>
</head>
<body>
	<form action="Employee" method="post">
		<!-- -need to mention method=post -->
		<center>
			<h2>Add or Update Employee</h2>
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
							<td><input type='submit' value='Add' name='submit'></td>
						</div>
						<div>
							<td><input type='submit' value='update'
								name='submit'></td>
						</div>
					</tr>
				</tbody>
			</table>
			<h2>Delete Employee</h2>
			<div>
				ID: <input type='text' name='emp_id'>
			</div>
			<div>
				<input type='submit' value='Delete' name='submit'>
			</div>
		</center>
	</form>
</body>
</html>