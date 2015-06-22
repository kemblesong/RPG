import org.newdawn.slick.SlickException;

/**
 * Elvira, the healing shaman.
 * @author Kemble Song
 *
 */
public class Elvira extends Villager {
	
	

	public Elvira(String img_path, double x, double y, int maxHP, int cooldownTime) throws SlickException {
		super(img_path, x, y, maxHP, cooldownTime);
		this.setName("Elvira");
	}


	@Override
	public void interact(Player p) {
		if (this.getCooldownTimer() <= 0) {
			this.setCooldownTimer(this.getCooldown());
			if (p.getHP() < p.getMaxHP()) {
				p.setHP(p.getMaxHP());
				this.setPhrase("You're looking much healther now");
				return;
			}
			else if (p.getHP() == p.getMaxHP()) {
				this.setPhrase("Return to me if you ever need healing.");
			}
		}
	}
}
