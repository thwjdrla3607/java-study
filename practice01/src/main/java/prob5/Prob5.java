package prob5;

public class Prob5 {

	public static void main(String[] args) {
		
		int num = 1;
		
		while(num <= 99) {
			String clap = "";
			String Str = Integer.toString(num);
			
			if(Str.contains("3") || Str.contains("6") || Str.contains("9")) {
				for(int i = 0 ; i < Str.length() ; i++) {
					char c = Str.charAt(i);
					if(c == '3' || c == '6' || c == '9') {
						clap += "짝";
					}
				}
				System.out.println(num + " " + clap);
			}
			num++;
		}	
	}
}