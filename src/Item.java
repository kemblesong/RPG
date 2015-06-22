import org.newdawn.slick.SlickException;


/**
 * Class for an item in the game
 * @author Kemble Song
 */
public abstract class Item extends GameObject {
	
	/** Marker for determining if this item has been picked up. */
	private boolean pickedUp;
	
	public Item(String img_path, double x, double y) throws SlickException {
		super(img_path, x, y);
		this.pickedUp = false;
	}
	
	/** Marks this item as picked up and adds item to player's inventory. */
	public void setPickedUp(Player p) {
		this.pickedUp = true;
		p.getInventory().add(this);
	}
	/** Returns whether this item has been picked up or not. */
	public boolean getPickedUp() {
		return this.pickedUp;
	}
	/**
	 * Causes a special effect on the player.
	 * Marks item as picked up.
	 * @param p the player interacting with the item
	 */
	public abstract void pickup(Player p);
}
