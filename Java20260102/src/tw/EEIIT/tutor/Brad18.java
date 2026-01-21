package tw.EEIIT.tutor;

import tw.brad.apis.Scooter;

public class Brad18 {

	public static void main(String[] args) {
		Scooter s1 = new Scooter();
		s1.accelerate();s1.accelerate();s1.accelerate();
		s1.accelerate();s1.accelerate();
		System.out.println(s1.getSpeed());
		s1.setGear(3);
		s1.accelerate();s1.accelerate();
		System.out.println(s1.getSpeed());
		System.out.println(s1.getColor());
		Scooter s2 = new Scooter("Red");
		System.out.println(s2.getColor());
	}

}