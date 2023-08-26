package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class ChatClientThread extends Thread {
	private Socket socket;
	
	public ChatClientThread(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			
			while(true) {
				String msg = br.readLine();
				if (msg == null) {
					break;
		        }
		        System.out.println(msg);
			}
		} catch(SocketException e){
			ChatClient.log("error: " + e);	
		} catch(IOException e){
			ChatClient.log(e + " 프로그램 종료");	
		} finally {
			System.exit(0);
		}
	}
}