package study.websocket.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import study.websocket.jwt.JwtUtil;

@RequiredArgsConstructor
@Component
public class WebSocketInterceptor implements ChannelInterceptor{

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {


        return message;
    }
}
