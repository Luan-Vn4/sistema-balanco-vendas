package br.mendonca.testemaven.model.entities;

import java.time.LocalDateTime;
import java.util.StringJoiner;
import java.util.UUID;

public class Message {

    private UUID uuid;

    private UUID uuidChat;

    private UUID uuidSenderUser;

    private UUID uuidReceiverUser;

    private LocalDateTime date;

    private String content;

    public Message(UUID uuid, UUID uuidChat, UUID uuidSenderUser, UUID uuidReceiverUser,
                   LocalDateTime date, String content) {
        this.uuid = uuid;
        this.uuidChat = uuidChat;
        this.uuidSenderUser = uuidSenderUser;
        this.uuidReceiverUser = uuidReceiverUser;
        this.date = date;
        this.content = content;
    }

    public Message() {}

    public UUID getUuidSenderUser() {
        return uuidSenderUser;
    }

    public void setUuidSenderUser(UUID uuidSenderUser) {
        this.uuidSenderUser = uuidSenderUser;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuidChat() {
        return uuidChat;
    }

    public void setUuidChat(UUID uuidChat) {
        this.uuidChat = uuidChat;
    }

    public UUID getUuidReceiverUser() {
        return uuidReceiverUser;
    }

    public void setUuidReceiverUser(UUID uuidReceiverUser) {
        this.uuidReceiverUser = uuidReceiverUser;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Message.class.getSimpleName() + "[", "]")
            .add("uuid=" + uuid)
            .add("uuidChat=" + uuidChat)
            .add("uuidSenderUser=" + uuidSenderUser)
            .add("uuidReceiverUser=" + uuidReceiverUser)
            .add("date=" + date)
            .add("content='" + content + "'")
            .toString();
    }

}
