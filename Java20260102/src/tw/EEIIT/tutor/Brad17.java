package tw.EEIIT.tutor;

import tw.brad.apis.Bike;

public class Brad17 {

	public static void main(String[] args) {
		Bike bike1 = new Bike();
		Bike bike2 = new Bike();
		System.out.println(bike1);
		System.out.println(bike2);
		System.out.println(bike1.getSpeed());
		System.out.println(bike2.getSpeed());
		
		bike1.accelerate();bike1.accelerate();bike1.accelerate();bike1.accelerate();
		System.out.println(bike1.getSpeed());
		
		
	}
}
