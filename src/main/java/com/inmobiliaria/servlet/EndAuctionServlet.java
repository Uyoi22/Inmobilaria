package com.inmobiliaria.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet(name="EndAuctionServlet", urlPatterns = "/api/endAuction")
public class EndAuctionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONObject jsonRequest = new JSONObject(sb.toString());

        int id = jsonRequest.getInt("id");
        int famosoId = jsonRequest.getInt("famosoId");
        double valor = jsonRequest.getDouble("precio");
        String tipo = jsonRequest.getString("tipo");

        String dbURL = "jdbc:mariadb://localhost:3306/Inmobiliaria";
        String dbUser = "root";
        String dbPassword = "";

        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement insertTransactionStmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            String sql = "UPDATE inmueble SET Estado = 'vendido' WHERE InmuebleId = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            int rowsUpdated = statement.executeUpdate();
            
            JSONObject jsonResponse = new JSONObject();
            
            if (rowsUpdated > 0) {
                double comision = valor * 0.05;
                
                String insertTransactionSql = "INSERT INTO transaccion (Tipo, Fecha, InmuebleId, FamosoId, Precio, Comision) VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
                insertTransactionStmt = connection.prepareStatement(insertTransactionSql);
                insertTransactionStmt.setString(1, tipo);
                insertTransactionStmt.setInt(2, id);
                insertTransactionStmt.setInt(3, famosoId);
                insertTransactionStmt.setDouble(4, valor);
                insertTransactionStmt.setDouble(5, comision);
                insertTransactionStmt.executeUpdate();
                
                connection.commit();
                
                jsonResponse.put("message", "Subasta finalizada exitosamente.");
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                jsonResponse.put("error", "No se encontró la propiedad o no se pudo actualizar.");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(jsonResponse.toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Error al finalizar la subasta: " + e.getMessage());
            response.getWriter().print(errorResponse.toString());
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
