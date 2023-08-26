package chat.gui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	private Socket socket;
	private String name;
	
	private BufferedReader br;
	private PrintWriter pw;
	
	public ChatWindow(String name, Socket socket) {
		this.socket = socket;
		this.name = name;
		
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage();
			}
		});
		
		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack();
		
		// IOStream 받아오기
		try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true); // auto-flush
			
			// ChatClientThread 생성하고 실행
			new ChatClientThread().start();
		} catch(IOException e) {
			ChatClientApp.log("error: " + e);
		}
	}
	
	private void finish() {
		try {
			// quit 프로토콜 구현 
			// exit java(JVM) 
			if(socket != null && !socket.isClosed()) {
				socket.close();
			}
			System.out.println("채팅창 종료");
			System.exit(0);
		} catch (IOException e) {
			ChatClientApp.log("error : " + e);
		}
	}
	
	private void sendMessage() {
		String message = textField.getText();
		if(message != null) {
			pw.println("message:" + message);
			System.out.println("메시지 : " + message);
		}
		System.out.println("메세지를 보내는 프로코톨 구현:" + message);
		
		textField.setText("");
		textField.requestFocus();
		
		// ChatClientThread 에서 서버로 부터 받은 메세지가 있다고 치고~~
		updateTextArea("마이콜: " + message);
	}
	
	private void updateTextArea(String message) {
		if(message.contains(":")) { 
			String[] tokens = message.split(":");
			
			String nickname = tokens[0];
			String content = tokens[1];
			
			
			if(nickname.equals(name)) { 
				message = "[나] " + content; 
				message = "\t\t   " + message;
			} else { 
				message = "[" + nickname + "] " + content;
			}
			
			textArea.append(message);
			textArea.append("\n");
		} else { 
			textArea.append(message);
			textArea.append("\n\n");
		}
	}
	
	private class ChatClientThread extends Thread {
		@Override
		public void run() {
			try {
				while(true) {
					String data = br.readLine(); 
					if(data == null) { 
						break;
					}
						
					Thread.sleep(1);  
					updateTextArea(data);
				}
			} catch (InterruptedException e) { 
			} catch (SocketException e) {
				ChatClientApp.log("error: " + e);
			} catch (IOException e) {
				ChatClientApp.log("error: " + e);
			} finally {
				finish();  
			}
		}
	}
}