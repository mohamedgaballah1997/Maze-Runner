package Controller;

import java.awt.Point;


public class LowHealth implements State {

	@Override
	public void MoveRight(Point position) {
		position.x+=1;
		
	}

	@Override
	public void MoveLeft(Point position) {
		position.x-=1;
		
	}

	@Override
	public void MoveUp(Point position) {
		position.y-=1;
		
	}

	@Override
	public void MoveDown(Point position) {
		position.y+=1;
		
	}
   

}