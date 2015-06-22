import org.newdawn.slick.SlickException;


public class Bandit extends AggressiveMonster {

	public Bandit(String img_path, double x, double y, int maxHP,
			int cooldownTime, float damage) throws SlickException {
		super(img_path, x, y, maxHP, cooldownTime, damage);
		this.setName("Bandit");
	}



}
