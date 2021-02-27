public class printEncoding {
	
	public static void printE(String str, String asf) {
		if(str.length() == 0) {
			System.out.println(asf);
			return;
		}
		
		// problematic situation
		if(str.charAt(0) == '0')
			return;
		
		// first call - obvious
		int num1 = str.charAt(0) - '0';
		char ch1 = (char)('a' + num1 - 1);
		printE(str.substring(1), asf + ch1);
		// second - conditional
		if(str.length() > 1) {
			int num = Integer.parseInt("" + str.charAt(0) + str.charAt(1));
			if(num >= 10 && num <= 26) {
				char ch2 = (char)('a' + num - 1);
				printE(str.substring(2), asf + ch2);
			}
		}
	}
	
	public static void main(String[] args) {
		printE("655196", "");
	}

}
