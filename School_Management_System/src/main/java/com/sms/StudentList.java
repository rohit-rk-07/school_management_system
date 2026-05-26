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

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/StudentList")
public class StudentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public StudentList() {
        super();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        JSONArray studentArray = new JSONArray();

        
        String query = "SELECT " +
                       "  s.roll_number, " +
                       "  s.full_name, " +
                       "  s.parent_name, " +
                       "  s.parent_phone, " +
                       "  CONCAT(c.class_name, ' - ', c.section) AS class_info " +
                       "FROM students s " +
                       "LEFT JOIN student_enrollments se ON s.student_id = se.student_id " +
                       "LEFT JOIN classes c ON se.class_id = c.class_id";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                JSONObject student = new JSONObject();
                student.put("roll_number", rs.getString("roll_number") != null ? rs.getString("roll_number") : "N/A");
                student.put("full_name", rs.getString("full_name") != null ? rs.getString("full_name") : "N/A");
                student.put("class_info", rs.getString("class_info") != null ? rs.getString("class_info") : "Not Assigned");
                student.put("parent_name", rs.getString("parent_name") != null ? rs.getString("parent_name") : "N/A");
                student.put("phone", rs.getString("parent_phone") != null ? rs.getString("parent_phone") : "N/A");
                
                studentArray.put(student);
            }

            response.getWriter().write(studentArray.toString());

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
