import org.newdawn.slick.SlickException;

/**
 * The Elixir of Life
 * @author Kemble Song
 *
 */

public class Elixir extends Item {

	public Elixir(String img_path, double x, double y) throws SlickException {
		super(img_path, x, y);
	}

	@Override
	public void pickup(Player p) {
		this.setPickedUp(p);

	}

}
