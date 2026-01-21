package tw.EEIIT.tutor;

import tw.brad.apis.Bike;

public class Brad26 {

	public static void main(String[] args) {
		Bike b1 = new Bike();
		b1.accelerate().accelerate().accelerate().lowSpeed();
		System.out.println(b1);
		System.out.println("---");
		String name = "Brad";
		String n2 = name.concat("iii");
		System.out.println(name);
		System.out.println(n2);
	}

}
