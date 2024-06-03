package com.inmobiliaria.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/registerProperty")
@MultipartConfig
public class PropertyRegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String propertyType = request.getParameter("propertyType");
        String propertyAddress = request.getParameter("propertyAddress");
        String value = request.getParameter("value");
        String propertyState = request.getParameter("propertyState");
        String famosoId = request.getParameter("famosoId");

        String dbURL = "jdbc:mariadb://localhost:3306/Inmobiliaria";
        String dbUser = "root";
        String dbPassword = "";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            String sql = "INSERT INTO inmueble (Tipo, Ubicacion, Valor, Estado, FamosoId) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, propertyType);
            statement.setString(2, propertyAddress);
            statement.setString(3, value);
            statement.setString(4, propertyState);
            statement.setString(5, famosoId);

            int row = statement.executeUpdate();
            JSONObject jsonResponse = new JSONObject();
            
            if (row > 0) {
                jsonResponse.put("message", "El inmueble ha sido registrado con éxito.");
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                jsonResponse.put("error", "Error al registrar el inmueble.");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jsonResponse.toString());
            out.flush();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Error al registrar el inmueble: " + e.getMessage());
            PrintWriter out = response.getWriter();
            out.print(errorResponse.toString());
            out.flush();
        } finally {
            try {
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
