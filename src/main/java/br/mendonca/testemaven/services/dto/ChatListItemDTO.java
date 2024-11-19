package br.mendonca.testemaven.services.dto;

import br.mendonca.testemaven.model.entities.Chat;
import java.util.UUID;

public class ChatListItemDTO {

    private UUID uuid;

    private UUID uuidThisUser;

    private String thisUserName;

    private UUID uuidOtherUser;

    private String otherUserName;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuidThisUser() {
        return uuidThisUser;
    }

    public void setUuidThisUser(UUID uuidThisUser) {
        this.uuidThisUser = uuidThisUser;
    }

    public String getThisUserName() {
        return thisUserName;
    }

    public void setThisUserName(String thisUserName) {
        this.thisUserName = thisUserName;
    }

    public UUID getUuidOtherUser() {
        return uuidOtherUser;
    }

    public void setUuidOtherUser(UUID uuidOtherUser) {
        this.uuidOtherUser = uuidOtherUser;
    }

    public String getOtherUserName() {
        return otherUserName;
    }

    public void setOtherUserName(String otherUserName) {
        this.otherUserName = otherUserName;
    }

    public static ChatListItemDTO from(Chat chat, String thisUserName, String otherUserName) {
        ChatListItemDTO dto = new ChatListItemDTO();

        dto.setUuid(chat.getUuid());
        dto.setUuidThisUser(chat.getUuidThisUser());
        dto.setUuidOtherUser(chat.getUuidOtherUser());
        dto.setThisUserName(thisUserName);
        dto.setOtherUserName(otherUserName);

        return dto;
    }

}
