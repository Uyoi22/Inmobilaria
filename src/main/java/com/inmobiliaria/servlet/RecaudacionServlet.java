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

@WebServlet(name = "RecaudacionServlet", urlPatterns = {"/api/recaudacion"})
@MultipartConfig

public class RecaudacionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String dbURL = "jdbc:mariadb://localhost:3306/Inmobiliaria";
        String dbUser = "root";
        String dbPassword = "";
        
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement impuestosStmt = null;
        ResultSet comisionesRs = null;
        ResultSet impuestosRs = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            // Consultar el valor recaudado por comisiones
            String comisionesSql = "SELECT SUM(Comision) AS TotalComisiones FROM transaccion";
            statement = connection.prepareStatement(comisionesSql);
            comisionesRs = statement.executeQuery();
            
            double totalComisiones = 0;
            if (comisionesRs.next()) {
                totalComisiones = comisionesRs.getDouble("TotalComisiones");
            }

            // Consultar el valor pagado en impuestos por nacionalidad
            String impuestosSql = "SELECT f.Nacionalidad, SUM(t.Impuesto) AS TotalImpuestos "
                    + "FROM tributo t "
                    + "JOIN famoso f ON t.FamosoId = f.FamosoId "
                    + "GROUP BY f.Nacionalidad";
            impuestosStmt = connection.prepareStatement(impuestosSql);
            impuestosRs = impuestosStmt.executeQuery();

            JSONArray impuestosArray = new JSONArray();
            while (impuestosRs.next()) {
                JSONObject impuestosObj = new JSONObject();
                impuestosObj.put("nacionalidad", impuestosRs.getString("Nacionalidad"));
                impuestosObj.put("totalImpuestos", impuestosRs.getDouble("TotalImpuestos"));
                impuestosArray.put(impuestosObj);
            }

            JSONObject result = new JSONObject();
            result.put("totalComisiones", totalComisiones);
            result.put("impuestosPorPais", impuestosArray);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(result.toString());
            out.flush();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", e.getMessage());
            PrintWriter out = response.getWriter();
            out.print(errorResponse.toString());
            out.flush();
        } finally {
            try {
                if (comisionesRs != null) {
                    comisionesRs.close();
                }
                if (impuestosRs != null) {
                    impuestosRs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (impuestosStmt != null) {
                    impuestosStmt.close();
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
