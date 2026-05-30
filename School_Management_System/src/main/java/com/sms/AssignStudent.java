package com.sms;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONObject;
@WebServlet("/AssignStudent")
public class AssignStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public AssignStudent() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		JSONObject finalJson = new JSONObject();
		JSONArray studentsArray = new JSONArray();
		JSONArray classesArray = new JSONArray();
		JSONArray enrollmentArray = new JSONArray();
		try (Connection conn = DBConnection.getConnection()) {
			String studentSql = "SELECT student_id, full_name FROM students";
			try (PreparedStatement studentStatement = conn.prepareStatement(studentSql);
				 ResultSet studentRs = studentStatement.executeQuery()) {
				while (studentRs.next()) {
					JSONObject student = new JSONObject();
					student.put("student_id", studentRs.getInt("student_id"));
					student.put("full_name", studentRs.getString("full_name"));
					studentsArray.put(student);
				}
			}
			String classSql = "SELECT class_id, class_name, section FROM classes";
			try (PreparedStatement classStatement = conn.prepareStatement(classSql);
				 ResultSet classRs = classStatement.executeQuery()) {
				while (classRs.next()) {
					JSONObject classes = new JSONObject();
					classes.put("class_id", classRs.getInt("class_id"));
					classes.put("class_name", classRs.getString("class_name"));
					classes.put("section", classRs.getString("section"));
					classesArray.put(classes);
				}
			}
			String enrollmentSql =
					"SELECT se.enrollment_id, " +
					"s.full_name, " +
					"s.roll_number, " +
					"c.class_name, " +
					"c.section, " +
					"se.assigned_on " +
					"FROM student_enrollments se " +
					"JOIN students s ON se.student_id = s.student_id " +
					"JOIN classes c ON se.class_id = c.class_id";
			try (PreparedStatement enrollmentStatement = conn.prepareStatement(enrollmentSql);
				 ResultSet enrollmentRs = enrollmentStatement.executeQuery()) {
				while (enrollmentRs.next()) {
					JSONObject enrollment = new JSONObject();
					enrollment.put("enrollment_id", enrollmentRs.getInt("enrollment_id"));
					enrollment.put("full_name", enrollmentRs.getString("full_name"));
					enrollment.put("roll_number", enrollmentRs.getString("roll_number"));
					enrollment.put("class_name", enrollmentRs.getString("class_name"));
					enrollment.put("section", enrollmentRs.getString("section"));
					enrollment.put("assigned_on", enrollmentRs.getString("assigned_on"));
					enrollmentArray.put(enrollment);
				}
			}
			finalJson.put("students", studentsArray);
			finalJson.put("classes", classesArray);
			finalJson.put("enrollments", enrollmentArray);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			finalJson.put("status", "error");
			finalJson.put("message", "Database read error: " + e.getMessage());
		}
		response.getWriter().print(finalJson.toString());
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject jsonResponse = new JSONObject();
		StringBuilder sb = new StringBuilder();
		String line;
		try (BufferedReader reader = request.getReader()) {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		}
		try (Connection conn = DBConnection.getConnection()) {
			JSONObject jsonInput = new JSONObject(sb.toString());
			int student_id = jsonInput.getInt("student_id");
			int class_id = jsonInput.getInt("class_id");
			String checkSql = "SELECT * FROM student_enrollments WHERE student_id=?";
			try (PreparedStatement checkStatement = conn.prepareStatement(checkSql)) {
				checkStatement.setInt(1, student_id);
				try (ResultSet rs = checkStatement.executeQuery()) {
					if (rs.next()) {
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						jsonResponse.put("status", "error");
						jsonResponse.put("message", "Student already assigned to a class!");
						out.print(jsonResponse.toString());
						return;
					}
				}
			}
			String insertSql = "INSERT INTO student_enrollments(student_id, class_id, assigned_on) VALUES(?,?,CURDATE())";
			try (PreparedStatement insertStatement = conn.prepareStatement(insertSql)) {
				insertStatement.setInt(1, student_id);
				insertStatement.setInt(2, class_id);
				int rows = insertStatement.executeUpdate();
				if (rows > 0) {
					response.setStatus(HttpServletResponse.SC_OK);
					jsonResponse.put("status", "success");
					jsonResponse.put("message", "Student assigned successfully!");
				} else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					jsonResponse.put("status", "error");
					jsonResponse.put("message", "Failed to assign student entry.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			jsonResponse.put("status", "error");
			jsonResponse.put("message", "Something went wrong: " + e.getMessage());
		}
		out.print(jsonResponse.toString());
	}
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject jsonResponse = new JSONObject();
		String enrollmentIdStr = request.getParameter("enrollmentId");

		if (enrollmentIdStr == null || enrollmentIdStr.trim().isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			jsonResponse.put("status", "error");
			jsonResponse.put("message", "Missing enrollment ID context parameter.");
			out.print(jsonResponse.toString());
			return;
		}
		String sql = "DELETE FROM student_enrollments WHERE enrollment_id = ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement statement = conn.prepareStatement(sql)) {
			int enrollmentId = Integer.parseInt(enrollmentIdStr);
			statement.setInt(1, enrollmentId);
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				response.setStatus(HttpServletResponse.SC_OK);
				jsonResponse.put("status", "success");
				jsonResponse.put("message", "Enrollment removed successfully!");
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				jsonResponse.put("status", "error");
				jsonResponse.put("message", "Enrollment record not found.");
			}
		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			jsonResponse.put("status", "error");
			jsonResponse.put("message", "Invalid enrollment ID parameter string structure.");
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			jsonResponse.put("status", "error");
			jsonResponse.put("message", "Database query execution fault: " + e.getMessage());
		}
		out.print(jsonResponse.toString());
		out.flush();
	}
}