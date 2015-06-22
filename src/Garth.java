
import org.newdawn.slick.SlickException;

/**
 * Garth, the villager. Knows where items are.
 * @author Kemble Song
 *
 */
public class Garth extends Villager {

	

	public Garth(String img_path, double x, double y, int maxHP, int cooldownTime) throws SlickException {
		super(img_path, x, y, maxHP, cooldownTime);
		this.setName("Garth");
	}

	@Override
	public void interact(Player p) {
		if (this.getCooldownTimer() <= 0) {
			this.setCooldownTimer(this.getCooldown());
			boolean amulet = false;
			boolean sword = false;
			boolean tome = false;

			for (Item i : p.getInventory()) {
				if (i.getClass().getSimpleName() == "Amulet") {
					amulet = true;
				}
				if (i.getClass().getSimpleName() == "Sword") {
					sword = true;
				}
				if (i.getClass().getSimpleName() == "Tome") {
					tome = true;
				}
			}
			if (!amulet) {
				this.setPhrase("Find the Amulet of Vitality, across the river to the west.");
			} else if (!sword) {
				this.setPhrase("Find the Sword of Strength - cross the river and back, on the east side.");
			} else if (!tome) {
				this.setPhrase("Find the Tome of Agility, in the Land of Shadows.");
			} else {
				this.setPhrase("You have found all the treasure I know of.");
			}
		}
	}
}

