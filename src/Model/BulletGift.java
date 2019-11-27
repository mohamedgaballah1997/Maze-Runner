package Model;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BulletGift extends Gift {

	public BulletGift(Point p) {
		this.position=p;
		try {
			image = ImageIO.read(BulletGift.class.getResource("/imgs/gift2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bounds=new Rectangle(position.x,position.y,image.getWidth(),image.getHeight());
	}

	public void giftAction(AbstractPlayer p) {
		p.setBullets(p.getBullets()+3);
		p.setScore(p.getScore()+ 10);
	}





}
