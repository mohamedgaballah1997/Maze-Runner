package Model;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Stone extends Wall{
	public Stone(Point p) {
		position=p;
		try {
			image = ImageIO.read(Player.class.getResource("/imgs/BrickWall.png"));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean destroyable() {
		return false;
	}

}
