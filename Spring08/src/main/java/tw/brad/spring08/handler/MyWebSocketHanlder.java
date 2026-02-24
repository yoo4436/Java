package tw.brad.spring08.handler;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;


public class MyWebSocketHanlder extends TextWebSocketHandler {

    private static final Set<WebSocketSession> sessions = new HashSet<>();
    // private static final Set<WebSocketSession> sessions2 = new CopyOnWriteArraySet<>();

    public MyWebSocketHanlder() {
        System.out.println("MyWebSocketHanlder");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.add(session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.remove(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for (WebSocketSession s : sessions) {
            if (s.isOpen()) {
                s.sendMessage(message);
            }
        }
    }
    
}
