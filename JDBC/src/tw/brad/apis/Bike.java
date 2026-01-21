package tw.brad.apis;

import java.io.Serializable;

public class Bike implements Serializable{
	protected double speed;
	
	public Bike() {
		System.out.println("Bike()");
	}
	
	public Bike(int a) {
		System.out.println("Bike(int)");
	}
	
	public Bike accelerate() {
		speed = speed < 1 ? 1 :speed * 1.3;
		return this;
	}
	public Bike lowSpeed() {
		speed = speed < 1 ? 0 : speed * 0.5;
		return this;
	}
	public double getSpeed() {
		return speed;
	}
	
	public String toString() {
		return "Speed: " + speed;
	}
}
