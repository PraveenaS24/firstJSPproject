package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

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
 * Servlet implementation class Employees
 */
@WebServlet("/Employee")
public class Employee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Employee() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Employees> allEmployees = EmployeesDao.getAllEmployees();
		request.setAttribute("emplist", allEmployees);
		RequestDispatcher rd = request.getRequestDispatcher("/viewEmployee.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("submit").equals("Add")) {
			String source = "AddNewEmployees";
			String message = "<h1>Error while " + source + "</h1>";
			PrintWriter out = response.getWriter();
			Employees emp = new Employees();
			try {

				String emp_id = request.getParameter("id");
				try {
					Validator.checkStringForParse(emp_id);
				} catch (InvalidInputDataException e) {
					message += " Error in Employee id input </p>";
					String errorPage = ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
					return; // It terminates the Code execution beyond this point
				}
				int id = Integer.parseInt(emp_id);
				try {
					Validator.CheckNumberForGreaterThanZero(id);
				} catch (InvalidInputDataException e) {
					message += " Error in Employee id input </p>";
					String errorPage = ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
					return;
				}
				emp.setEmp_Id(id);

				String emp_Firstname = request.getParameter("fname");
				try {
					Validator.checkStringOnly(emp_Firstname);
				} catch (InvalidInputDataException e) {
					message += " Error in First Name input </p>";
					String errorPage = ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
					return;
				}
				emp.setFirst_name(emp_Firstname);
				String emp_LastName = request.getParameter("lname");
				try {
					Validator.checkStringOnly(emp_LastName);
				} catch (InvalidInputDataException e) {
					message += " Error in Last Name input </p>";
					String errorPage = ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
					return;
				}
				emp.setLast_name(emp_LastName);
				String emp_email = request.getParameter("email");
				try {
					Validator.checkMail(emp_email);
				} catch (InvalidInputDataException e) {
					message += " Error in email input </p>";
					String errorPage = ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
					return;
				}
				emp.setEmail(emp_email);
				SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
				String emp_HireDate = request.getParameter("hdate");
				// Date hire_date=hire_dateFormate.parse(emp_HireDate);

				try {
					Validator.checkDateFormat(emp_HireDate);
				} catch (InvalidInputDataException e) {
					message += " Error in Hire Date input </p>";
					String errorPage = ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
					return;
				}
				try {
					emp.setHire_date(hire_dateFormate.parse(emp_HireDate));
				} catch (ParseException e) {
					message += " Error in Hire Date input </p>";
					String errorPage = ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
					return;
				}
				String emp_Job_id = request.getParameter("jobid");
				try {
					Validator.checkjob(emp_Job_id);
				} catch (InvalidInputDataException e) {
					message += " Error in Job Id input </p>";
					String errorPage = ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
					return;
				}
				emp.setJob_id(emp_Job_id);
				String emp_salary = null;
				emp_salary = request.getParameter("salary");
				float salary = Float.parseFloat(emp_salary);
				try {
					Validator.checkSalLimit(salary);
				} catch (InvalidInputDataException e) {
					message += " Error in Salary input </p>";
					String errorPage = ExceptionManager.handleException(e, source, message);
					out.print(errorPage);
					return;
				}

				emp.setSalary(salary);
			} catch (Exception e) {
				message += " Error while inserting record </p>";
				String errorPage = ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
			}
			try {
				out.close();
			} catch (Exception e) {
				message += "Message: " + e.getMessage();
				String errorPage = ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
				return;
			}
			int result = EmployeesDao.insertEmployees(emp);
			request.setAttribute("addemp", result);
			RequestDispatcher rd = request.getRequestDispatcher("/AddEmployee.jsp");
			rd.forward(request, response);
		} else if (request.getParameter("submit").equals("update")) {
			doPut(request, response);
		} else if (request.getParameter("submit").equals("Delete")) {
			doDelete(request, response);
		} else if (request.getParameter("submit").equals("view")) {
			doDelete(request, response);
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Employees emp = new Employees();
		try {
			String emp_id = request.getParameter("id");
			try {
				Validator.checkStringForParse(emp_id);
			} catch (InvalidInputDataException e) {
				System.out.println("Error:" + e.getMessage());

			}
			int id = Integer.parseInt(emp_id);
			try {
				Validator.CheckNumberForGreaterThanZero(id);
			} catch (InvalidInputDataException e) {
				out.println("Error in Id:" + e.getMessage());
			}
			emp.setEmp_Id(id);

			String emp_Firstname = request.getParameter("fname");
			try {
				Validator.checkStringOnly(emp_Firstname);
			} catch (InvalidInputDataException e) {
				out.println("Error first name:" + e.getMessage());
			}
			emp.setFirst_name(emp_Firstname);
			String emp_LastName = request.getParameter("lname");
			try {
				Validator.checkStringOnly(emp_LastName);
			} catch (InvalidInputDataException e) {
				out.println("Error in Last name:" + e.getMessage());
			}
			emp.setLast_name(emp_LastName);
			String emp_email = request.getParameter("email");
			try {
				Validator.checkMail(emp_email);
			} catch (InvalidInputDataException e) {
				out.println("Error in Email:" + e.getMessage());
			}
			emp.setEmail(emp_email);
			SimpleDateFormat hire_dateFormate = new SimpleDateFormat("dd/MM/yyyy");
			String emp_HireDate = request.getParameter("hdate");
			// Date hire_date=hire_dateFormate.parse(emp_HireDate);

			try {
				Validator.checkDateFormat(emp_HireDate);
			} catch (InvalidInputDataException e) {
				out.println("Error in Hire date:" + e.getMessage());
			}
			try {
				emp.setHire_date(hire_dateFormate.parse(emp_HireDate));
			} catch (ParseException e) {
				out.println("Error in Hire date:" + e.getMessage());
			}
			String emp_Job_id = request.getParameter("jobid");
			try {
				Validator.checkjob(emp_Job_id);
			} catch (InvalidInputDataException e) {
				out.println("Error in Job id:" + e.getMessage());

			}
			emp.setJob_id(emp_Job_id);
			String emp_salary = request.getParameter("salary");
			float sal = Float.parseFloat(emp_salary);
			try {
				Validator.checkSalLimit(sal);
			} catch (InvalidInputDataException e) {
				out.println("Error in salary:" + e.getMessage());

			}
			float salary = Float.parseFloat(emp_salary);
			emp.setSalary(salary);
			int result = EmployeesDao.updateEmployees(emp);
			out.println(result + "row Updated");
		} catch (Exception e) {
			out.println("Error in some input data:" + e.getMessage());
		}
		try {
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String source = "Update Employee";
		String message = "<h1>Error while " + source + "</h1>";
		PrintWriter out = response.getWriter();
		Employees newemp = null;
		int result = 0;
		int empId = 0;
		String errorPage = null;
		try {
			newemp = new Employees();
			String id = request.getParameter("emp_id");
			try {
				Validator.checkStringForParse(id);
			} catch (InvalidInputDataException e) {
				message += " Error in Employee id input </p>";
				errorPage = ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
			}
			empId = Integer.parseInt(id);
			try {
				Validator.CheckNumberForGreaterThanZero(empId);
			} catch (InvalidInputDataException e) {
				message += " Error in Employee id input </p>";
				errorPage = ExceptionManager.handleException(e, source, message);
				out.print(errorPage);
			}
			newemp.setEmp_Id(empId);
			result = EmployeesDao.deleteEmployees(empId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("<div> Delete Employee: " + result + "</div>");
	}

}
