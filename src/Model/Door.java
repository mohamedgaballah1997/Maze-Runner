package Model;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Door extends Wall{
	
	public Door(Point p)
	{
			position=p;
			try {
				image = ImageIO.read(Door.class.getResource("/imgs/end.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			bounds=new Rectangle(position.x,position.y,image.getWidth(),image.getHeight());

	}

	@Override
	public boolean destroyable() {
		return false;
	}

}
