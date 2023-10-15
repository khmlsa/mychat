package com.tr.endpoint;

import com.alibaba.fastjson.JSON;
import com.tr.config.WsConfig;
import com.tr.pojo.WsMessage;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;

@Component
@ServerEndpoint(value = "/chat",configurator = WsConfig.class)
public class ChatEndPoint {
    private static final HashMap<String, Session> onlineUsers = new HashMap<>();
    @OnOpen
    public void onOpen(Session session, EndpointConfig config){
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        onlineUsers.put((String) httpSession.getAttribute("user"),session);
        //广播
        WsMessage wsMessage = createWsMessage(onlineUsers.keySet());
        System.out.println(wsMessage);
        broadCast(wsMessage);
        System.out.println("连接成功");
    }

    private WsMessage createWsMessage(Object data) {
        WsMessage wsMessage = new WsMessage();
        wsMessage.setFrom(null);
        wsMessage.setSys(true);
        wsMessage.setTo(null);
        wsMessage.setMsg(data);
        return wsMessage;
    }

    private void broadCast(WsMessage message) {
        for (Map.Entry<String, Session> entry : onlineUsers.entrySet()) {
            try{
                String jsonString = JSON.toJSONString(message);
                entry.getValue().getBasicRemote().sendText(jsonString);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    @OnMessage
    public void onMessage(String str){

    }
}
