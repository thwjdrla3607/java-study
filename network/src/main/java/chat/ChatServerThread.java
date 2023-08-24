package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ChatServerThread extends Thread {
	private String nickname;
	private Socket socket;
	private List<PrintWriter> pw;
	
	public ChatServerThread(Socket socket, List<PrintWriter> pw) {
		this.socket = socket;
		this.pw = pw;
	}
	
	try {
		ServerSocket serverSocket = new ServerSocket();
		
		String hostAddress = 
	}
	
	// 1. 서버 소켓 생성
	 ServerSocket serverSocket = new ServerSocket();
	 
	// 2. 바인딩
	String hostAddress = InetAddress.getLocalHost().getHostAddress();
	serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
	log("연결 기다림" + hostAddress + ":" + PORT);
	
	// 3. 요청 대기
	try {
		while(true) {
			Socket socket = serverSocket.accept();
			new ChatServerTread(socket).start();
	} catch(IOException e) {
		e.printStackTrace();
	}
	
}