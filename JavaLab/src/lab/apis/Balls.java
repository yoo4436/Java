package lab.apis;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.DebugGraphics;
import javax.swing.JPanel;

import lab.exee.ShootBalls;

public class Balls extends JPanel{
	private List<Oval> balls;
	private Random rand = new Random();
	private ShootBalls gameFrame;
	
	private final int ball_size = 60;
	private final int ball_maximum = 5;
	
	public Balls(ShootBalls sb) {
		this.gameFrame = sb;
		setBackground(Color.gray);
		balls = new ArrayList<>();
		
		MsListener listener = new MsListener();
		addMouseListener(listener);
		
		for(int i=0; i<ball_maximum;i++) {
			addRandomBall(800 -20, 600 -100);
		}
		
	}
	
	public void reset() {
		balls.clear();
		for(int i=0; i<ball_maximum;i++) {
			addRandomBall(getWidth(), getHeight());
		}
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (Oval ball:balls) {
			ball.draw(g);
		}
		
//		Graphics2D g2d = (Graphics2D)g;
//		g2d.setColor(new Color(20,250,220));
//		g2d.fillOval(300, 200, 100, 100);
//		g2d.setColor(new Color(20,250,220));
//		g2d.drawOval(300, 200, 100, 100);
		
//		if (g instanceof Graphics2D) {
//			System.out.println("2D");
//		}else if (g instanceof DebugGraphics) {
//			System.out.println("De");
//		}
	}

	protected boolean isHit(Oval ball, int ccx, int ccy) {
		double distance = Math.sqrt(
				Math.pow(ccx - ball.getCenterX(),2) + 
				Math.pow(ccy - ball.getCenterY(),2)
				);
		return distance <= ball.Radius();
	}
	
	private boolean isOverlapping(Oval b1, Oval b2) {
		double distance = Math.sqrt(
				Math.pow(b1.getCenterX() - b2.getCenterX(), 2) +
				Math.pow(b1.getCenterY() - b2.getCenterY(), 2)
				);
		
		return distance < (b1.Radius() + b2.Radius());
	}
	
	private void addRandomBall(int screenW, int screenH) {
		int attempt = 0;
		while(attempt < 100) {
			int rx = rand.nextInt(screenW - ball_size);
			int ry = rand.nextInt(screenH - ball_size);
			Color rc = new Color(20,250,220);
			
			Oval newBall = new Oval(rx,ry,ball_size,rc);
			
			boolean overlap = false;
			for (Oval exist:balls) {
				if (isOverlapping(newBall, exist)) {
					overlap = true;
					break;
				}
			}
			
			if (!overlap) {
				balls.add(newBall);
				break;
			}
			attempt++;
		}
		
	}
	
	private class MsListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			int ccx = e.getX();
			int ccy = e.getY();
			
			for(int i = balls.size() -1; i>=0 ;i--) {
				Oval ball = balls.get(i);
				
				if (isHit (ball, ccx,ccy)) {
					balls.remove(i);
					gameFrame.addScore();
					gameFrame.GameStart();
					addRandomBall(getWidth(), getHeight());
					
					repaint();
					break;
				}
			}
		}
	}
	
}
