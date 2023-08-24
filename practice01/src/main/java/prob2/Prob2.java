package prob2;

public class Prob2 {
	public static void main(String[] args) {
		/* 코드 작성 */
		int row = 9;
		int col = 10;
		
		for (int i = 1; i <= row; i++ ) {
			for (int j = i; j < i + col ; j++) {
				System.out.print(j + " ");
			}
			System.out.println();
		}
	}
}
