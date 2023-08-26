package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_IP = "127.0.0.1";
	
	public static void main(String[] args) {
		Socket socket = null;
		Scanner s = null;
		
		try {
			// 1. 키보드 연결
			s = new Scanner(System.in);
			
			// 2. socket 생성
			socket = new Socket();
			
			// 3. 연결
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));
			log("connected");
			
			// 4. reader/writer 생성
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			
			// 5. join 프로토콜
			System.out.println("채팅 프로그램입니다. 사용하실 닉네임을 입력하세요.");
			System.out.print("닉네임>> ");
			String nickname = s.nextLine();
			
			if(nickname.equals("")) {
				nickname = "익명" ;
			}
			
			pw.println("join: " + nickname);
//			pw.flush();
			
			//ack 받기!!
			String ack = br.readLine();
			if("join: OK".equals(ack)) {
				System.out.println(nickname + "님이 입장하였습니다.");
			}
			
			// 6. ChatClientReceiveThread 시작
			new ChatClientThread(socket).start();
			
			// 7. 키보드 입력 처리
			while(true) {
				System.out.print(">> ");
				String input = s.nextLine();
				
				if(!s.hasNextLine()) {
					// 공백처리
					continue;
				}
				
				if("quit".equals(input)) {
					// 8. quit 프로토콜 처리
					pw.println("quit");
					break;
				} else {
					// 9. 메시지 처리
					pw.println("message:" + input);
				}
			}
			
		} catch (ConnectException e) {
			log("서버[" + SERVER_IP + ":" + ChatServer.PORT + "]에 연결할 수 없습니다.");	
		} catch (Exception e) {
			log(e + " 프로그램 종료");	
		} finally {
			try {
				if(s != null) {
					s.close();
				}
				if(socket != null && socket.isClosed() == false){
					socket.close();
				}
			}catch(IOException e) {
				log(e + " 프로그램 종료");	
			}
		}
	}

	public static void log(String message) {
		System.out.println("[Chat Client] " + message);		
	}
}
