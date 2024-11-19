package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Chat;
import br.mendonca.testemaven.model.entities.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatDTO {

    private UUID uuid;

    private String thisUserName;

    private UUID uuidThisUser;

    private String otherUserName;

    private UUID uuidOtherUser;

    private List<Message> messages = new ArrayList<>();

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getThisUserName() {
        return thisUserName;
    }

    public void setThisUserName(String thisUserName) {
        this.thisUserName = thisUserName;
    }

    public UUID getUuidThisUser() {
        return uuidThisUser;
    }

    public void setUuidThisUser(UUID uuidThisUser) {
        this.uuidThisUser = uuidThisUser;
    }

    public String getOtherUserName() {
        return otherUserName;
    }

    public void setOtherUserName(String otherUserName) {
        this.otherUserName = otherUserName;
    }

    public UUID getUuidOtherUser() {
        return uuidOtherUser;
    }

    public void setUuidOtherUser(UUID uuidOtherUser) {
        this.uuidOtherUser = uuidOtherUser;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public static ChatDTO from(Chat chat, String thisUserName, String otherUserName) {
        ChatDTO chatDTO = new ChatDTO();

        chatDTO.setUuid(chat.getUuid());
        chatDTO.setUuidThisUser(chat.getUuidThisUser());
        chatDTO.setUuidOtherUser(chat.getUuidOtherUser());
        chatDTO.setThisUserName(thisUserName);
        chatDTO.setOtherUserName(otherUserName);
        chatDTO.setMessages(chat.getMessages());

        return chatDTO;
    }

}
