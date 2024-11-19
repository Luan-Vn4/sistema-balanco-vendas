package br.mendonca.testemaven.services;

import br.mendonca.testemaven.dao.ChatDAO;
import br.mendonca.testemaven.dao.UserDAO;
import br.mendonca.testemaven.model.entities.Chat;
import br.mendonca.testemaven.services.dto.ChatDTO;
import br.mendonca.testemaven.services.dto.ChatListItemDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ChatService {

    private final ChatDAO chatDAO = new ChatDAO();

    private final UserDAO userDAO = new UserDAO();

    public void create(Chat chat) throws SQLException, ClassNotFoundException {
        chatDAO.register(chat);
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public List<ChatListItemDTO> findChatListForUser(UUID userUuid)
                                                     throws SQLException, ClassNotFoundException {
        List<Chat> chats = chatDAO.findChatByUserUUID(userUuid);

        List<ChatListItemDTO> dtos = new ArrayList<>();
        for (Chat chat : chats) {
            String thisUsername = userDAO.findByUUID(chat.getUuidThisUser()).get().getName();
            String otherUsername = userDAO.findByUUID(chat.getUuidOtherUser()).get().getName();
            var dto = ChatListItemDTO.from(chat, thisUsername, otherUsername);
            dtos.add(dto);
        }

        return dtos;
    }

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    public ChatDTO findChat(UUID uuid, UUID uuidThisUser) throws SQLException, ClassNotFoundException {
        Optional<Chat> chat = chatDAO.findByUUID(uuid, uuidThisUser);

        if (chat.isEmpty()) throw new RuntimeException("Não foi possível encontrar o chat de UUID " + uuid
            + " para o usuário de UUID: " + uuidThisUser);

        String thisUsername = userDAO.findByUUID(chat.get().getUuidThisUser()).get().getName();
        String otherUsername = userDAO.findByUUID(chat.get().getUuidOtherUser()).get().getName();

        return ChatDTO.from(chat.get(), thisUsername, otherUsername);
    }

}
