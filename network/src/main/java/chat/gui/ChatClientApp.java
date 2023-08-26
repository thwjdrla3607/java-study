package chat.gui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientApp {
	public static final String SERVER_IP = "127.0.0.1"; 
	public static final int PORT = 9999;

	public static void main(String[] args) {
		String name = null;
		Scanner scanner = null;
		Socket socket = null;
		
		try {
			
			scanner = new Scanner(System.in);
						
			while( true ) {
				System.out.println("대화명을 입력하세요.");
				System.out.print("> ");
				name = scanner.nextLine();
				
				if (name.isEmpty() == false ) { 
					break;
				}
				
				System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
			}
						
			// 1. create socket 
			socket = new Socket();
			
			// 2. connect server
			socket.connect(new InetSocketAddress(SERVER_IP, PORT));
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true); // auto-flush
			
			// 3. join protocol 진행 
			pw.println("join:" + name); 
			
			String ack = br.readLine(); 
			if("join:ok".equals(ack)) { 
				new ChatWindow(name, socket).show();
			}
		} catch (ConnectException e) {
			log("서버[" + SERVER_IP + ":" + PORT + "]에 연결할 수 없습니다.");
		} catch (Exception e) {
			log("error: " + e);
		} finally {
			// 자원 정리 
			if(scanner != null) {
				scanner.close();
			}
		}	
	}
	
	public static void log(String msg) {
		System.out.println("[Chat Client] " + msg);
	}

}