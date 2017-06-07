package chang.backend.websocket;

import chang.backend.constant.Constants;
import chang.backend.dto.MessageDTO;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * Created by chang on 2017/3/27.
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private static final Map<Long, WebSocketSession> userSocketSessionMap;

    static {
        userSocketSessionMap = Maps.newHashMap();
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("websocket:" + session.getId() + "已连接");
        //建立user与会话的关系，发送消息时指定user,最终需要指定session
        Long userId = (Long)session.getAttributes().get(Constants.UID);
        if(userSocketSessionMap.get(userId) == null){
            userSocketSessionMap.put(userId,session);
        }
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        if(webSocketMessage.getPayloadLength() == 0) return;
        MessageDTO messageDTO = buildMessageDTO(webSocketSession, webSocketMessage);
        sendMessageToUser(messageDTO.getTo().getId(), new TextMessage(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(messageDTO)));
    }

    private MessageDTO buildMessageDTO(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) {
        MessageDTO messageDTO = new Gson().fromJson(webSocketMessage.getPayload().toString(), MessageDTO.class);
        messageDTO.setSendTime(new Date());
        Map<String, Object> uidMap = webSocketSession.getAttributes();
        if(uidMap != null){
            Long fromId = (Long)uidMap.get("uid");
            if(fromId.equals(messageDTO.getFrom().getId()))
            messageDTO.setFrom(messageDTO.getFrom());
        }
        messageDTO.setTo(Constants.USERS.get(messageDTO.getTo().getId()));
        return messageDTO;
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable throwable) throws Exception {
        System.out.println("websocket:" + session.getId() + "传输错误");
        if(session.isOpen()){
            session.close();
        }
        removeSession(session);
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("websocket:" + session.getId() + "已经关闭");
        removeSession(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 发送消息
     * @param uid 用户id
     * @param textMessage 消息内容
     * @throws IOException
     */
    public void sendMessageToUser(Long uid,TextMessage textMessage) throws IOException {
        WebSocketSession session = userSocketSessionMap.get(uid);
        if(session != null && session.isOpen()){
            session.sendMessage(textMessage);
        }
    }

    private void removeSession(WebSocketSession session) {
        for(Map.Entry<Long, WebSocketSession> entry : userSocketSessionMap.entrySet()){
            if(entry.getValue().getId().equals(session.getId())){
                userSocketSessionMap.remove(entry.getKey());
                System.out.println("websocket会话已移除，用户ID : " + entry.getKey());
                break;
            }
        }
    }
}
