package tw.EEIIT.tutor;

public class Brad30 {

	public static void main(String[] args) {
		Brad301 obj1 = new Brad302();
		Brad303 obj2 = new Brad302();
		obj1.m1();
		obj2.m3();
	}

}

interface Brad301{
	void m1();
	void m2();
}

class Brad302 implements Brad301,Brad303 {
	public void m1() {
		System.out.println("M1");
	}
	public void m2() {
		System.out.println("M2");
	}
	public void m3() {
		System.out.println("M3");
	}
	public void m4() {
		System.out.println("M4");
	}
}

interface Brad303{
	void m3();
	void m4();
}