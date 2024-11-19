package br.mendonca.testemaven.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.mendonca.testemaven.model.entities.User;

public class UserDAO {

	public void register(User user) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO users (name, email, password, idade, status) values (?,?,?,?,?)");
		ps.setString(1, user.getName());
		ps.setString(2, user.getEmail());
		ps.setString(3, user.getPassword());
		ps.setInt(4, user.getIdade());
		ps.setBoolean(5, user.isStatus());
		ps.execute();
		ps.close();
	}
	
	public List<User> listAllUser() throws ClassNotFoundException, SQLException {
		ArrayList<User> lista = new ArrayList<User>();
		
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM users");
		
		while (rs.next()) {
			User user = new User();
			user.setUuid((UUID) rs.getObject("uuid"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			
			lista.add(user);
		}
		
		rs.close();
		
		return lista;
	}

	public User search(String email, String password) throws ClassNotFoundException, SQLException {
		User user = null;
		
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);
		
		// Apesar de qualquer SQL funcionar com Statement, a abordagem de usar Prepared Statement evita SQL Injection.
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE email = ? AND password = ?");
		ps.setString(1, email);
		ps.setString(2, password);
		System.out.println(ps); // Exibe no console do Docker a query j montada.
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			
			user = new User();
			user.setUuid((UUID) rs.getObject("uuid"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
		}
		
		rs.close();
		
		return user;
	}

	// TODO: No testado
	public List<User> searchByName(String name) throws ClassNotFoundException, SQLException {
		ArrayList<User> lista = new ArrayList<User>();
		
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		// Apesar de qualquer SQL funcionar com Statement, a abordagem de usar Prepared Statement evita SQL Injection.
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM users WHERE "+
						"LOWER(name) LIKE ('%' || LOWER(?) || '%')");
		ps.setString(1, name);
		
		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			User user = new User();
			user.setUuid((UUID) rs.getObject("uuid"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			user.setIdade(rs.getInt("idade"));
			user.setStatus(rs.getBoolean("status"));
			
			lista.add(user);
		}
		
		rs.close();
		
		return lista;
	}

	public void followUser(String followerEmail, String followedEmail) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO user_followers (follower_email, followed_email) VALUES (?, ?)"
		);
		ps.setString(1, followerEmail);
		ps.setString(2, followedEmail);
		ps.execute();
		ps.close();
	}

	public void unfollowUser(String followerEmail, String followedEmail) throws ClassNotFoundException, SQLException {
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		PreparedStatement ps = conn.prepareStatement(
				"DELETE FROM user_followers WHERE follower_email = ? AND followed_email = ?"
		);
		ps.setString(1, followerEmail);
		ps.setString(2, followedEmail);
		ps.execute();
		ps.close();
	}

	public List<User> getFollowedUsers(String followerEmail) throws ClassNotFoundException, SQLException {
		List<User> followedUsers = new ArrayList<>();
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		PreparedStatement ps = conn.prepareStatement(
				"SELECT u.* FROM users u "
						+ "INNER JOIN user_followers uf ON u.email = uf.followed_email "
						+ "WHERE uf.follower_email = ?"
		);
		ps.setString(1, followerEmail);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			User user = new User();
			user.setUuid((UUID) rs.getObject("uuid"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			followedUsers.add(user);
		}

		rs.close();
		ps.close();
		return followedUsers;
	}

	public List<User> getFollowers(String followedEmail) throws ClassNotFoundException, SQLException {
		List<User> followers = new ArrayList<>();
		Connection conn = ConnectionPostgres.getConexao();
		conn.setAutoCommit(true);

		PreparedStatement ps = conn.prepareStatement(
				"SELECT u.* FROM users u "
						+ "INNER JOIN user_followers uf ON u.email = uf.follower_email "
						+ "WHERE uf.followed_email = ?"
		);
		ps.setString(1, followedEmail);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			User user = new User();
			user.setUuid((UUID) rs.getObject("uuid"));
			user.setName(rs.getString("name"));
			user.setEmail(rs.getString("email"));
			user.setPassword(rs.getString("password"));
			followers.add(user);
		}

		rs.close();
		ps.close();
		return followers;
	}


}
