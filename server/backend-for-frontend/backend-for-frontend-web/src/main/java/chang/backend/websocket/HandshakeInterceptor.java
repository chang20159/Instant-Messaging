package chang.backend.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by chang on 2017/3/28.
 */
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("before handshake");
        ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
        HttpSession session = servletRequest.getServletRequest().getSession(false);
        Long uid = (Long)session.getAttribute("uid");
        if(uid == null){
            return false;
        }

        //如果uid==null,afterConnectionEstablished不执行，协议转换不成功，报下面的错误
        //websocket.html?uid=1990:56 WebSocket connection to 'ws://localhost:8080/ws?uid=1990' failed: Error during WebSocket handshake: Unexpected response code: 500
        attributes.put("uid", uid);
        return true;

        // 如果这里返回false，浏览器会报下面这个错误
        // WebSocket connection to 'ws://localhost:8080/ws' failed: Error during WebSocket handshake: Unexpected response code: 200
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        System.out.println("after handshake");
    }

}
