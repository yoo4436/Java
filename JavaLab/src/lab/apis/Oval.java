package lab.apis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Oval {
	private int x;
	private int y;
	private int d;
	private Color color;
	
	public Oval(int x, int y, int d, Color color) {
		this.x=x;
		this.y=y;
		this.d=d;
		this.color=color;
	}
	
	public int getCenterX() {
		return x + d / 2;
	}
	
	public int getCenterY() {
		return y + d / 2;
	}
	
	public int Radius() {
		return d / 2;
	}
	
	protected void draw(Graphics g) {
		g.setColor(new Color(20,250,220));
		g.fillOval(x, y, d, d);
		g.setColor(new Color(20,250,220));
		g.drawOval(x, y, d, d);
	}
}
