package com.daw.swapp.repository.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.daw.swapp.model.users.User;

@Repository
public class UsersRepository {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=swapp;encrypt=true;trustServerCertificate=true";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "PasswordO1.@";

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();

        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Users");
            while (rs.next()) {
                String name = rs.getString("Name");
                String surname = rs.getString("Surname");
                int id = rs.getInt("Id");

                users.add(new User(name, surname, id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public int addUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (name, surname) VALUES (?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user.name);
            stmt.setString(2, user.surname);

            int affectedRows = stmt.executeUpdate();
            return affectedRows;

        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            throw e;
        }
    }

    public boolean deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM Users WHERE id = ?";

        try (Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        }
    }
}
