package Controller;

import java.awt.Point;

public class MediumHealth implements State {

	@Override
	public void MoveRight(Point position) {
		position.x+=2;
	}

	@Override
	public void MoveLeft(Point position) {
		position.x-=2;
	}

	@Override
	public void MoveUp(Point position) {
		position.y-=2;
	}

	@Override
	public void MoveDown(Point position) {
	    position.y+=2;
	}

	
}