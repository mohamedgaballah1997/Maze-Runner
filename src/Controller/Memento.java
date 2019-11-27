package Controller;

import java.awt.Point;
import java.io.Serializable;

public class Memento implements Serializable{
	private int Health;
	private int Bullets;
	private Point Position;



public Memento(int Health, int Bullets, Point p)
{
	  this.Health= Health;
	  this.Bullets= Bullets;
	  this.Position =new Point(p.x,p.y);
}

public int getHealth() {
	return Health;
	
}
	
public int getBullets() {
	return Bullets;
	
}

public Point getPosition() {
	return new Point(Position.x, Position.y);
	
}
	
}