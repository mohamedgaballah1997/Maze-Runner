package Model;

import java.awt.Point;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Monster2 extends AbstractMonster{
	
	public Monster2(Point p) {
		position=p;
		try {
			image = ImageIO.read(Player.class.getResource("/imgs/Monster2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
