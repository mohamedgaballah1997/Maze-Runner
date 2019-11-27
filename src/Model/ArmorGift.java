package Model;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ArmorGift extends Gift {
	
	public ArmorGift(Point p) {
		position=p;
		try {
			image = ImageIO.read(ArmorGift.class.getResource("/imgs/gift.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bounds=new Rectangle(position.x,position.y,image.getWidth(),image.getHeight());
	}


	@Override
	public void giftAction(AbstractPlayer p) {
		p.setScore(p.getScore()+10);
	//	p=new ArmorDecorator(p);
		
	}

}
