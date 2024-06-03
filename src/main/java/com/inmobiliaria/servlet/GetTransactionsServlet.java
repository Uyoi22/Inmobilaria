package com.inmobiliaria.servlet;

import java.io.IOException;
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

@WebServlet(name = "GetTransactionsServlet", urlPatterns = "/api/getTransactions")
@MultipartConfig

public class GetTransactionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String dbURL = "jdbc:mariadb://localhost:3306/Inmobiliaria";
        String dbUser = "root";
        String dbPassword = "";

        try (Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPassword)) {
            
            String sql = "SELECT * FROM transaccion";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            JSONArray jsonArray = new JSONArray();

            while (resultSet.next()) {
                JSONObject transaccion = new JSONObject();
                transaccion.put("transaccion_id", resultSet.getInt("TransaccionId"));
                transaccion.put("tipo", resultSet.getString("Tipo"));
                transaccion.put("fecha", resultSet.getString("Fecha"));
                transaccion.put("inmueble_id", resultSet.getInt("InmuebleId"));
                transaccion.put("famoso_id", resultSet.getInt("FamosoId"));
                transaccion.put("precio", resultSet.getDouble("Precio"));
                transaccion.put("comision", resultSet.getDouble("Comision"));
                jsonArray.put(transaccion);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jsonArray.toString());
            
        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Error al obtener las transacciones: " + e.getMessage());
            response.getWriter().print(errorResponse.toString());
        }
    }
}
