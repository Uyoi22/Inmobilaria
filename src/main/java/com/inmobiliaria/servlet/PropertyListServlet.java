package com.inmobiliaria.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "PropertyListServlet", urlPatterns = "/api/properties")
@MultipartConfig

public class PropertyListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String estado = request.getParameter("estado");

        String dbURL = "jdbc:mariadb://localhost:3306/Inmobiliaria";
        String dbUser = "root";
        String dbPassword = "";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            String sql = "SELECT * FROM inmueble WHERE Estado = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, estado);

            resultSet = statement.executeQuery();

            JSONArray properties = new JSONArray();

            while (resultSet.next()) {
                JSONObject property = new JSONObject();
                property.put("id", resultSet.getInt("InmuebleId"));
                property.put("tipo", resultSet.getString("Tipo"));
                property.put("ubicacion", resultSet.getString("Ubicacion"));
                property.put("valor", resultSet.getBigDecimal("Valor"));
                property.put("estado", resultSet.getString("Estado"));
                property.put("famoso_id", resultSet.getInt("FamosoId"));
                properties.put(property);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(properties.toString());
            out.flush();
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", e.getMessage());  // Include the exception message for debugging
            PrintWriter out = response.getWriter();
            out.print(errorResponse.toString());
            out.flush();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}