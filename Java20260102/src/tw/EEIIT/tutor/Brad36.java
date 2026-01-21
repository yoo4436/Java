package tw.EEIIT.tutor;

public class Brad36 {

	public static void main(String[] args) {
		int a = 10, b=1;
		int c;
		int[] d = {1,2,3,4,5};
		
		try {
			c = a / b;
			System.out.println(c);
			System.out.println(d[30]);
		}catch (ArithmeticException e) {
			System.out.println("Ooops!");
		}catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("XXX!");
		}catch (RuntimeException e) {
			System.out.println("ZZZ");
		}
		System.out.println("GameOver");
	}
}
