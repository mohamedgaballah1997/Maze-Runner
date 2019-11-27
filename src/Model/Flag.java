package Model;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Flag extends Component{
	public Flag(Point p)
	{
			position=p;
			try {
				image = ImageIO.read(Flag.class.getResource("/imgs/flag.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			bounds=new Rectangle(position.x,position.y,image.getWidth(),image.getHeight());

	}

}
