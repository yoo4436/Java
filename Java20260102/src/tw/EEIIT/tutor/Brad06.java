package tw.EEIIT.tutor;

public class Brad06 {

	public static void main(String[] args) {
		double c = Math.random() * 101;	
		int score = (int)c;	
		System.out.println(score);
		if(score >= 90) 
			System.out.println("A");
		else if(score >= 80)
			System.out.println("B");
		else if(score >= 70)
			System.out.println("C");
		else if(score >=60)
			System.out.println("D");
		else
			System.out.println("Down");
	}

}
