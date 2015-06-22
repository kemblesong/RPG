import java.awt.geom.Point2D;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Represents a physical object in the game.
 * Can be a Unit (player, monster or villager) or an Item.
 * @author Kemble Song
 */
public abstract class GameObject {
	
	/** The max distance between two objects in which interaction can occur. */
	public static final int INTERACT_DIST = 50;
	
	/** The 2D point coordinate position of the object */
	private Point2D.Double position;
	
	/** The object's sprite */
	private Image sprite;
	
	public GameObject(String img_path, double x, double y) 
		throws SlickException 
	{
		this.position = new Point2D.Double(x, y);
		this.sprite = new Image(img_path);
	}
	
	/** Gets the position of the object in pixels. */
	public Point2D.Double getPosition() {
		return this.position;
	}
	/** Gets the sprite of the object */
	public Image getSprite() {
		return this.sprite;
	}

	/**
	 * Gives the distance between this object and another object
	 * @param o the other object
	 * @return the distance between them
	 */
	public double distTo(GameObject o) {
		return this.position.distance(o.getPosition());
	}
	
	/**
	 * Renders the object's sprite.
	 */
	public void render() {
		this.sprite.drawCentered((int)this.position.x, (int)this.position.y);
	}
}
