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
@WebServlet("/AssignTeacher")
public class AssignTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AssignTeacher() {
		super();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject finalJson = new JSONObject();
		JSONArray teachersArray = new JSONArray();
		JSONArray classesArray = new JSONArray();
		JSONArray assignmentsArray = new JSONArray();
		try (Connection conn = DBConnection.getConnection()) {
			String teacherSql = "SELECT teacher_id, full_name FROM teachers";
			try (PreparedStatement teacherStatement = conn.prepareStatement(teacherSql);
				 ResultSet teacherRs = teacherStatement.executeQuery()) {
				while (teacherRs.next()) {
					JSONObject teacher = new JSONObject();
					teacher.put("teacher_id", teacherRs.getInt("teacher_id"));
					teacher.put("full_name", teacherRs.getString("full_name"));
					teachersArray.put(teacher);
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
			String assignmentSql =
					"SELECT cs.schedule_id, " +
					"t.full_name, " +
					"cs.subject_name, " +
					"c.class_name, " +
					"c.section " +
					"FROM class_schedules cs " +
					"JOIN teachers t ON cs.teacher_id = t.teacher_id " +
					"JOIN classes c ON cs.class_id = c.class_id";
			try (PreparedStatement assignmentStatement = conn.prepareStatement(assignmentSql);
				 ResultSet assignmentRs = assignmentStatement.executeQuery()) {
				while (assignmentRs.next()) {
					JSONObject assignment = new JSONObject();
					assignment.put("schedule_id", assignmentRs.getInt("schedule_id"));
					assignment.put("full_name", assignmentRs.getString("full_name"));
					assignment.put("subject_name", assignmentRs.getString("subject_name"));
					assignment.put("class_name", assignmentRs.getString("class_name"));
					assignment.put("section", assignmentRs.getString("section"));
					assignmentsArray.put(assignment);
				}
			}
			finalJson.put("teachers", teachersArray);
			finalJson.put("classes", classesArray);
			finalJson.put("assignments", assignmentsArray);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			finalJson.put("status", "error");
			finalJson.put("message", "Database compilation retrieval fault: " + e.getMessage());
		}
		out.print(finalJson.toString());
		out.flush();
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
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			jsonResponse.put("status", "error");
			jsonResponse.put("message", "Error interpretation failure on request payload.");
			out.print(jsonResponse.toString());
			return;
		}
		try (Connection conn = DBConnection.getConnection()) {
			JSONObject jsonInput = new JSONObject(sb.toString());
			int teacher_id = jsonInput.getInt("teacher_id");
			int class_id = jsonInput.getInt("class_id");
			String subject_name = jsonInput.getString("subject_name");
			String checkSql = "SELECT * FROM class_schedules WHERE teacher_id=? AND class_id=? AND subject_name=?";
			try (PreparedStatement checkStatement = conn.prepareStatement(checkSql)) {
				checkStatement.setInt(1, teacher_id);
				checkStatement.setInt(2, class_id);
				checkStatement.setString(3, subject_name);

				try (ResultSet rs = checkStatement.executeQuery()) {
					if (rs.next()) {
						response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
						jsonResponse.put("status", "error");
						jsonResponse.put("message", "Teacher already assigned!");
						out.print(jsonResponse.toString());
						return;
					}
				}
			}
			String insertSql = "INSERT INTO class_schedules (teacher_id, class_id, subject_name) VALUES(?,?,?)";
			try (PreparedStatement insertStatement = conn.prepareStatement(insertSql)) {
				insertStatement.setInt(1, teacher_id);
				insertStatement.setInt(2, class_id);
				insertStatement.setString(3, subject_name);
				
				int rows = insertStatement.executeUpdate();
				if (rows > 0) {
					response.setStatus(HttpServletResponse.SC_OK);
					jsonResponse.put("status", "success");
					jsonResponse.put("message", "Teacher assigned successfully!");
				} else {
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
					jsonResponse.put("status", "error");
					jsonResponse.put("message", "Failed to update record context inside database storage.");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			jsonResponse.put("status", "error");
			jsonResponse.put("message", "Something went wrong: " + e.getMessage());
		}
		out.print(jsonResponse.toString());
		out.flush();
	}
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		JSONObject jsonResponse = new JSONObject();
		String scheduleIdStr = request.getParameter("scheduleId");
		if (scheduleIdStr == null || scheduleIdStr.trim().isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			jsonResponse.put("status", "error");
			jsonResponse.put("message", "Missing query criteria sequence value parameter.");
			out.print(jsonResponse.toString());
			return;
		}
		String sql = "DELETE FROM class_schedules WHERE schedule_id = ?";
		try (Connection conn = DBConnection.getConnection();
			 PreparedStatement statement = conn.prepareStatement(sql)) {
			int scheduleId = Integer.parseInt(scheduleIdStr);
			statement.setInt(1, scheduleId);
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				response.setStatus(HttpServletResponse.SC_OK);
				jsonResponse.put("status", "success");
				jsonResponse.put("message", "Teacher assignment removed successfully!");
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				jsonResponse.put("status", "error");
				jsonResponse.put("message", "Assignment reference mapping not located.");
			}
		} catch (NumberFormatException e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			jsonResponse.put("status", "error");
			jsonResponse.put("message", "Faulty parameter tracking structure type configuration.");
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			jsonResponse.put("status", "error");
			jsonResponse.put("message", "Database processing query level error: " + e.getMessage());
		}
		out.print(jsonResponse.toString());
		out.flush();
	}
}