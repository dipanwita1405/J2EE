package com.name;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Loginservlet
 * 
 * create database log; -- create table log( -- userid varchar(20), -- password
 * varchar(20) -- ) insert into log values('Dip','1234');
 */
@WebServlet("/Loginservlet")
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Loginservlet() {
		super();
		// Default constructor
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Set content type to HTML
		response.setContentType("text/html");

		// Get PrintWriter to write response
		PrintWriter out = response.getWriter();

		// Retrieve parameters from the request
		String userId = request.getParameter("UserId"); // "UserId" instead of "userId"
		String password = request.getParameter("Password"); // "Password" instead of "password"

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/log", "root", "Dipanwita3018@");
			Statement st = conn.createStatement();
			String query = "select * from log where userid='" + userId + "' and password='" + password + "'";
			ResultSet rs = st.executeQuery(query);
			if (rs.next()) {
				out.print("<h1>" + userId + ":Welcome to Homepage</h1><br>");
				out.print("<h1>Login Successfully</h1>");
			} else {
				out.print("<h1>" + userId + ":Please enter correct userid</h1><br>");
				out.print("<h1>Login unsuccessfully</h1>");
			}
			rs.close();
			st.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			out.print("<h1>Login unsuccessfully because of server failed</h1>");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			out.print("<h1>Login unsuccessfully because of server failed</h1>");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Output the userId and password
//		out.println("<html><body>");
//		out.println("<h2>UserId: " + userId + "</h2>");
//		out.println("<h2>Password: " + password + "</h2>");
//		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Handle POST request by calling doGet
		doGet(request, response);
	}
}
