package tw.brad.apis;

public class Scooter extends Bike{
	private int gear;
	private String color;

	
	public Scooter() {
		super(2);
		System.out.println("Scooter()");
		color = "Yellow";
	}
	
	public Scooter(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return color;
	}
	
	@Override //可寫可不寫
	public Scooter accelerate() {
		speed = speed < 1 ? 1 : speed * 1.8 * gear;
		return this;
	}

	public void setGear(int gear) {
		if (gear >= 0 && gear <= 4) {
			this.gear = gear;
		}
	}
	
	public int getGear() {
		return gear;
	}
}
