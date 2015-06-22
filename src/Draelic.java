import org.newdawn.slick.SlickException;

/**
 * Draelic the Necromancer, Lord of the Land of Shadows.
 * @author kemble
 *
 */
public class Draelic extends AggressiveMonster {

	public Draelic(String img_path, double x, double y, int maxHP, int cooldownTime, float damage) 
		throws SlickException 
	{
		super(img_path, x, y, maxHP, cooldownTime, damage);
		this.setName("Draelic");
	}


}
