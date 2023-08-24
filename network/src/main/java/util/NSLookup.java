package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		while(true) {
			System.out.print(">> ");
			String line = s.nextLine();
						
			if("quit".equals(line)) {
				break;
			}
			
			try {
				InetAddress[] addresses = InetAddress.getAllByName(line);
				for(InetAddress address : addresses) {
					System.out.println(line + " : " + address.getHostAddress());
				}
			} catch (UnknownHostException e) {
				System.out.println("잘못된 주소");
			}
		}
	}
}
