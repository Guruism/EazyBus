package MyPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class Register extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("name");
        String password = request.getParameter("pword");
        String dob = request.getParameter("dob");

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            // Connect to MySQL database
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eazybus", "root", "gurupreeth");

            // Insert user data into users table
            String sql = "INSERT INTO users (username, password, dob) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, dob);
            stmt.executeUpdate();

            
            // Redirect to login page after successful registration
            response.sendRedirect("home.html?registration=success");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Redirect to registration page with error message
            response.sendRedirect("register.html?error=" + e.getMessage());
        } finally {
            // Close resources
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
