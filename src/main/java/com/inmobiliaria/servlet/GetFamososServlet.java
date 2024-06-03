package com.inmobiliaria.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet(name = "GetFamososServlet", urlPatterns = {"/api/getFamosos"})
public class GetFamososServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dbURL = "jdbc:mariadb://localhost:3306/Inmobiliaria";
        String dbUser = "root";
        String dbPassword = "";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            String sql = "SELECT FamosoId, Nombre FROM famoso";
            statement = connection.prepareStatement(sql);
            
            
            ResultSet resultSet = statement.executeQuery();

            JSONArray jsonArray = new JSONArray();

            while (resultSet.next()) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("famoso_id", resultSet.getInt("FamosoId"));
                jsonObject.put("nombre", resultSet.getString("Nombre"));
                jsonArray.put(jsonObject);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jsonArray.toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                JSONObject errorResponse = new JSONObject();
                errorResponse.put("error", "Error al obtener la lista de famosos: " + e.getMessage());
                response.getWriter().print(errorResponse.toString());
            }
        }
    }
