package main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	public boolean save(User user) {
		String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
		try (Connection connection = DBConfig.getConnection()) {
			var stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			int affected = stmt.executeUpdate();
			return affected == 1;
		} catch (SQLException e) {
			return false;
		}
	}

	public List<User> findAll() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM user";
		try (Connection connection = DBConfig.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				User user = new User(username, password);
				users.add(user);
			}
		} catch (SQLException e) {
			System.out.printf("ERROR: %s.\n", e.getMessage());
		}
		return users;
	}

	public User find(int id) {
		String sql = "SELECT * FROM user WHERE id = ?";
		try (Connection connection = DBConfig.getConnection()) {
			var stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String username = rs.getString("username");
				String password = rs.getString("password");
				return new User(username, password);
			}
		} catch (SQLException e) {
			System.out.printf("ERROR: %s.", e.getMessage());
			System.exit(0);
		}
		return null;
	}

	public User find(String username) {
		String sql = "SELECT * FROM user WHERE username = ?";
		try (Connection connection = DBConfig.getConnection()) {
			var stmt = connection.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("username");
				String password = rs.getString("password");
				return new User(name, password);
			}
		} catch (SQLException e) {
			System.out.printf("ERROR: %s.", e.getMessage());
			System.exit(0);
		}
		return null;
	}
}
