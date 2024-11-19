package br.mendonca.testemaven.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

public class Chat {

    // ATRIBUTOS
    private UUID uuid;

    private UUID uuidThisUser;

    private UUID uuidOtherUser;

    private List<Message> messages = new ArrayList<>();


    // MÉTODOS DE ACESSO
    public Chat(UUID uuidThisUser, UUID uuidOtherUser, List<Message> messages) {
        this.uuidThisUser = uuidThisUser;
        this.uuidOtherUser = uuidOtherUser;
        this.messages = new ArrayList<>(messages);
    }

    public Chat() {}

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


    // MÉTODOS
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    public void removeMessage(Message message) {}

    @Override
    public String toString() {
        return new StringJoiner(", ", Chat.class.getSimpleName() + "[", "]")
            .add("uuid=" + uuid)
            .add("uuidThisUser=" + uuidThisUser)
            .add("uuidOtherUser=" + uuidOtherUser)
            .add("messages=" + messages)
            .toString();
    }

}
