import org.newdawn.slick.SlickException;

/**
 * A giant bat.
 * @author Kemble Song
 *
 */
public class GiantBat extends PassiveMonster {

	public GiantBat(String img_path, double x, double y, int maxHP,
			int cooldownTime, double fleeTimer) throws SlickException {
		super(img_path, x, y, maxHP, cooldownTime, fleeTimer);
		this.setName("Giant Bat");
	}


}
