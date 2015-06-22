import org.newdawn.slick.SlickException;

/**
 * The Amulet of Vitality
 * @author Kemble Song
 */
public class Amulet extends Item {
	
	private static final int BOOST = 80; 
	
	public Amulet(String img_path, double x, double y) throws SlickException {
		super(img_path, x, y);
	}

	@Override
	public void pickup(Player p) {
		p.setHP(p.getHP()+BOOST);
		p.setMaxHP(p.getMaxHP()+BOOST);
		this.setPickedUp(p);
	}

}
