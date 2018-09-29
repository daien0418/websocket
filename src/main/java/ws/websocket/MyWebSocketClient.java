package ws.websocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.handshake.ServerHandshake;

public class MyWebSocketClient {

	public static void main(String args[]) {
		WebSocketClient mSocketClient = null;
		try {
			mSocketClient = new WebSocketClient(new URI("ws://localhost:2018/"), new Draft_10()) {
				@Override
				public void onOpen(ServerHandshake handshakedata) {
					System.out.println("客户端成功连接到服务器！");
				}

				@Override
				public void onMessage(String message) {
					System.out.println("服务器发来的消息："+message);
				}

				@Override
				public void onClose(int code, String reason, boolean remote) {

				}

				@Override
				public void onError(Exception ex) {
					System.out.println("连接出错！");
				}
			};
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		mSocketClient.connect();

		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(in);

		while (true) {
			try {
				String msg = reader.readLine();
				if(msg.trim().equals("close")){
					mSocketClient.close();
				}else{
					mSocketClient.send(msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
