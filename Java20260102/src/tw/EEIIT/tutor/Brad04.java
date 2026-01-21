package tw.EEIIT.tutor;

import java.util.Scanner;

public class Brad04 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("a = ");
		int a = scanner.nextInt();
		System.out.print("b = ");
		int b = scanner.nextInt();
		int t1 = a + b;
		int t2 = a - b;
		int t3 = a * b;
		int t4 = a / b;
		int t5 = a % b;
		System.out.printf("%d + %d = %d\n",a,b,t1);
		System.out.printf("%d - %d = %d\n",a,b,t2);
		System.out.printf("%d x %d = %d\n",a,b,t3);
		System.out.printf("%d / %d = %d ... %d\n",a,b,t4,t5);
	}

}
