package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	public static final int PORT = 9999;
	
	public static void main(String[] args) {
		List<PrintWriter> listPrintWriter = new ArrayList<PrintWriter>();
		ServerSocket serverSocket = null;
		
		try {
			
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();
//			serverSocket.setReuseAddress(true);
			
			// 2. 바인딩
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress("127.0.0.1", PORT), 10);
			log("연결 기다림" + hostAddress + ":" + PORT);
			
			// 3. 요청 대기
			while(true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, listPrintWriter).start();
			}
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 자원 정리 
			try {
				if(serverSocket != null && !serverSocket.isClosed()) {
					serverSocket.close();
				}
			} catch (IOException e) {
				log("error: " + e);
			}
		}
	}

	public static void log(String message) {
		System.out.println("[Chat Server] " + message);
	}
}
