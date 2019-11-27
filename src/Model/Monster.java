package Model;

import java.awt.Point;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Monster extends AbstractMonster{

	public Monster(Point p) {
		position=p;
		try {
			image = ImageIO.read(Player.class.getResource("/imgs/Monster.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
