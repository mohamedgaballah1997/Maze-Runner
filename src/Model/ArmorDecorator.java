package Model;

import java.awt.Point;
import java.io.IOException;

import javax.imageio.ImageIO;

import Controller.Engine;

public class ArmorDecorator extends Decorator {
	AbstractPlayer pl;
	private static ArmorDecorator ad;
	private ArmorDecorator(AbstractPlayer pl) {
		super();
		this.pl=pl;
		position=pl.getPosition();
		try {
			image = ImageIO.read(ArmorDecorator.class.getResource("/imgs/pacman with armor.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		direction="right";
	}
public static ArmorDecorator getArmorDecorator(AbstractPlayer pl) {
	if(ad==null)
	{ ad= new ArmorDecorator(pl);}
	return ad;
}
	public void MoveRight() {
		pl.MoveRight();
		try {
			image = ImageIO.read(ArmorDecorator.class.getResource("/imgs/pacman with armor.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		direction="right";
   }
	public void MoveLeft() {
		pl.MoveLeft();
		try {
			image = ImageIO.read(ArmorDecorator.class.getResource("/imgs/pacman with armor3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		direction="left";
	}
	public void MoveUP() {
		pl.MoveUP();	
		try {
			image = ImageIO.read(ArmorDecorator.class.getResource("/imgs/pacman with armor1.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		direction="up";
	}
	public void MoveDown() {
		pl.MoveDown();
		try {
			image = ImageIO.read(ArmorDecorator.class.getResource("/imgs/pacman with armor2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		direction="down";
	}
	@Override
	public boolean hitComponent(Component c) {
		if(c instanceof Monster) return false;
		if(c instanceof Bomb) return true;
		return pl.hitComponent(c);
	}
	@Override
	public void hitMonster(AbstractMonster Monster) {
		// TODO Auto-generated method stub
		
	}


}
