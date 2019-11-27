package Model;

import java.awt.Point;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bullet extends Component implements MovableComponents{

	String direction;
	public Bullet (Point p,String direction) {
	position=p;
	this.direction=direction;
	try {
		if(direction.equals("right"))
		image = ImageIO.read(Player.class.getResource("/imgs/bullet.png"));
		else if(direction.equals("up"))
			image = ImageIO.read(Player.class.getResource("/imgs/bullet1.png"));
		else if(direction.equals("down"))
			image = ImageIO.read(Player.class.getResource("/imgs/bullet2.png"));
		else if(direction.equals("left"))
			image = ImageIO.read(Player.class.getResource("/imgs/bullet3.png"));
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
	public void shoot() {
		if(direction.equals("right"))
		position.x+=20;
		else if(direction.equals("left"))
			position.x-=20;
		else if(direction.equals("down"))
			position.y+=20;
		else if(direction.equals("up"))
			position.y-=20;
	}
	@Override
	public boolean hitComponent(Component c) { //+state 
		if(c instanceof Tree || c instanceof Bomb || c instanceof Gift || c instanceof AbstractMonster) {
			return true;
		}
		return false;
	}
}