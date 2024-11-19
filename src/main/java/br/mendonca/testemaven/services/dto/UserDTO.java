package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.User;

import java.util.UUID;

public class UserDTO {

	private UUID uuid;
	private String name;
	private String email;
	private int idade;
	private boolean status;

	
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

	public UserDTO() {
	}

	public UserDTO(UUID uuid, String name, String email) {
		this.uuid = uuid;
		this.name = name;
		this.email = email;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public static UserDTO userMapper(User user) {
		UserDTO dto = new UserDTO();
		dto.setUuid(user.getUuid());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setIdade(user.getIdade());
		dto.setStatus(user.isStatus());
		
		return dto;
	}
}
