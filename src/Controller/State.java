package Controller;


import java.awt.Point;

public interface State {

	public void MoveRight(Point position);
	public void MoveLeft(Point position);
	public void MoveUp(Point position);
	public void MoveDown(Point position);

}