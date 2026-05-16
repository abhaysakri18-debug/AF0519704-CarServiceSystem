package com.car;

import java.sql.*;
import java.util.Scanner;

public class CarSystem {

    Scanner sc = new Scanner(System.in);

    Connection conn = DBConnection.getConnection();

    // SESSION VARIABLES
    private int currentUserId;
    private String currentUserName;
    private String currentUserRole;

    // =========================
    // REGISTER
    // =========================

    public void register() {

        try {

            System.out.println("\n===== BUYER REGISTRATION =====");

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            // EMAIL VALIDATION
            if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {

                System.out.println("❌ Invalid Email Format");
                return;
            }

            // DUPLICATE EMAIL CHECK
            String checkSql =
                    "SELECT * FROM users WHERE email=?";

            PreparedStatement checkPs =
                    conn.prepareStatement(checkSql);

            checkPs.setString(1, email);

            ResultSet checkRs =
                    checkPs.executeQuery();

            if (checkRs.next()) {

                System.out.println("❌ Email Already Registered");
                return;
            }

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            // PASSWORD VALIDATION
            if (password.length() < 6) {

                System.out.println(
                        "❌ Password Must Be At Least 6 Characters");

                return;
            }

            String sql =
                    "INSERT INTO users(name,email,password,role) VALUES(?,?,?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, "BUYER");

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("✅ Registration Successful");

            } else {

                System.out.println("❌ Registration Failed");
            }

        } catch (Exception e) {

            System.out.println("Registration Error");
            System.out.println(e.getMessage());
        }
    }
    // =========================
    // LOGIN
    // =========================

    public boolean login() {

        try {

            System.out.println("\n===== LOGIN =====");

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Password: ");
            String password = sc.nextLine();

            String sql =
                    "SELECT * FROM users WHERE email=? AND password=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                currentUserId = rs.getInt("user_id");
                currentUserName = rs.getString("name");
                currentUserRole = rs.getString("role");

                System.out.println(
                        "\n✅ Login Successful");
                System.out.println(
                        "Welcome " + currentUserName);

                return true;

            } else {

                System.out.println("❌ Invalid Email or Password");
            }

        } catch (Exception e) {

            System.out.println("Login Error");
            System.out.println(e.getMessage());
        }

        return false;
    }

    // =========================
    // LOGOUT
    // =========================

    public void logout() {

        currentUserId = 0;
        currentUserName = null;
        currentUserRole = null;

        System.out.println("✅ Logged Out");
    }

    // =========================
    // ROLE CHECK
    // =========================

    public String getCurrentUserRole() {
        return currentUserRole;
    }

    // =========================
    // ADMIN DASHBOARD
    // =========================

    public void adminDashboard() {

        int choice;

        do {

            System.out.println("\n===== ADMIN DASHBOARD =====");

            System.out.println("1. Add Car");
            System.out.println("2. View All Cars");
            System.out.println("3. Update Car");
            System.out.println("4. Delete Car");
            System.out.println("5. View Sold Cars");
            System.out.println("6. Logout");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addCar();
                    break;

                case 2:
                    viewAllCars();
                    break;

                case 3:
                    updateCar();
                    break;

                case 4:
                    deleteCar();
                    break;

                case 5:
                    viewSoldCars();
                    break;

                case 6:
                    logout();
                    break;

                default:
                    System.out.println("❌ Invalid Choice");
            }

        } while (choice != 6);
    }

    // =========================
    // BUYER DASHBOARD
    // =========================

    public void buyerDashboard() {

        int choice;

        do {

            System.out.println("\n===== BUYER DASHBOARD =====");

            System.out.println("1. View Available Cars");
            System.out.println("2. Search Car By Brand");
            System.out.println("3. Buy Car");
            System.out.println("4. My Purchases");
            System.out.println("5. Logout");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    viewAvailableCars();
                    break;

                case 2:
                    searchCar();
                    break;

                case 3:
                    buyCar();
                    break;

                case 4:
                    myPurchases();
                    break;

                case 5:
                    logout();
                    break;

                default:
                    System.out.println("❌ Invalid Choice");
            }

        } while (choice != 5);
    }

    // =========================
    // ADD CAR
    // =========================

    public void addCar() {

        try {

            System.out.println("\n===== ADD CAR =====");

            System.out.print("Brand: ");
            String brand = sc.nextLine();

            System.out.print("Model: ");
            String model = sc.nextLine();

            System.out.print("Year: ");
            int year = sc.nextInt();

            System.out.print("Price: ");
            double price = sc.nextDouble();

            System.out.print("Stock Quantity: ");
            int stock = sc.nextInt();
            sc.nextLine();

            String sql =
                    "INSERT INTO cars(brand,model,year,price,stock,status) VALUES(?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, brand);
            ps.setString(2, model);
            ps.setInt(3, year);
            ps.setDouble(4, price);
            ps.setInt(5, stock);
            ps.setString(6, "AVAILABLE");

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Car Added Successfully");
            }

        } catch (Exception e) {

            System.out.println("Add Car Error");
            System.out.println(e.getMessage());
        }
    }
    // =========================
    // VIEW ALL CARS
    // =========================

    public void viewAllCars() {

        try {

            String sql = "SELECT * FROM cars";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n===== ALL CARS =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("car_id") + " | " +
                        rs.getString("brand") + " | " +
                        rs.getString("model") + " | " +
                        rs.getInt("year") + " | " +
                        rs.getDouble("price") + " | Stock: " +
                        rs.getInt("stock") + " | " +
                        rs.getString("status")
                );
            }

        } catch (Exception e) {

            System.out.println("View Car Error");
            System.out.println(e.getMessage());
        }
    }
    // =========================
    // UPDATE CAR
    // =========================

    public void updateCar() {

        try {

            System.out.print("Enter Car ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.print("Brand: ");
            String brand = sc.nextLine();

            System.out.print("Model: ");
            String model = sc.nextLine();

            System.out.print("Year: ");
            int year = sc.nextInt();

            System.out.print("Price: ");
            double price = sc.nextDouble();
            sc.nextLine();

            String sql =
                    "UPDATE cars SET brand=?,model=?,year=?,price=? WHERE car_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, brand);
            ps.setString(2, model);
            ps.setInt(3, year);
            ps.setDouble(4, price);
            ps.setInt(5, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Car Updated");
            } else {
                System.out.println("❌ Car Not Found");
            }

        } catch (Exception e) {

            System.out.println("Update Error");
            System.out.println(e.getMessage());
        }
    }

    // =========================
    // DELETE CAR
    // =========================

    public void deleteCar() {

        try {

            System.out.print("Enter Car ID: ");
            int id = sc.nextInt();

            conn.setAutoCommit(false);

            // DELETE FROM PURCHASES FIRST

            String deletePurchaseSql =
                    "DELETE FROM purchases WHERE car_id=?";

            PreparedStatement purchasePs =
                    conn.prepareStatement(deletePurchaseSql);

            purchasePs.setInt(1, id);

            purchasePs.executeUpdate();

            // DELETE CAR

            String deleteCarSql =
                    "DELETE FROM cars WHERE car_id=?";

            PreparedStatement carPs =
                    conn.prepareStatement(deleteCarSql);

            carPs.setInt(1, id);

            int rows = carPs.executeUpdate();

            if (rows > 0) {

                conn.commit();

                System.out.println("✅ Car Deleted Successfully");

            } else {

                conn.rollback();

                System.out.println("❌ Car Not Found");
            }

            conn.setAutoCommit(true);

        } catch (Exception e) {

            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Delete Error");
            System.out.println(e.getMessage());
        }
    }
    // =========================
    // VIEW AVAILABLE CARS
    // =========================

    public void viewAvailableCars() {

        try {

            String sql =
                    "SELECT * FROM cars WHERE stock > 0";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n===== AVAILABLE CARS =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("car_id") + " | " +
                        rs.getString("brand") + " | " +
                        rs.getString("model") + " | " +
                        rs.getInt("year") + " | " +
                        rs.getDouble("price") + " | Stock: " +
                        rs.getInt("stock")
                );
            }

        } catch (Exception e) {

            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }

    // =========================
    // SEARCH CAR
    // =========================

    public void searchCar() {

        try {

            System.out.print("Enter Brand Name: ");
            String brand = sc.nextLine();

            String sql =
                    "SELECT * FROM cars WHERE brand LIKE ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + brand + "%");

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== SEARCH RESULT =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("car_id") + " | " +
                        rs.getString("brand") + " | " +
                        rs.getString("model") + " | " +
                        rs.getDouble("price") + " | Stock: " +
                        rs.getInt("stock") + " | " +
                        rs.getString("status")
                );
            }

        } catch (Exception e) {

            System.out.println("Search Error");
            System.out.println(e.getMessage());
        }
    }

    // =========================
    // BUY CAR
    // =========================

    public void buyCar() {

        try {

            System.out.print("Enter Car ID to Buy: ");
            int carId = sc.nextInt();

            conn.setAutoCommit(false);

            // CHECK AVAILABILITY

            String checkSql =
                    "SELECT * FROM cars WHERE car_id=? AND stock > 0";

            PreparedStatement checkPs =
                    conn.prepareStatement(checkSql);

            checkPs.setInt(1, carId);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {

                int stock = rs.getInt("stock");

                // INSERT PURCHASE

                String purchaseSql =
                        "INSERT INTO purchases(user_id,car_id) VALUES(?,?)";

                PreparedStatement purchasePs =
                        conn.prepareStatement(purchaseSql);

                purchasePs.setInt(1, currentUserId);
                purchasePs.setInt(2, carId);

                purchasePs.executeUpdate();

                // UPDATE STOCK

                if (stock > 1) {

                    String updateSql =
                            "UPDATE cars SET stock = stock - 1 WHERE car_id=?";

                    PreparedStatement updatePs =
                            conn.prepareStatement(updateSql);

                    updatePs.setInt(1, carId);
                    updatePs.executeUpdate();

                } else {

                    String updateSql =
                            "UPDATE cars SET stock = 0, status='SOLD' WHERE car_id=?";

                    PreparedStatement updatePs =
                            conn.prepareStatement(updateSql);

                    updatePs.setInt(1, carId);
                    updatePs.executeUpdate();
                }

                conn.commit();

                System.out.println("✅ Car Purchased Successfully");

            } else {

                System.out.println("❌ Car Out Of Stock");
            }

            conn.setAutoCommit(true);

        } catch (Exception e) {

            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            System.out.println("Purchase Error");
            System.out.println(e.getMessage());
        }
    }

    // =========================
    // MY PURCHASES
    // =========================

    public void myPurchases() {

        try {

            String sql =
                    "SELECT c.* FROM cars c " +
                    "JOIN purchases p " +
                    "ON c.car_id = p.car_id " +
                    "WHERE p.user_id=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, currentUserId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\n===== MY PURCHASES =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("car_id") + " | " +
                        rs.getString("brand") + " | " +
                        rs.getString("model") + " | " +
                        rs.getDouble("price")
                );
            }

        } catch (Exception e) {

            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }

    // =========================
    // VIEW SOLD CARS
    // =========================

    public void viewSoldCars() {

        try {

            String sql =
                    "SELECT * FROM cars WHERE status='SOLD'";

            Statement st = conn.createStatement();

            ResultSet rs = st.executeQuery(sql);

            System.out.println("\n===== SOLD CARS =====");

            while (rs.next()) {

                System.out.println(
                        rs.getInt("car_id") + " | " +
                        rs.getString("brand") + " | " +
                        rs.getString("model") + " | " +
                        rs.getDouble("price")
                );
            }

        } catch (Exception e) {

            System.out.println("Error");
            System.out.println(e.getMessage());
        }
    }

}
