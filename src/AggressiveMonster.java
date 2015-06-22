import org.newdawn.slick.SlickException;
/**
 * An aggressive monster that chases and attacks the player.
 * @author Kemble Song
 *
 */

public abstract class AggressiveMonster extends Monster {
	/** The max distance in which the monster starts chasing */
	private static final int CHASE_DIST = 150;
	/** Movement speed of this monster */
	private static final double SPEED = 0.25;
	private float damage;
	
	public AggressiveMonster(String img_path, double x, double y, int maxHP, int cooldownTime, float damage) 
		throws SlickException 
	{
		super(img_path, x, y, maxHP, cooldownTime);
		this.damage = damage;
	}
	/** The damage of this monster */
	public float getDamage() {
		return this.damage;
	}
	
	@Override
	public void update(World w, int delta) {
		if (this.getHP() <= 0) {
			this.setIsDead(true);
		}
		if (this.distTo(w.getPlayer()) <= CHASE_DIST) {
			if (this.distTo(w.getPlayer()) > GameObject.INTERACT_DIST) {
				double dist_x = w.getPlayer().getPosition().getX()-this.getPosition().getX();
				double dist_y = w.getPlayer().getPosition().getY()-this.getPosition().getY();
				double dist_total = Math.sqrt((dist_x*dist_x)+(dist_y*dist_y));
				double amount = SPEED * delta;
				double new_x = this.getPosition().getX() + ((dist_x/dist_total) * amount);
				double new_y = this.getPosition().getY() + ((dist_y/dist_total) * amount);
				this.moveTo(new_x, new_y, w);
			}
			else {
				this.attack(w.getPlayer(), delta);
			}
		}
		// Decrement cooldown timer
        if (this.getCooldownTimer() >= 0) {
        	this.setCooldownTimer(this.getCooldownTimer()-delta);
        }
	}
	
    /**
     * Aggressive monster attacks unit.
     * @param u the unit being attacked
     */
    public void attack(Unit u, int delta) {
    	if (this.getCooldownTimer() <= 0) {
    		u.setHP((int)(u.getHP()-Math.random()*this.getDamage()));
    		this.setCooldownTimer(this.getCooldown());
    	}
    	
    }

}
