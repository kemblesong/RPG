import org.newdawn.slick.SlickException;
/**
 * Class for an enemy in the game.
 * @author Kemble Song
 *
 */

public abstract class Monster extends Unit {
	
	/** Marks whether the monster has been hit or not. */
	private boolean isHit;
	/** Marks whether the monster is dead, ie. zero HP */
	private boolean isDead;
	
	/** Returns true if monster is hit */
	public boolean getIsHit() {
		return this.isHit;
	}
	public void setIsHit(boolean val) {
		this.isHit = val;
	}
	/** Returns true if monster is dead */
	public boolean getIsDead() {
		return this.isDead;
	}
	public void setIsDead(boolean val) {
		this.isDead = val;
	}
	
	public Monster(String img_path, double x, double y, int maxHP, int cooldownTimer) throws SlickException {
		super(img_path, x, y, maxHP, cooldownTimer);
		this.isHit = false;
		this.isDead = false;
	}
	
	/**
	 * Updates the monster's state.
	 * @param w
	 * @param delta
	 */
	public abstract void update(World w, int delta);
	
}
