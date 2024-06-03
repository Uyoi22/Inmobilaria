package com.inmobiliaria.servlet;

import java.io.BufferedReader;
import java.io.IOException;
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

import org.json.JSONObject;

@WebServlet("/api/updateProperty")
public class PropertyUpdateServlet extends HttpServlet {

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
        int nuevoFamosoId = jsonRequest.getInt("famosoId");
        double valor = jsonRequest.getDouble("precio");
        String tipo = jsonRequest.getString("tipo");

        String dbURL = "jdbc:mariadb://localhost:3306/Inmobiliaria";
        String dbUser = "root";
        String dbPassword = "";

        Connection connection = null;
        PreparedStatement updatePropertyStmt = null;
        PreparedStatement insertTransactionStmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            connection.setAutoCommit(false);

            String updatePropertySql = "UPDATE inmueble SET FamosoId = ?, Estado = 'vendido' WHERE InmuebleId = ?";
            updatePropertyStmt = connection.prepareStatement(updatePropertySql);
            updatePropertyStmt.setInt(1, nuevoFamosoId);
            updatePropertyStmt.setInt(2, id);
            int rowsUpdated = updatePropertyStmt.executeUpdate();

            if (rowsUpdated > 0) {
                double comision = valor * 0.05;
                double impuestos = valor * 0.15; // Por ejemplo, 15% de impuestos

                String insertTransactionSql = "INSERT INTO transaccion (Tipo, Fecha, InmuebleId, FamosoId, Precio, Comision) VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
                insertTransactionStmt = connection.prepareStatement(insertTransactionSql);
                insertTransactionStmt.setString(1, tipo);
                insertTransactionStmt.setInt(2, id);
                insertTransactionStmt.setInt(3, nuevoFamosoId);
                insertTransactionStmt.setDouble(4, valor);
                insertTransactionStmt.setDouble(5, comision);
                insertTransactionStmt.executeUpdate();

                // Obtener el ID de la transacción recién insertada
                ResultSet rs = insertTransactionStmt.executeQuery("SELECT @@IDENTITY AS TransaccionId");
                int transaccionId = 0;
                if (rs.next()) {
                    transaccionId = rs.getInt("TransaccionId");
                }

                // Insertar registro en la tabla `tributo`
                String insertTributoSql = "INSERT INTO tributo (TransaccionId, FamosoId, Impuesto) VALUES (?, ?, ?)";
                PreparedStatement insertTributoStmt = connection.prepareStatement(insertTributoSql);
                insertTributoStmt.setInt(1, transaccionId);
                insertTributoStmt.setInt(2, nuevoFamosoId);
                insertTributoStmt.setDouble(3, impuestos);
                insertTributoStmt.executeUpdate();
                
                connection.commit();

                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("message", "Propiedad actualizada y transacción registrada exitosamente.");
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(jsonResponse.toString());
            } else {
                connection.rollback();
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("error", "No se encontró la propiedad o no se pudo actualizar.");
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(jsonResponse.toString());
            }
        } catch (SQLException | ClassNotFoundException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Error al actualizar la propiedad y registrar la transacción: " + e.getMessage());
            response.getWriter().print(errorResponse.toString());
        } finally {
            try {
                if (updatePropertyStmt != null) {
                    updatePropertyStmt.close();
                }
                if (insertTransactionStmt != null) {
                    insertTransactionStmt.close();
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
