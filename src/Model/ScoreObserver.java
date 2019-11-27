package Model;

public class ScoreObserver implements Observer {
	private int score;
	private Subject player;
	
	public ScoreObserver(Subject player) {
		super();
		this.player = player;
		this.score=((Player) player).getScore();
		player.register(this);
	}

	@Override
	public void update(int health, int bullets, int score) {
		this.score=score;

	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
