import org.newdawn.slick.SlickException;
/**
 * A skellington.
 * @author Kemble Song
 *
 */

public class Skeleton extends AggressiveMonster {

	public Skeleton(String img_path, double x, double y, int maxHP,
			int cooldownTime, float damage) throws SlickException {
		super(img_path, x, y, maxHP, cooldownTime, damage);
		this.setName("Skeleton");
	}


}
