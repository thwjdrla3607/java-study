package prob01;

public class Printer {
	
//    // 정수값 출력 메소드
//    public void println(int value) {
//        System.out.println(value);
//    }
//
//    // 논리값 출력 메소드
//    public void println(boolean value) {
//        System.out.println(value);
//    }
//
//    // 실수값 출력 메소드
//    public void println(double value) {
//        System.out.println(value);
//    }
//
//    // 문자열 출력 메소드
//    public void println(String value) {
//        System.out.println(value);
//    }

	
//	** 제너릭 메서드
	public <T> void println(T t) {
		System.out.println(t);
	}

//	** Object로 받아서 사용 _ 위랑 동일한 코드
//	public void println(Object o) {
//		System.out.print(o);
//	}
	
	public int sum(Integer... nums) {
		int s = 0;
		for(Integer i : nums) {
			s += i;
		}
		return s;
	}
	
	public <T> void println(T... ts) {
		for(T t : ts) {
			System.out.print(t);
		}
	}
}
