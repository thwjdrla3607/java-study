package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class PhoneList02 {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("phone.txt");

		if(!file.exists()) {
			System.out.println("File Not Found");
			return;
		}

		System.out.println("===== 파일정보 =====");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.length() + "bytes");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(file.lastModified())));

		System.out.println("===== 전화번호 =====");
		Scanner s = new Scanner(file);
		
		while(s.hasNextLine()) {
			String name = s.next();
			String phone1 = s.next();
			String phone2 = s.next();
			String phone3 = s.next();
			
			System.out.println(name + ":" + phone1 + "-" + phone2 + "-" + phone3);
			
		}
		s.close();

	}

}
