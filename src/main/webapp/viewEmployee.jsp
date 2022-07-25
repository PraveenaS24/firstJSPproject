<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  
    import = "java.util.List,com.chainsys.jspproject.pojo.Employees,java.util.ArrayList"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee List</title>
</head>
<body>
<table border="2"> 
<thead>
<th  colspan = "7">Details of All the Employee </th>
<tr>
<th>Emp_Id</th>
<th>First_Name</th>
<th>Last_Name</th>
<th>Email</th>
<th>Hire_Date</th>
<th>Job_Id</th>
<th>Salary</th>

</tr></thead>
<br>
<% 
List<Employees> allEmployees = (ArrayList<Employees>)request.getAttribute("emplist");
for(Employees emp: allEmployees){
 %>
 <tr>
  <td> <%=emp.getEmp_Id()%>  </td>
  <td> <%=emp.getFirst_name()%>  </td>
  <td> <%=emp.getLast_name()%>  </td>
  <td> <%=emp.getEmail()%>  </td>
  <td> <%=emp.getHire_date()%>  </td>
  <td> <%=emp.getJob_id()%>  </td>
  <td> <%=emp.getSalary()%>  </td>
  
  
 </tr>
 <%}%>
 </table>
</body>
</html>