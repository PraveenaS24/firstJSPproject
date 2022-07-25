package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.jspproject.commonutil.ExceptionManager;
import com.chainsys.jspproject.commonutil.InvalidInputDataException;
import com.chainsys.jspproject.commonutil.Validator;
import com.chainsys.jspproject.dao.EmployeesDao;
import com.chainsys.jspproject.pojo.Employees;

/**
 * Servlet implementation class ModifyEmployeeServlet
 */
@WebServlet("/ModifyEmployeeServlet")
public class ModifyEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String source = "Update Employee";
		 String message = "<h1> Error while "+source +"</h1>";
		 PrintWriter out = response.getWriter();
		 Employees newEmp = new Employees();
		 String id = request.getParameter("id");
		 int empId;
		 try {
			 Validator.checkStringForParse(id);
			 empId = Integer.parseInt(id);
		 }catch(InvalidInputDataException err) {
			 message += "Error in Employee id :</p>";
			 String errorPage = ExceptionManager.handleException(err, source, message);
			 out.print(errorPage);
			 return;
			 
		 }
		 try {
				Validator.CheckNumberForGreaterThanZero(empId);
				newEmp.setEmp_Id(empId);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee id input: </p>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
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
				out.print(errorPage);
				return;			}

		

			String Lname = request.getParameter("lname");
			try {
				Validator.checkStringOnly(Lname);
				Validator.checklengthOfString(Lname);
				newEmp.setLast_name(Lname);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee last name input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;

			}

		
			String email = request.getParameter("email");
			try {
				Validator.checkMail(email);
				newEmp.setEmail(email);
			} catch (InvalidInputDataException e) {
				message += " Error in Employee email input: <p/>";
				String errorPage = ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
			}

			

			String jobid = request.getParameter("jobid");
			try {
				Validator.checkjob(jobid);
				newEmp.setJob_id(jobid);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee job_id input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;}

		
			String sDate = request.getParameter("hdate");
			Date date = null;
			try {
				date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);

			} catch (ParseException e) {
				message += " Error in Employee hire_date input: <p/>";
				String errorPage = ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
			}
			try {
				Validator.checkDateFormat(sDate);
				newEmp.setHire_date(date);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee hire_date input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;			}

			
			String salary = request.getParameter("salary");
			Float fsalary;
			try {
				Validator.checkStringForParseFloat(salary);
				fsalary = Float.parseFloat(salary);

			} catch (InvalidInputDataException err) {
				message += " Error in Employee salary input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			try {
				Validator.checkSalLimit(fsalary);
				newEmp.setSalary(fsalary);
			} catch (InvalidInputDataException err) {
				message += " Error in Employee salary input: <p/>";
				String errorPage = ExceptionManager.handleException(err, source, message);
				out.print(errorPage);
				return;
			}
			int result = EmployeesDao.updateEmployees(newEmp);
			request.setAttribute("updateemp", result);
			RequestDispatcher rd = request.getRequestDispatcher("updateemployee.jsp");
			rd.forward(request, response);	}

}