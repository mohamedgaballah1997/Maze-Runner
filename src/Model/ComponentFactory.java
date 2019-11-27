package Model;

import java.awt.Point;
import java.util.ArrayList;

public class ComponentFactory {
  
  public ComponentFactory()
  {  }
  
  public Component getMazeComponent (int number, Point p) {
	  if(number == 1) {
		 return new Stone(p);
	 } 
	 else if(number == 2) {
		 return new Tree(p);
	 } 
     else if(number == 4)
	  {
    	// return new Door(p);
		 return new ArmorGift(p);
	  }
	  else if(number == 5)
	  {
		 return new Bomb1(p);
	  }
	  else if(number == 6)
	  {
		  return new BulletGift(p);
	  }
	  else if(number== 7)
	  {
		  return new HealthGift(p);
	  }
	  else if(number == 8)
	  {
		 return new PowerBomb(p);
	  }
	  else if(number == 9)
	  {
		  return new Monster(p);
	  }
	  else if(number == 10)
	  {
		 return new Monster2(p);
	  }
	  else if(number == 12)
	  {
		 return new Door(p);
	  }
	  else if(number == 11)
	  {
		  return new Flag(p);
	  }
	return null;
      }
}