import org.newdawn.slick.SlickException;

/**
 * Braaains
 * @author Kemble Song
 *
 */
public class Zombie extends AggressiveMonster {

	public Zombie(String img_path, double x, double y, int maxHP,
			int cooldownTime, float damage) throws SlickException {
		super(img_path, x, y, maxHP, cooldownTime, damage);
		this.setName("Zombie");
	}


}
