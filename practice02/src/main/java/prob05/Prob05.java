package prob05;

import java.util.Random;
import java.util.Scanner;

public class Prob05 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner( System.in );

		while( true ) {
			
			/* 게임 작성 */

			// 정답 램덤하게 만들기
			Random random = new Random();
			int correctNumber = random.nextInt( 100 ) + 1;
			//System.out.println(correctNumber);
			System.out.println("수를 결정하였습니다. 맞추어 보세요");
			
			int min=1;
			int max=100;
			int count=1;
			
			while(true) {
				System.out.println(min+"-"+max);
				System.out.print(count+">>");
				int num=scanner.nextInt();
				count++;
				
				if(num==correctNumber) {
					break;
				}
				else if(num<correctNumber) {
					System.out.println("더 높게");
					min=num;
				}
				else {
					System.out.println("더 낮게");
					max=num;
				}
			}

			
			//새 게임 여부 확인하기
			System.out.print( "다시 하겠습니까(y/n)>>" );
			String answer = scanner.next();
			if( "y".equals( answer ) == false ) {
				break;
			}
		}
		
		scanner.close();
	}

}