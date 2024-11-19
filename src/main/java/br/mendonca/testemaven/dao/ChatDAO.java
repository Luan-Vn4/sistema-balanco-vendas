package br.mendonca.testemaven.dao;

import br.mendonca.testemaven.model.entities.Chat;
import br.mendonca.testemaven.model.entities.Message;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ChatDAO {

    public void register(Chat chat) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String query = "INSERT INTO chats (uuid, uuid_user1, uuid_user2) VALUES (?, ?, ?)";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setObject(1, UUID.randomUUID());
        ps.setObject(2, chat.getUuidThisUser());
        ps.setObject(3, chat.getUuidOtherUser());
        ps.execute();
        ps.close();
    }

    /**
     * Retorna chats daquele usuário, mas sem as mensagens inicializadas
     */
    public List<Chat> findChatByUserUUID(UUID uuidUser) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String query = "SELECT * FROM chats WHERE uuid_user1 = ? OR uuid_user2 = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setObject(1, uuidUser);
        ps.setObject(2, uuidUser);
        ResultSet rs = ps.executeQuery();

        List<Chat> chats = new ArrayList<>();
        while (rs.next()) {
            Chat chat = new Chat();
            chat.setUuid((UUID) rs.getObject("uuid"));
            chat.setUuidThisUser(uuidUser);
            chat.setUuidOtherUser(resolveChatOtherUser(rs, uuidUser));
            chats.add(chat);
        }

        rs.close();
        ps.close();

        return chats;
    }

    /**
     * Retorna aquele chat em específico, com as mensagens inicializadas
     * @param uuidThisUser utilizado para saber a partir de qual perspectiva aquele chat será visualizado
     */
    public Optional<Chat> findByUUID(UUID uuid, UUID uuidThisUser) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String query = "SELECT * FROM chats WHERE uuid = ? AND uuid_user1 = ? OR uuid_user2 = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setObject(1, uuid);
        ps.setObject(2, uuidThisUser);
        ps.setObject(3, uuidThisUser);

        ResultSet rs = ps.executeQuery();
        List<Message> messages = unwrapMessageResultSet(rs);

        Chat chat = null;
        if (rs.next()) {
            chat = new Chat();
            chat.setUuid((UUID) rs.getObject("uuid"));
            chat.setUuidThisUser(uuidThisUser);
            chat.setUuidOtherUser(resolveChatOtherUser(rs, uuidThisUser));
            chat.setMessages(findMessagesByChatUUID(uuid));
        }

        rs.close();
        ps.close();

        return Optional.ofNullable(chat);
    }

    private UUID resolveChatOtherUser(ResultSet rs, UUID uuidThisUser) throws SQLException {
        UUID uuidUser1 = (UUID) rs.getObject("uuid_user1");
        UUID uuidUser2 = (UUID) rs.getObject("uuid_user2");

        return uuidUser1.equals(uuidThisUser) ? uuidUser2 : uuidUser1;
    }

    public List<Message> findMessagesByChatUUID(UUID chatUuid) throws ClassNotFoundException, SQLException {
        Connection conn = ConnectionPostgres.getConexao();
        conn.setAutoCommit(true);

        String query = "SELECT * FROM messages WHERE uuid_chat = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet resultSet = ps.executeQuery();
        List<Message> messages = unwrapMessageResultSet(resultSet);

        ps.close();
        resultSet.close();

        return messages;
    }

    public List<Message> unwrapMessageResultSet(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<Message> messages = new ArrayList<>();

        while (rs.next()) {
            Message message = new Message();
            message.setUuid((UUID) rs.getObject("uuid"));
            message.setUuidChat((UUID) rs.getObject("uuid_chat"));
            message.setUuidSenderUser((UUID) rs.getObject("uuid_sender"));
            message.setUuidReceiverUser((UUID) rs.getObject("uuid_receiver"));
            message.setDate(rs.getTimestamp("date_time").toLocalDateTime());
            message.setContent(rs.getString("content"));

            messages.add(message);
        }

        System.out.println("Messages: " + messages);
        return messages;
    }

}
