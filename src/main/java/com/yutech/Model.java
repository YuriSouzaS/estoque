package com.yutech;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DatabaseMetaData;
import java.sql.Statement;
import java.sql.ResultSet;
// import java.sql.PreparedStatement;

public class Model {
    public static String url = "jdbc:sqlite:MyEstoque.db";

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("Nome do driver " + meta.getDriverName());
                System.out.println("Um novo banco de dados foi criado.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } // final

    public static void createNewTable() {
    
        // Query SQL 
        String sql = "CREATE TABLE IF NOT EXISTS Produtos (\n"
                + "	id	INTEGER NOT NULL,\n"
                + " name	TEXT NOT NULL,\n"
                + "	brand	TEXT NOT NULL,\n"
                + " price	INTEGER NOT NULL,\n"
                + " amount	INTEGER NOT NULL,\n"
                + " PRIMARY KEY(id AUTOINCREMENT));";
        
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // criando nova tabela
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } // final

    public void selectAll(){
        String sql = "SELECT * FROM Produtos";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop com resultados
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  "\t" + 
                                   rs.getString("name") + "\t" +
                                   rs.getString("brand") + "\t" +
                                   rs.getString("price") + "\t" +
                                   rs.getString("amount"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } // final

     public void getWithId(Integer id){
               String sql = "SELECT name, price, brand "
                          + "FROM Produtos WHERE id = ?";
        
        try (Connection conn = this.connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
            
            pstmt.setInt(1,id);
            ResultSet rs  = pstmt.executeQuery();
            
            // loop de resultados
            while (rs.next()) {
                System.out.println(rs.getString("name") +  "\t" + 
                                   rs.getString("price") + "\t" +
                                   rs.getString("brand"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    } // final

    public void insert(String name, String brand, float price, Integer amount) {
        String sql = "INSERT INTO Produtos(name,brand, price, amount) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, brand);
            pstmt.setFloat(3, price);
            pstmt.setInt(4, amount);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }// final

    public void update(int id, String name, String brand, float price, Integer amount) {
        String sql = "UPDATE Produtos SET name = ? , "
                + "brand = ?, "
                + "price = ?, "
                + "amount = ? "
                + "WHERE id = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set do update
            pstmt.setString(1, name);
            pstmt.setString(2, brand);
            pstmt.setFloat(3, price);
            pstmt.setInt(4, amount);
            pstmt.setInt(5, id);
            // update dos dados
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }// final

    public void delete(int id) {
        String sql = "DELETE FROM Produtos WHERE id = ?";

        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Model app = new Model();
        // app.selectAll(); 
        app.delete(2);      
    }
}
