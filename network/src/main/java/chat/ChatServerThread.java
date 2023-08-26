package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

public class ChatServerThread extends Thread {
	private String nickname;
	private Socket socket;
	private List<PrintWriter> listPrintWriter;
	
	public ChatServerThread(Socket socket, List<PrintWriter> listPrintWriter) {
		this.socket = socket;
		this.listPrintWriter = listPrintWriter;
	}
	
	@Override
	public void run() {
		BufferedReader br = null;
		PrintWriter pw = null;
		
		try {
			InetSocketAddress remoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			ChatServer.log("connected by client[" + remoteSocketAddress.getAddress().getHostAddress() + ":" + remoteSocketAddress.getPort() + "]" );
			
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
			
			while(true) {
				String request = br.readLine();
				if(request == null) {
					ChatServer.log("클라이언트로부터 연결 끊김");
					doQuit(pw);
					break;
				}
				
				// 프로토콜 분석
				String[] tokens = request.split(":");
				if("join".equals(tokens[0])) {
					doJoin(tokens[1], pw);
				} else if("message".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if("quit".equals(tokens[0])) {
					doQuit(pw);
				} else {
					ChatServer.log("에러: 알 수 없는 요청(" + tokens[0] + ")");
				}
			}
		} catch (SocketException e) {
			doQuit(pw);
			ChatServer.log( "abnormal closed by client" );
		} catch (IOException e) {
			doQuit(pw);
			ChatServer.log( "error:" + e );
		} finally {
			try {
				if( socket != null && socket.isClosed() == false ) {
					socket.close();
				}
			}catch( IOException e ) {
				ChatServer.log( "error:" + e );
			}
		}
	}
	

	//quit
	private void doQuit(PrintWriter printwriter) {
		removePrintWriter(printwriter);
		
		if(nickname != null) {
			String data = nickname + "님이 퇴장 하였습니다.";
			broadcast(data);
		}
	}
	
	//message
	private void doMessage(String msg) {
		broadcast(nickname + ":" + msg);
	}
	
	//join
	private void doJoin(String nickname, PrintWriter printwriter) {
		this.nickname = nickname;
		
		String data = nickname + "님이 입장하였습니다.";
		broadcast(data);
		
		// writer pool에 저장
		addPrintWriter(printwriter);
		
		// ack
		printwriter.println("join: OK");
//		printwriter.flush();
	}
	
	
	// printWriter list에서 printWriter 지우기
	private void removePrintWriter(PrintWriter printwriter) {
		synchronized(listPrintWriter) {
			listPrintWriter.remove(printwriter);
		}
	}
	
	// printWriter list에 클라이언트 printWriter 추가
	private void addPrintWriter(PrintWriter printwriter) {
		synchronized(listPrintWriter) {
		listPrintWriter.add(printwriter);
		}
	}
	
	// 브로드캐스트 메시지를 모든 클라이언트에게 보냄
    private void broadcast(String msg) {
        synchronized(listPrintWriter) {
        	for (PrintWriter printWriter : listPrintWriter) {
        		printWriter.println(msg);
//        		printWriter.flush();
        	}
        }
    }
}