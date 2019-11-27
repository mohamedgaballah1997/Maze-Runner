package Model;

import java.awt.Point;
import java.util.ArrayList;

import Controller.GameState;

public abstract class AbstractPlayer extends Component  implements MovableComponents, Subject {
	protected String direction;
	protected static int health;
	protected static int bullets;
	protected static int score;
	protected static boolean check=true;
	protected static GameState gs;
	public static ArrayList<Observer> observers;
	public abstract void MoveDown() ;
	public abstract void MoveRight() ;
	public abstract void MoveUP() ;
	public abstract void MoveLeft() ;
	//observers 
	

	public int getBullets() {
		return bullets;
	}
	public void setBullets(int bullets) {
		this.bullets = bullets;
		notifyObserver();
	}
	public  int getScore() {
		return score;
	}
	public  void setScore(int score) {
		this.score = score;
		notifyObserver();
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public  int getHealth() {
		return health;
	}
	public  void setHealth(int health) {
		if (health>0) check=true;
		this.health = health;
		gs.setState(health);
		notifyObserver();
	}
	public Bullet GetBulletPosition() {
		if(bullets<=0) return null;
		return new Bullet(new Point(position.x, position.y), direction);
	}
	@Override
	public void register(Observer o) {
		observers.add(o);
	}
	@Override
	public void unregister(Observer o) {
		observers.remove(o);
		
	}
	@Override
	public void notifyObserver() {
		for(Observer o : observers) {
			o.update(health, bullets, score);
		}
		
	}
	public abstract void hitMonster(AbstractMonster Monster);
	public boolean getCheck() {
		return this.check;
	}

}
