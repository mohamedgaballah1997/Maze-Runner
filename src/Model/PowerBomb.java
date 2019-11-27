package Model;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PowerBomb extends Bomb{
	static String type = "PowerBomb";

	public PowerBomb (Point p)
	{
		position=p;
		try {
			image = ImageIO.read(PowerBomb.class.getResource("/imgs/PowerBomb.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bounds=new Rectangle(position.x,position.y,image.getWidth(),image.getHeight());
	}
	
	
	@Override
	public void damage() {
		// TODO Auto-generated method stub
		
	}

}
