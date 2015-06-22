import org.newdawn.slick.SlickException;

/**
 * The Tome of Agility
 *	@author Kemble Song
 */
public class Tome extends Item {
	
	private static final int BOOST = 300;
	public Tome(String img_path, double x, double y) throws SlickException {
		super(img_path, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pickup(Player p) {
		p.setCooldown(p.getCooldown()-BOOST);
		this.setPickedUp(p);
	}

}
