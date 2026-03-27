package car;

//Minimum packages only
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;

import java.util.ArrayList;

public class CarServiceDAO {

 // ── Connection details — CHANGE THESE ──
 static final String URL  = "jdbc:mysql://localhost:3306/car_service_db";
 static final String USER = "root";
 static final String PASS = "Darshu@1203!";

 // ── Get connection ──
 static Connection getConn() throws Exception {
     Class.forName("com.mysql.cj.jdbc.Driver");
     return DriverManager.getConnection(URL, USER, PASS);
 }

 // ── CREATE ──
 public void create(CarServiceRecord r) {
     String sql = "INSERT INTO car_service_records " +
                  "(owner_name, car_model, license_no, service_date, service_type, cost, mechanic) " +
                  "VALUES (?, ?, ?, ?, ?, ?, ?)";
     try {
         Connection con       = getConn();
         PreparedStatement ps = con.prepareStatement(sql);

         ps.setString(1, r.ownerName);
         ps.setString(2, r.carModel);
         ps.setString(3, r.licenseNo);
         ps.setDate(4,   Date.valueOf(r.serviceDate));  // needs "YYYY-MM-DD"
         ps.setString(5, r.serviceType);
         ps.setDouble(6, r.cost);
         ps.setString(7, r.mechanic);

         ps.executeUpdate();
         System.out.println("Record added successfully!");

         ps.close();
         con.close();

     } catch (Exception e) {
         System.out.println("Error: " + e.getMessage());
     }
 }

 // ── READ ALL ──
 public void readAll() {
     String sql = "SELECT * FROM car_service_records";
     try {
         Connection con  = getConn();
         Statement  stmt = con.createStatement();
         ResultSet  rs   = stmt.executeQuery(sql);

         boolean found = false;
         while (rs.next()) {
             found = true;
             CarServiceRecord r = new CarServiceRecord(
                 rs.getInt("id"),
                 rs.getString("owner_name"),
                 rs.getString("car_model"),
                 rs.getString("license_no"),
                 rs.getDate("service_date").toString(),
                 rs.getString("service_type"),
                 rs.getDouble("cost"),
                 rs.getString("mechanic")
             );
             System.out.println(r);
         }
         if (!found) System.out.println("No records found.");

         rs.close();
         stmt.close();
         con.close();

     } catch (Exception e) {
         System.out.println("Error: " + e.getMessage());
     }
 }

 // ── READ BY ID ──
 public void readById(int id) {
     String sql = "SELECT * FROM car_service_records WHERE id = ?";
     try {
         Connection        con  = getConn();
         PreparedStatement ps   = con.prepareStatement(sql);
         ps.setInt(1, id);
         ResultSet rs = ps.executeQuery();

         if (rs.next()) {
             CarServiceRecord r = new CarServiceRecord(
                 rs.getInt("id"),
                 rs.getString("owner_name"),
                 rs.getString("car_model"),
                 rs.getString("license_no"),
                 rs.getDate("service_date").toString(),
                 rs.getString("service_type"),
                 rs.getDouble("cost"),
                 rs.getString("mechanic")
             );
             System.out.println(r);
         } else {
             System.out.println("No record found with ID: " + id);
         }

         rs.close();
         ps.close();
         con.close();

     } catch (Exception e) {
         System.out.println("Error: " + e.getMessage());
     }
 }

 // ── UPDATE ──
 public void update(int id, CarServiceRecord r) {
     String sql = "UPDATE car_service_records SET " +
                  "owner_name=?, car_model=?, license_no=?, " +
                  "service_date=?, service_type=?, cost=?, mechanic=? " +
                  "WHERE id=?";
     try {
         Connection        con = getConn();
         PreparedStatement ps  = con.prepareStatement(sql);

         ps.setString(1, r.ownerName);
         ps.setString(2, r.carModel);
         ps.setString(3, r.licenseNo);
         ps.setDate(4,   Date.valueOf(r.serviceDate));
         ps.setString(5, r.serviceType);
         ps.setDouble(6, r.cost);
         ps.setString(7, r.mechanic);
         ps.setInt(8,    id);

         int rows = ps.executeUpdate();
         if (rows > 0) System.out.println("Record updated successfully!");
         else           System.out.println("No record found with ID: " + id);

         ps.close();
         con.close();

     } catch (Exception e) {
         System.out.println("Error: " + e.getMessage());
     }
 }

 // ── DELETE ──
 public void delete(int id) {
     String sql = "DELETE FROM car_service_records WHERE id = ?";
     try {
         Connection        con = getConn();
         PreparedStatement ps  = con.prepareStatement(sql);
         ps.setInt(1, id);

         int rows = ps.executeUpdate();
         if (rows > 0) System.out.println("Record deleted successfully!");
         else           System.out.println("No record found with ID: " + id);

         ps.close();
         con.close();

     } catch (Exception e) {
         System.out.println("Error: " + e.getMessage());
     }
 }
}