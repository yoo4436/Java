package tw.brad.apis;

import java.io.Serializable;

public class Student implements Serializable{
	private String name;
	private int ch, en, math;
	private Bike bike;
	
	public Student(String name, int ch, int en, int math){
		this.name = name; this.ch = ch;
		this.en = en; this.math = math;
		bike = new Bike();
	}
	
	public int score() {
		return ch+en+math;
	}
	
	public double avg() {
		return score() / 3.0;
	}
	
	public String getName() {
		return name;
	}
	
	public Bike getBike() {
		return bike;
	}
}
