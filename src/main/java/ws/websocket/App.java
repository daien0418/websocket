package ws.websocket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * Hello world!
 *
 */
public class App {

	private static int PORT = 2018;

	public static void main(String[] args) {

		WSServer socketServer = new WSServer(new InetSocketAddress(PORT));
		socketServer.start();

		try {
			String ip = InetAddress.getLocalHost().getHostAddress();
			int port = socketServer.getPort();
			System.out.println(String.format("服务已启动: %s:%d", ip, port));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(in);

		while (true) {
			try {
				String msg = reader.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
