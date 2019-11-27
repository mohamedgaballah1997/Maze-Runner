package Model;

import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.omg.CORBA.Bounds;

import Controller.Engine;
import Controller.GameState;
import java.io.*;
import sun.audio.*;

public class Player extends AbstractPlayer  {

private static Player p;
//	private static Point posis

/*	public Player(Point position) {
		super();
		this.position=position;
		try {
			image = ImageIO.read(Player.class.getResource("/imgs/pacman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		direction="right";
		notifyObserver();
	} */
	private Player() {
		this.position=new Point(24, 24);
		health=100;
		score=0;
		bullets=6;
		gs=new GameState();
		observers= new ArrayList<>();
		gs.setState(health);
		try {
			image = ImageIO.read(Player.class.getResource("/imgs/pacman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		direction="right";
	}
	public static AbstractPlayer getPlayer() {
		if(p==null)
		{ p= new Player();}
		return p;
	}
	public void MoveRight() {
		try {
			image = ImageIO.read(Player.class.getResource("/imgs/pacman.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		gs.getState().MoveRight(position);
		direction="right";
	}
	public void MoveLeft() {
		try {
			image = ImageIO.read(Player.class.getResource("/imgs/pacman3.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gs.getState().MoveLeft(position);
		direction="left";
	}
	public void MoveUP() {
		try {
			image = ImageIO.read(Player.class.getResource("/imgs/pacman1.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gs.getState().MoveUp(position);
		direction="up";
	}
	public void MoveDown() {
		try {
			image = ImageIO.read(Player.class.getResource("/imgs/pacman2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gs.getState().MoveDown(position);
		direction="down";
	}

	public void hitMonster(AbstractMonster am) {
		
		if(this.getHealth()>30 && am instanceof Monster)
		{setHealth(this.getHealth()-30);
		}
		else {
			setHealth(0);
			check=false;
		}
		notifyObserver();
	}
	@Override
	public boolean hitComponent(Component c) { //+state 
		if(c instanceof Wall || c instanceof Monster) {
			if(direction.equals("right")) {
				gs.getState().MoveLeft(position);
			}
			else if(direction.equals("left")) {
				gs.getState().MoveRight(position);
			}
			else if(direction.equals("up")) {
				gs.getState().MoveDown(position);
			}
			else if(direction.equals("down")) {
				gs.getState().MoveUp(position);
			}
			if(c instanceof Monster) {
				hitMonster((AbstractMonster) c);
			}
			return false;
		}
		else if(c instanceof Bomb1) {
			if(this.getHealth()>10)
			{setHealth(this.getHealth()-10);
		
			}
			else {
				setHealth(0);
				check=false;
			}
			notifyObserver();
			return true;
		}
		else if(c instanceof PowerBomb) {
			if(this.getHealth()>30)
			{setHealth(this.getHealth()-30);
			}
			else {
				setHealth(0);
				check=false;
			}
			notifyObserver();
			return true;
		}
		else if(c instanceof PowerBomb) {
			if(this.getHealth()>30)
			{setHealth(this.getHealth()-30);
			}
			else {
				setHealth(0);
				check=false;
			}
			notifyObserver();
			return true;
		}/*
		else if(c instanceof Monster) {
			if(direction.equals("right")) {
				gs.getState().MoveLeft(position);
			}
			else if(direction.equals("left")) {
				gs.getState().MoveRight(position);
			}
			else if(direction.equals("up")) {
				gs.getState().MoveDown(position);
			}
			else if(direction.equals("down")) {
				gs.getState().MoveUp(position);
			}
			if(this.getHealth()>30)
			{setHealth(this.getHealth()-30);
			}
			else {
				setHealth(0);
				check=false;
			}
			notifyObserver();
			return false;
		} */
		else if(c instanceof Gift) {
			((Gift) c).giftAction(this);
		//	System.out.println("flag");
		//	System.out.println(this instanceof ArmorDecorator);
		return true; // gift
		}
		else if(c instanceof Flag) {
			return true;
		}
		else if(c instanceof Door) {
			return false;
		}
		return false ;
	}



}
