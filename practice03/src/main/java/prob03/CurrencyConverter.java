package prob03;

public class CurrencyConverter {
	private static double rate;
	
	public static double toDollar(double won) {
		double dollar = won / rate;
		
		return dollar;
	}
	
	public static double toKRW(double dollar) {
		double won = dollar * rate;
		
		return won;
	}
	
	public static void setRate(double r) {
		if(r>0) {
			rate = r;
		} else{
			System.out.println("유효하지 않은 환율입니다.");
		}
	}
}