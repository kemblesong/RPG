import org.newdawn.slick.SlickException;

/**
 * An enemy that runs away from the player when attacked.
 * @author Kemble Song
 *
 */
public abstract class PassiveMonster extends Monster {
	
	/** How long it flees for */
	private static final int FLEE_TIME = 5;
	/** How often a new random direction is chosen*/
	private static final int UPDATE_TIME = 3;
	/** Speed of this monster*/
	private static final double SPEED = 0.20; 
	
	/** A timer that determines how long until the monster stops fleeing. */
	private double fleeTimer;
	
	/** The direction the monster moves in */
	private DIRTYPE direction;
	
	public PassiveMonster(String img_path, double x, double y, int maxHP, int cooldownTime, double fleeTimer) 
		throws SlickException 
	{
		super(img_path, x, y, maxHP, cooldownTime);
		this.fleeTimer = fleeTimer;
		this.direction = DIRTYPE.getRandom();
	}
	
	/** Returns the time left until monster stops fleeing */
	public double getFleeTimer() {
		return this.fleeTimer;
	}
	
	/** Returns the direction this is moving in*/
	public DIRTYPE getDirection() {
		return this.direction;
	}
	
	@Override
	public void update(World w, int delta) {
		// Check if we are dead.
		if (this.getHP() <= 0) {
			this.setIsDead(true);
		}
		
		// If hit, runaway from player
		if (this.getIsHit()) {
			this.runaway(w.getPlayer(), delta, w);
			// Runaway for some time, then stop
			if (this.fleeTimer <= 0) {
				this.setIsHit(false); // Reset hit status
				this.fleeTimer = FLEE_TIME; // Reset the flee timer
			}
		}
		// Otherwise do random things
		else {
			if (this.getCooldownTimer() <= 0) {
				this.direction = DIRTYPE.getRandom();
				this.setCooldownTimer(UPDATE_TIME);
			}
			else {
				double amount = SPEED * delta;
				if (direction == DIRTYPE.NORTH)
					this.moveTo(this.getPosition().getX(), this.getPosition().getY()+amount, w);
				if (direction == DIRTYPE.NORTHEAST)
					this.moveTo(this.getPosition().getX()+amount, this.getPosition().getY()+amount, w);
				if (direction == DIRTYPE.NORTHWEST)
					this.moveTo(this.getPosition().getX()-amount, this.getPosition().getY()+amount, w);
				if (direction == DIRTYPE.EAST)
					this.moveTo(this.getPosition().getX()+amount, this.getPosition().getY(), w);
				if (direction == DIRTYPE.WEST)
					this.moveTo(this.getPosition().getX()-amount, this.getPosition().getY(), w);
				if (direction == DIRTYPE.SOUTH)
					this.moveTo(this.getPosition().getX(), this.getPosition().getY()-amount, w);
				if (direction == DIRTYPE.SOUTHEAST)
					this.moveTo(this.getPosition().getX()+amount, this.getPosition().getY()-amount, w);
				if (direction == DIRTYPE.SOUTHWEST)
					this.moveTo(this.getPosition().getX()-amount, this.getPosition().getY()-amount, w);
				this.setCooldownTimer(this.getCooldownTimer()-((double)delta/1000));
			}
		}
	}
	/**
	 * Algorithm for running away from a player
	 * @param p the player to runaway from
	 * @param delta time elapsed since last frame (milliseconds)
	 * @param w the world
	 */
	public void runaway(Player p, int delta, World w) {
		double dist_x = p.getPosition().getX()-this.getPosition().getX();
		double dist_y = p.getPosition().getY()-this.getPosition().getY();
		double dist_total = Math.sqrt((dist_x*dist_x)+(dist_y*dist_y));
		double amount = SPEED * delta;
		double new_x = this.getPosition().getX() - ((dist_x/dist_total) * amount);
		double new_y = this.getPosition().getY() - ((dist_y/dist_total) * amount);
		this.moveTo(new_x, new_y, w);
		this.fleeTimer -= ((double)delta/1000);
	}
}
