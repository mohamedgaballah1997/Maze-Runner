package View;
import javax.swing.JPanel;
import javax.swing.Timer;

import Controller.Engine;
import Model.BulletsObserver;
import Model.HealthObserver;
import Model.ScoreObserver;
import Model.Subject;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObserverPanel extends JPanel {
	HealthObserver healthObserver;
	ScoreObserver scoreObserver;
	BulletsObserver bulletsObserver;
	Panel p;
	 Timer t;
	private int count=0;
	JLabel timer ;
	JLabel health;
	JLabel score;
	JLabel bullets;
	Engine eng;
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public ObserverPanel(int width,int height, Color c,Panel p) {
		this.p=p;
		eng=Engine.getEngine();
		healthObserver= new HealthObserver(eng.getP());
		scoreObserver= new ScoreObserver(eng.getP());
		bulletsObserver = new BulletsObserver(eng.getP());
		
		this.setBackground(c);
		Dimension dimension=new Dimension(width, height) ;
		this.setPreferredSize(dimension);
		
		//setBackground(Color.DARK_GRAY);
		setLayout(null); 
	
		JLabel lblTimer = new JLabel("Timer:");
		lblTimer.setForeground(Color.ORANGE);
		lblTimer.setFont(new Font("Harrington", Font.BOLD, 27));
		lblTimer.setBounds(22, 99, 90, 37);
		add(lblTimer);
		
		JLabel lblHealth = new JLabel("Health:");
		lblHealth.setForeground(Color.ORANGE);
		lblHealth.setFont(new Font("Harrington", Font.BOLD, 27));
		lblHealth.setBounds(22, 147, 90, 37);
		add(lblHealth);
		
		JLabel lblBullets = new JLabel("Bullets:");
		lblBullets.setForeground(Color.ORANGE);
		lblBullets.setFont(new Font("Harrington", Font.BOLD, 27));
		lblBullets.setBounds(22, 195, 106, 37);
		add(lblBullets);
		
		JLabel lblScore = new JLabel("Score:");
		lblScore.setForeground(Color.ORANGE);
		lblScore.setFont(new Font("Harrington", Font.BOLD, 27));
		lblScore.setBounds(22, 252, 106, 37);
		add(lblScore);
		
		timer = new JLabel("");
		timer.setForeground(Color.ORANGE);
		timer.setFont(new Font("Harrington", Font.BOLD, 27));
		timer.setBounds(134, 99, 80, 37);
		add(timer);
		
	    health = new JLabel("");
		health.setForeground(Color.ORANGE);
		health.setFont(new Font("Harrington", Font.BOLD, 27));
		health.setBounds(134, 147, 80, 37);
		add(health);
		
		bullets = new JLabel("");
		bullets.setForeground(Color.ORANGE);
		bullets.setFont(new Font("Harrington", Font.BOLD, 27));
		bullets.setBounds(134, 195, 80, 37);
		add(bullets);
		
		score = new JLabel("");
		score.setForeground(Color.ORANGE);
		score.setFont(new Font("Harrington", Font.BOLD, 27));
		score.setBounds(134, 252, 80, 37);
		add(score);
		/*
		JLabel lblshowTime = new JLabel("");
		lblshowTime.setBounds(182, 100, 126, 36);
		add(lblshowTime); */
		t= new Timer (1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				count++;
			//	eng.moveMonsters();
				repaint();
			//	timer.setText(count+"");
				
			}
		});
		t.start();
		if (count==60) {t.stop();}
	}
	@Override
	public void paintComponent(Graphics g) {
		if(eng.isWin()) {eng.getP().setScore((int) (eng.getP().getScore()+ count*0.5)); eng.setWin(false);}
		if(GUI.isPaused == false)
		{super.paintComponent(g);
		timer.setText(count+"");
		health.setText(healthObserver.getHealth()+"");
		score.setText(scoreObserver.getScore()+"");
		bullets.setText(bulletsObserver.getBullets()+"");}
	//	p.repaint();
		
	} 
}