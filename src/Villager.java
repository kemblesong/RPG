import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: Kemble Song
 */

/**
 * Class for a villager
 * @author Kemble Song
 */
public abstract class Villager extends Unit{
	private String phrase;
	
	public Villager(String img_path, double x, double y, int maxHP, int cooldownTime) 
		throws SlickException 
	{
		super(img_path, x, y, maxHP, cooldownTime);
	}
	/**
	 * Villager interacts with player.
	 * @param p The player to interact with.
	 */
	public abstract void interact(Player p);
	
	public void renderDialogue(Graphics g) {
		Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
        Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
        
        String text;                // Text to display
		int text_x, text_y;         // Coordinates to draw text
	    int bar_x, bar_y;           // Coordinates to draw rectangles
	    int bar_width, bar_height;  // Size of rectangle to draw
	    
	    text = this.phrase;
	    bar_width = g.getFont().getWidth(text)+6;
	    bar_height = 20;
	    bar_x = (int)this.getPosition().getX()-bar_width/2;
	    bar_y = (int)this.getPosition().getY()-70;
	    text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
	    text_y = bar_y+2;
	    g.setColor(BAR_BG);
	    g.fillRect(bar_x, bar_y, bar_width, bar_height);
	    g.setColor(VALUE);
	    g.drawString(text, text_x, text_y);
	}
	
	public String getPhrase() {
		return this.phrase;
	}
	public void setPhrase(String phrase) {
		this.phrase = phrase;
	}
}
