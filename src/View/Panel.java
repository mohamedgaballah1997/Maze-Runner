package View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import Controller.Engine;
import Model.Bullet;
import Model.Stone;

public class Panel extends JPanel {
	//Player p;
	Stone  s;
	Bullet b;
	Engine eng;
	KeyManager k;
	//public ObserverPanel observerpanel;
	public Panel(int width,int height, Color c) {
		//this.setBackground(Color.BLUE);
		Dimension dimension=new Dimension(width, height) ;
	//	this.setPreferredSize(dimension);
		//this.observerpanel = observerpanel;
		k=new KeyManager();
		addKeyListener(k);
		//p=new Player();
		s=new Stone(new Point(50, 50));
		
		eng=Engine.getEngine();
		Timer t= new Timer (250, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eng.moveMonsters();
				repaint();
			//	timer.setText(count+"");
				
			}
		});
		t.start();
	}
	public void addnewKeyListener() {
		removeKeyListener(k);
		k=new KeyManager();
		//System.out.println(k);
		addKeyListener(k);
	}

public class KeyManager implements KeyListener{

	@Override
	public void keyPressed(KeyEvent arg0) {

if(arg0.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(eng.isWin()==true) {
			JOptionPane.showMessageDialog(null, "YOU WON!!!!");
			eng.setWin(false);
				System.exit(0);
				}
		else eng.moveRight();
			repaint();
		}
		if(arg0.getKeyCode()==KeyEvent.VK_LEFT) {
			eng.moveLeft();
			repaint();
		}
		if(arg0.getKeyCode()==KeyEvent.VK_UP) {
			eng.moveUp();
			repaint();
		}
		if(arg0.getKeyCode()==KeyEvent.VK_DOWN) {
			eng.moveDown();
			repaint();
		}
		if(arg0.getKeyCode()==KeyEvent.VK_SPACE) {
			
			Bullet b=eng.getP().GetBulletPosition(); //null if no bullets left
			if(b!= null)
			{eng.setB(b);
			eng.getP().setBullets(eng.getP().getBullets()-1);
		//	eng.getMazeDraw().add(b);
		//	for(int i=0;i<200;i++) { 
		//	Component cmp;
		//	cmp=eng.collides(b);
			while(!(eng.collides(b))) {
				b.shoot();
				paintComponent(getGraphics());
			}
			//	if(cmp instanceof Tree || cmp instanceof Gift || cmp instanceof Bomb)
			//		eng.getMazeDraw().remove(cmp); // remove from 2d array
			//	eng.getMazeDraw().remove(b);
			repaint();}
			//p.setBullets(p.getBullets()-1);
		//	eng.shoot(getGraphics());
			}
		}
		
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		
		 
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}
}

public void paintComponent(Graphics g) {
	
	super.paintComponent(g);
//	if (eng.getP().getCheck()== true)
	
//	observerpanel.repaint();
	try {
		eng.paint(g);
		//g.drawImage(p.getImage(), p.getPosition().x, p.getPosition().y, null);
		//g.drawImage(s.getImage(),s.getPosition().x, s.getPosition().y, null);
	//	g.drawImage(b.getImage(),b.getPosition().x, b.getPosition().y, null);
	}
	catch (Exception e) {}
	//return to check point till lives end
//	else {
	//	eng.returnToCheckpoint();
	//	repaint();
//		}
}
}
 