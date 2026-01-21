package lab.exee;

import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import lab.apis.Balls;

public class ShootBalls extends JFrame{
	private Balls tballs;
	private JButton restart;
	private JLabel timer,score;
	private int timeLeft = 60,point;
	private Timer gameTime;
	
	public ShootBalls() {
		
		tballs = new Balls(this);
		setLayout(new BorderLayout());
		add(tballs, BorderLayout.CENTER);
		
		JPanel top = new JPanel(new FlowLayout());
		restart = new JButton("ReStart");
		timer = new JLabel("Time: "+ timeLeft);
		score = new JLabel("Score: " + point + " ");
		top.add(score);
		top.add(timer);
		top.add(restart);
		
		add(top,BorderLayout.NORTH);
		
		setSize(800, 600);
		setTitle("ShootGame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setVisible(true);
		
		initEvent();
	}
	
	public void GameStart() {
		gameTime.start();
	}
	
	public void addScore() {
		point++;
		score.setText("Score: " + point + " ");
	}
	
	private void initEvent() {
		restart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				gameTime.stop();
				timeLeft = 60;
				timer.setText("Time: " + timeLeft);
				point = 0;
				score.setText("Score: " + point + " ");
				tballs.reset();
			}
		});
		// 提示：第一個參數是延遲時間 (毫秒)，第二個是監聽器
		gameTime = new Timer(1000, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	timeLeft--;
		    	timer.setText("Time: " + timeLeft);
		    	if(timeLeft == 0) {
		    		JOptionPane.showMessageDialog(ShootBalls.this, "Game Over");
		    		gameTime.stop();
		    	}
		    }
		});
	}




	public static void main(String[] args) {
		new ShootBalls();
	}

}
