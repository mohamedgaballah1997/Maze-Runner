package Model;

import java.awt.Point;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bomb1 extends Bomb {

	public Bomb1(Point p) {
		position=p;
		try {
			image = ImageIO.read(Player.class.getResource("/imgs/bomb.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void damage(/*player*/) {
		// player.sethealth...
		
		
	}

	
}
