package com.sms;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.JSONObject;

@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Profile() {
        super();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject profileJson = new JSONObject();

        String studentIdParam = request.getParameter("studentId");
        if (studentIdParam == null || studentIdParam.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            profileJson.put("error", "Missing studentId parameter");
            response.getWriter().write(profileJson.toString());
            return;
        }

        int studentId = Integer.parseInt(studentIdParam);

        String query = "SELECT " +
                       "  s.roll_number, " +
                       "  s.full_name, " +
                       "  s.email, " +
                       "  s.parent_name, " +
                       "  s.parent_phone, " +
                       "  s.date_of_birth, " +
                       "  CONCAT(c.class_name, ' - ', c.section) AS class_info " + 
                       "FROM students s " +
                       "LEFT JOIN student_enrollments se ON s.student_id = se.student_id " +
                       "LEFT JOIN classes c ON se.class_id = c.class_id " +
                       "WHERE s.student_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(query)) {
            
            statement.setInt(1, studentId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    profileJson.put("roll_number", rs.getString("roll_number") != null ? rs.getString("roll_number") : "N/A");
                    profileJson.put("full_name", rs.getString("full_name") != null ? rs.getString("full_name") : "N/A");
                    profileJson.put("email", rs.getString("email") != null ? rs.getString("email") : "N/A");
                    profileJson.put("parent_name", rs.getString("parent_name") != null ? rs.getString("parent_name") : "N/A");
                    profileJson.put("phone", rs.getString("parent_phone") != null ? rs.getString("parent_phone") : "N/A");
                    profileJson.put("dob", rs.getString("date_of_birth") != null ? rs.getString("date_of_birth") : "N/A");
                    profileJson.put("class_info", rs.getString("class_info") != null ? rs.getString("class_info") : "Not Enrolled");
                    profileJson.put("address", "Karnataka, India"); // Default fallback placeholder string
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    profileJson.put("error", "Student record not found");
                }
            }
            
            response.getWriter().write(profileJson.toString());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            try {
                response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
            } catch (Exception ignored) {}
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
