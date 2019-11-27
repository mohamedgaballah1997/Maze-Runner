package Model;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Component implements Serializable{

	protected int width,height;
	protected Point position;
	protected BufferedImage image;
	protected Rectangle bounds;
	
	public Rectangle getBounds() {
		return new Rectangle(position.x,position.y,image.getWidth(),image.getHeight());
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public  BufferedImage getImage() {
		return image;
	}

	public  void setImage(BufferedImage image) {
		this.image = image;
	}

	public Component() {
	}
	public boolean collides(Component c) {
		if(c.getBounds().intersects(getBounds()) && !this.equals(c)) {
			return true;
		}
		return false;
	}


}
