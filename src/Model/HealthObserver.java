package Model;

public class HealthObserver implements Observer{
	private int health;
	 public Subject player;
	
	public HealthObserver(Subject player) {
		super();
		this.player = player;
		this.health=((Player) player).getHealth();
		player.register(this);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public void update(int health, int bullets, int score) {
		this.health=health;
	}

}
