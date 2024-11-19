package br.mendonca.testemaven.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.mendonca.testemaven.dao.UserDAO;
import br.mendonca.testemaven.model.entities.User;
import br.mendonca.testemaven.services.dto.UserDTO;

public class UserService {
	UserDAO userDao = new UserDAO();

	public void register(String name, String email, String password, int idade, boolean status) throws ClassNotFoundException, SQLException {
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		user.setIdade(idade);
		user.setStatus(status);

		userDao.register(user);
	}

	public List<UserDTO> listAllUsers() throws ClassNotFoundException, SQLException {
		ArrayList<UserDTO> resp = new ArrayList<UserDTO>();

		List<User> lista = userDao.listAllUser();

		for (User user : lista) {
			resp.add(UserDTO.userMapper(user));
		}

		return resp;
	}

	public List<UserDTO> searchUsersByName(String name) throws ClassNotFoundException, SQLException {
		ArrayList<UserDTO> resp = new ArrayList<>();

		List<User> users = userDao.searchByName(name);

		for (User user : users) {
			resp.add(UserDTO.userMapper(user));
		}

		return resp;
	}

	public void followUser(String followerEmail, String followedEmail) throws ClassNotFoundException, SQLException {
		userDao.followUser(followerEmail, followedEmail);
	}

	public void unfollowUser(String followerEmail, String followedEmail) throws ClassNotFoundException, SQLException {
		userDao.unfollowUser(followerEmail, followedEmail);
	}

	public List<UserDTO> getFollowedUsers(String followerEmail) throws ClassNotFoundException, SQLException {
		List<User> followedUsers = userDao.getFollowedUsers(followerEmail);
		return followedUsers.stream()
				.map(user -> new UserDTO(null, user.getName(), user.getEmail()))
				.collect(Collectors.toList());
	}

	public List<UserDTO> getFollowers(String followedEmail) throws ClassNotFoundException, SQLException {
		List<User> followers = userDao.getFollowers(followedEmail);
		return followers.stream()
				.map(user -> new UserDTO(null, user.getName(), user.getEmail()))
				.collect(Collectors.toList());
	}
}
