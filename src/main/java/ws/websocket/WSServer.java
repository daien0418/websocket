package ws.websocket;

import java.net.InetSocketAddress;
import java.util.Collection;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

public class WSServer extends WebSocketServer{

	public WSServer(InetSocketAddress address) {
		super(address);
	}

	@Override
	public void onClose(WebSocket webSocket, int arg1, String arg2, boolean arg3) {
		String address = webSocket.getRemoteSocketAddress().getAddress().getHostAddress();
        String message = String.format("(%s) <关闭连接！>", address);
        System.out.println(message);
	}

	@Override
	public void onError(WebSocket webSocket, Exception e) {
		if (null != webSocket) {
            webSocket.close(0);
        }
        e.printStackTrace();
	}

	@Override
	public void onMessage(WebSocket webSocket, String s) {
		String address = webSocket.getRemoteSocketAddress().getAddress().getHostAddress();
        String message = String.format("(%s) %s", address, s);
        sendToAll(message);
        System.out.println(message);
	}

	@Override
	public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
		String address = webSocket.getRemoteSocketAddress().getAddress().getHostAddress();
		String message = String.format("(%s) <连接到服务器！>", address);
		System.out.println(message);
	}

	public void sendToAll(String message) {
        // 获取所有连接的客户端
        Collection<WebSocket> connections = connections();
        //将消息发送给每一个客户端
        for (WebSocket client : connections) {
            client.send(message);
        }
    }


}
