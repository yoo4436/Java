package tw.brad.spring08.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import tw.brad.spring08.handler.MyWebSocketHanlder;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        MyWebSocketHanlder hanlder = new MyWebSocketHanlder();
        registry.addHandler(hanlder, new String[] {"/ws"}).setAllowedOrigins("*");
    }

}
