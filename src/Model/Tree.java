package Model;

import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Tree extends Wall{

	public Tree(Point p) {
		
		position=p;
		try {
			image = ImageIO.read(Tree.class.getResource("/imgs/tree.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bounds=new Rectangle(position.x,position.y,image.getWidth(),image.getHeight());
	}

	@Override
	public boolean destroyable() {
		return true;
	}

}
