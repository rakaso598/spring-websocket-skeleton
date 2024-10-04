package com.example.demo.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class YourWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 받은 메시지를 로그로 출력
        System.out.println("Received message: " + message.getPayload());

        // 클라이언트에게 같은 메시지를 되돌려 보냄
        session.sendMessage(new TextMessage("Echo: " + message.getPayload()));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 연결이 성립된 후, 클라이언트에게 환영 메시지 전송
        System.out.println("Connection established: " + session.getId());
        session.sendMessage(new TextMessage("Welcome! You are connected with session ID: " + session.getId()));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 연결이 종료된 후 처리
        System.out.println("Connection closed: " + session.getId() + ", Status: " + status);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // 전송 오류 처리
        System.err.println("Transport error: " + exception.getMessage());
    }
}

