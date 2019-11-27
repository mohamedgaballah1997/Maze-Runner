package Controller;


public class GameState {

	private State state;
	
	public State getState() {
		return state;
	}

	public void setState(int health) {
		if(health >60)
			this.state= new HighHealth();
		else if ( health >30)
			this.state= new MediumHealth();
		else
			this.state= new LowHealth();
	}

}