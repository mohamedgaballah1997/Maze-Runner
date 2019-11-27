package Controller;

import java.awt.Point;

public class HighHealth implements State {

	@Override
	public void MoveRight(Point position) {
		position.x+=4;
	}

	@Override
	public void MoveLeft(Point position) {
	 position.x-=4;
	}

	@Override
	public void MoveUp(Point position) {
	position.y-=4;
	}

	@Override
	public void MoveDown(Point position) {
		position.y+=4;
	}
  

}