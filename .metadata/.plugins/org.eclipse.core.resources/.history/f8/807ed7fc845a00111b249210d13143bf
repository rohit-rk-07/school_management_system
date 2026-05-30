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

@WebServlet("/AdminDashboard")
public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminDashboard() {
        super();
    }
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        JSONObject finalJson = new JSONObject();
        JSONArray recentTeachersArray = new JSONArray();
        JSONArray recentStudentsArray = new JSONArray();

        try (Connection conn = DBConnection.getConnection()) {

            // TOTAL TEACHERS
            String teacherCountSql = "SELECT COUNT(*) AS total_teachers FROM teachers";
            try (PreparedStatement statement = conn.prepareStatement(teacherCountSql);
                 ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    finalJson.put("totalTeachers", rs.getInt("total_teachers"));
                }
            }

            // TOTAL STUDENTS
            String studentCountSql = "SELECT COUNT(*) AS total_students FROM students";
            try (PreparedStatement statement = conn.prepareStatement(studentCountSql);
                 ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    finalJson.put("totalStudents", rs.getInt("total_students"));
                }
            }

            // TOTAL CLASSES
            String classCountSql = "SELECT COUNT(*) AS total_classes FROM classes";
            try (PreparedStatement statement = conn.prepareStatement(classCountSql);
                 ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    finalJson.put("totalClasses", rs.getInt("total_classes"));
                }
            }

            // RECENT TEACHERS
            String recentTeacherSql = "SELECT full_name, subject FROM teachers ORDER BY teacher_id DESC LIMIT 3";
            try (PreparedStatement statement = conn.prepareStatement(recentTeacherSql);
                 ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    JSONObject teacher = new JSONObject();
                    teacher.put("full_name", rs.getString("full_name"));
                    teacher.put("subject", rs.getString("subject"));
                    recentTeachersArray.put(teacher);
                }
            }

            // RECENT STUDENTS
            String recentStudentSql = 
                    "SELECT s.full_name, c.class_name, c.section " +
                    "FROM students s " +
                    "LEFT JOIN student_enrollments se ON s.student_id = se.student_id " +
                    "LEFT JOIN classes c ON se.class_id = c.class_id " +
                    "ORDER BY s.student_id DESC LIMIT 3";
                    
            try (PreparedStatement statement = conn.prepareStatement(recentStudentSql);
                 ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    JSONObject student = new JSONObject();
                    student.put("full_name", rs.getString("full_name"));
                    student.put("class_name", rs.getString("class_name"));
                    student.put("section", rs.getString("section"));
                    recentStudentsArray.put(student);
                }
            }

            // ASSIGN ARRAYS TO FINAL JSON
            finalJson.put("recentTeachers", recentTeachersArray);
            finalJson.put("recentStudents", recentStudentsArray);

            // Write output only if everything succeeded
            response.getWriter().print(finalJson.toString());

        } catch (Exception e) {
            e.printStackTrace();
            // Crucial: Tell the frontend something actually failed!
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database processing error: " + e.getMessage());
        }
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
