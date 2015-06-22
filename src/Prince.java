
import org.newdawn.slick.SlickException;

/**
 * Prince Aldric, seeker of the elixir.
 * @author Kemble Song
 *
 */
public class Prince extends Villager {

	
	// Constructor
	public Prince(String img_path, double x, double y, int maxHP, int cooldownTime) throws SlickException {
		super(img_path, x, y, maxHP, cooldownTime);
		this.setName("Prince Aldric");
	}

	@Override
	public void interact(Player p) {
		if (this.getCooldownTimer() <= 0) {
			this.setCooldownTimer(this.getCooldown());
			for (Item i : p.getInventory()) {
				if (i.getClass().getSimpleName() == "Elixir") {
					this.setPhrase("The elixir! My father is cured! Thankyou!");
					return;
				}
			}
			this.setPhrase("Please seek out the Elixir of Life to cure the king.");
		}
	}

}
