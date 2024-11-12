package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.User;

import java.util.UUID;

public class UserDTO {

	private UUID uuid;
	private String name;
	private String email;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public static UserDTO userMapper(User user) {
		UserDTO dto = new UserDTO();
		dto.setUuid(user.getUuid());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		
		return dto;
	}
}
