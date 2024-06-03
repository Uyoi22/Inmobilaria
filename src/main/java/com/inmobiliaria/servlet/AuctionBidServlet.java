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

@WebServlet(name = "AuctionBidServlet", urlPatterns = {"/api/placeBid"})
public class AuctionBidServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        JSONObject jsonRequest = new JSONObject(sb.toString());

        int id = jsonRequest.getInt("id");
        double nuevoValor = jsonRequest.getDouble("valor");
        int nuevoFamosoId = jsonRequest.getInt("famosoId");

        String dbURL = "jdbc:mariadb://localhost:3306/Inmobiliaria";
        String dbUser = "root";
        String dbPassword = "";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            String sql = "UPDATE inmueble SET Valor = ?, FamosoId = ? WHERE InmuebleId = ? AND Estado = 'subasta'";
            statement = connection.prepareStatement(sql);
            statement.setDouble(1, nuevoValor);
            statement.setInt(2, nuevoFamosoId);
            statement.setInt(3, id);

            int rowsUpdated = statement.executeUpdate();
            JSONObject jsonResponse = new JSONObject();
            if (rowsUpdated > 0) {
                jsonResponse.put("message", "Puja realizada exitosamente.");
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                jsonResponse.put("error", "No se encontró la propiedad en subasta o no se pudo actualizar.");
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
            errorResponse.put("error", "Error al realizar la puja: " + e.getMessage());
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