package Controller;


import java.awt.Point;

public class CareTaker {
	Memento m;
public void addMemento(Memento m) {
	this.m = m;
}	

public Memento getMemento() {
	return m;
}
}