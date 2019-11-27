package Model;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HealthGift extends Gift {

	public HealthGift(Point pt) {
		position=pt;
		try {
			image = ImageIO.read(HealthGift.class.getResource("/imgs/gift3.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bounds=new Rectangle(position.x,position.y,image.getWidth(),image.getHeight());
	}

	public void giftAction(AbstractPlayer p) {
		p.setHealth((p.getHealth() + 30));
		if(p.getHealth()>100) p.setHealth(100);
		p.setScore(p.getScore()+10);
		
	}

}
