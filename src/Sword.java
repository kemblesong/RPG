import org.newdawn.slick.SlickException;

/**
 * The Sword of Strength
 * @author Kemble Song
 *
 */

public class Sword extends Item {

	private static final int BOOST = 30;	
	public Sword(String img_path, double x, double y) throws SlickException {
		super(img_path, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pickup(Player p) {
		p.setDamage(p.getDamage()+BOOST);
		this.setPickedUp(p);
	}

}
