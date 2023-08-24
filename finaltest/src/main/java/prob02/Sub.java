package prob02;

public class Sub implements Arithmetic {

	@Override
	public int calculate(int a, int b) {
		int answer = a - b;
		return answer;
	}

}
