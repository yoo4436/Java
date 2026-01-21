package tw.EEIIT.tutor;

public class Brad07 {

	public static void main(String[] args) {
		int year = 2100;
		boolean isLeap;
		
//		if(year%4==0) {
//			if(year%100==0) {
//				if(year%400==0)
//					System.out.printf("%d年為閏年", year);
//				else
//					System.out.printf("%d年為平年", year);
//			}
//			else {
//				System.out.printf("%d年為閏年", year);
//			}
//		}
//		else {
//			System.out.printf("%d年為平年", year);
//		}
		
		if(year%4==0) {
			if(year%100==0) {
				if(year%400==0)
					isLeap = true;
				else
					isLeap = false;
			}
			else {
				isLeap = true;
			}
		}
		else {
			isLeap = false;
		}
		System.out.printf("%d年為%s年", year, isLeap?"閏":"平");
	}

}
