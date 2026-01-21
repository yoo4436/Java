package tw.EEIIT.tutor;

public class Brad25 {

	public static void main(String[] args) {
		Brad251 obj1 = new Brad251(); obj1.m1();
		System.out.println("======1======");
		Brad252 obj2 = new Brad252(); obj2.m1(); obj2.m2();
		System.out.println("======2======");
		Brad251 obj3 = new Brad252(); obj3.m1();
		Brad252 obj4 = (Brad252)obj3;
		System.out.println("======3======");
		Brad251 obj5 = new Brad253();
		obj5.m1();
		System.out.println("======4======");
		go(obj1);
		go(obj2);
		go(obj4);
		System.out.println("======5======");
		if (obj3 instanceof Brad251) {
			System.out.println("OK");
		}else {
			System.out.println("XX");
		}
	}

	static void go(Brad251 obj) {
		obj.m1();
	}
}

class Brad251 {
	void m1() {
		System.out.println("Brad251:m1()");
	}
}

class Brad252 extends Brad251{
	void m1() {
		System.out.println("Brad252:m1()");
	}
	void m2() {
		System.out.println("Brad252:m2()");
	}
}

class Brad253 extends Brad251{
	void m1() {
		System.out.println("Brad253:m1()");
	}
}