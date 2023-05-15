package jardin;

import java.util.Scanner;

public class InputReader {
	Scanner sc;
	
	InputReader() {
		sc = new Scanner(System.in);
	}
	
	public int readIntValue() {
		
		return sc.nextInt();
	}
}
