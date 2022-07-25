<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
     import = "com.chainsys.jspproject.commonutil.ExceptionManager" 
     import  = "com.chainsys.jspproject.commonutil.Validator,com.chainsys.jspproject.commonutil.InvalidInputDataException"
     import = "com.chainsys.jspproject.dao.EmployeesDao,com.chainsys.jspproject.pojo.Employees"
     import = "java.text.ParseException,java.text.SimpleDateFormat,java.util.Date"
     %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Adding Employee</title>
</head>
<body>
<% 
  String source = "Add New Employee";
		 String message = "<h1> Error while "+source +"</h1>";
		 Employees newEmp = new Employees();
		 String id = request.getParameter("id");
		 int empId;
		 try {
			 Validator.checkStringForParse(id);
			 empId = Integer.parseInt(id);
		 }catch(InvalidInputDataException err) {
			 message += "Error in Employee id :</p>";
			 String errorPage = ExceptionManager.handleException(err, source, message);
			 %> <h2> <%=errorPage %></h2><% 
			 
			 return ;
			 
		 }
		 try {
			 Validator.CheckNumberForGreaterThanZero(empId);
			 newEmp.setEmp_Id(empId);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee id input: </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);%>
				<h2> <%=errorPage %></h2><% 
				return;
			}
		 String Fname = request.getParameter("fname");
		 try {
				Validator.checkStringOnly(Fname);
				Validator.checklengthOfString(Fname);
				newEmp.setFirst_name(Fname);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee first name input: </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				%> <h2> <%=errorPage %></h2><% 
				return;			}

		

			String Lname = request.getParameter("lname");
			try {
				Validator.checkStringOnly(Lname);
				Validator.checklengthOfString(Lname);
				newEmp.setLast_name(Lname);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee last name input: </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				%> <h2> <%=errorPage %></h2><% 				
						return;

			}

		
			String email = request.getParameter("email");
			try {
				Validator.checkMail(email);
				newEmp.setEmail(email);
			} catch (InvalidInputDataException e) {
				message += " Error in Employee email input: </p>";
				String errorPage = ExceptionManager.handleException(e, source, message);
				%> <h2> <%=errorPage %></h2><% 		
						return;
			}

			

			String jobid = request.getParameter("jobid");
			try {
				Validator.checkjob(jobid);
				newEmp.setJob_id(jobid);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee job_id input: </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				%> <h2> <%=errorPage %></h2><% 	
						return;}

		
			String sDate = request.getParameter("hdate");
			Date date = null;
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);

			} catch (ParseException e) {
				message += " Error in Employee hire_date input: </p>";
				String errorPage = ExceptionManager.handleException(e, source, message);
				%> <h2> <%=errorPage %></h2><% 		
						return;
			}
			try {
				Validator.checkDateFormat(sDate);
				newEmp.setHire_date(date);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee hire_date input: </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				%> <h2> <%=errorPage %></h2><% 		
						return;			}

			
			String salary = request.getParameter("salary");
			Float fsalary;
			try {
				Validator.checkStringForParseFloat(salary);
				fsalary = Float.parseFloat(salary);

			} catch (InvalidInputDataException err) {
				message += " Error in Employee salary input: </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				%> <h2> <%=errorPage %></h2><% 
						return;
			}
			try {
				Validator.checkSalLimit(fsalary);
				newEmp.setSalary(fsalary);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee salary input: </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				%> <h2> <%=errorPage %></h2><% 	
						return;
			}
			try{
			int result = EmployeesDao.insertEmployees(newEmp); %>
			<h2><%=result %> row inserted</h2><%
				} catch(Exception err){
					message += "Error while inserting record: </p>";
					String errorPage = ExceptionManager.handleException(err, source, message);%>
					<h2><%=errorPage %></h2><% 
					return;
				}
			%>
			
			
</body>
</html>