package Model;

public class BulletsObserver implements Observer{
	private int bullets;
	private Subject player;
	public int getBullets() {
		return bullets;
	}

	public void setBullets(int bullets) {
		this.bullets = bullets;
	}
	public BulletsObserver(Subject player) {
		super();
		this.player = player;
		this.bullets=((Player) player).getBullets();
		player.register(this);
	}

	@Override
	public void update(int health, int bullets, int score) {
		this.bullets=bullets;
	}

}
