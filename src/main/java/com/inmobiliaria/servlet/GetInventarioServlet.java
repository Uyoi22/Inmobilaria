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

@WebServlet(name = "GetInventarioServlet", urlPatterns = {"/api/getInventario"})
@MultipartConfig

public class GetInventarioServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dbURL = "jdbc:mariadb://localhost:3306/Inmobiliaria";
        String dbUser = "root";
        String dbPassword = "";

        String famosoId = request.getParameter("famosoId");

        if (famosoId == null || famosoId.isEmpty()) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Famoso ID is required");
            response.getWriter().print(errorResponse.toString());
            return;
        }
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
            
            String sql = "SELECT f.FamosoId, f.Nombre, p.InmuebleId, p.Tipo, p.Ubicacion, p.Valor, "
                    + "t.TransaccionId, t.Fecha, t.Precio, t.Comision, tr.TributoId, tr.Impuesto "
                    + "FROM famoso f "
                    + "JOIN inmueble p ON f.FamosoId = p.FamosoId "
                    + "LEFT JOIN transaccion t ON p.InmuebleId = t.InmuebleId "
                    + "LEFT JOIN tributo tr ON t.TransaccionId = tr.TransaccionId "
                    + "WHERE f.FamosoId = ? "
                    + "ORDER BY t.Fecha DESC";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(famosoId));
            
            resultSet = statement.executeQuery();

            JSONArray items = new JSONArray();

            while (resultSet.next()) {
                JSONObject inventario = new JSONObject();
                inventario.put("inmueble_id", resultSet.getInt("InmuebleId"));
                inventario.put("famoso_nombre", resultSet.getString("Nombre"));
                inventario.put("tipo", resultSet.getString("Tipo"));
                inventario.put("ubicacion", resultSet.getString("Ubicacion"));
                inventario.put("valor", resultSet.getDouble("Valor"));
                inventario.put("precio", resultSet.getDouble("Precio"));
                inventario.put("comision", resultSet.getDouble("Comision"));
                inventario.put("impuesto", resultSet.getDouble("Impuesto"));
                inventario.put("fecha", resultSet.getString("Fecha"));
                items.put(inventario);
            }

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            PrintWriter out = response.getWriter();
            out.print(items.toString());
            out.flush();
            
            System.out.println("JSON Response: " + items); // Log the response
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Error al obtener el inventario de bienes: " + e.getMessage());
            response.getWriter().print(errorResponse.toString());
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
