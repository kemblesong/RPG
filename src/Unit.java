

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


/**
 * A unit inside the game.
 * Can be a player, monster or villager
 * @author Kemble Song
 */

public abstract class Unit extends GameObject {
	
	private int maxHP;
	private float hp;
	private double cooldownTimer;
	private double cooldown;
	private String name;
	
	public Unit(String img_path, double x, double y, int maxHP, double cooldownTime) 
	throws SlickException 
	{
		super(img_path, x, y);
		this.maxHP = maxHP;
		this.hp = this.maxHP;
		this.cooldownTimer = 0;
		this.cooldown = cooldownTime;
	}
	
	/** Gets maxHP */
	public int getMaxHP() {
		return this.maxHP;
	}
	public void setMaxHP(int hp) {
		this.maxHP = hp;
	}
	
	/** Gets current HP */
	public float getHP() {
		return this.hp;
	}
	public void setHP(float hp) {
		this.hp = hp;
	}
	
	/** Gets cooldown timer */
	public double getCooldownTimer() {
		return this.cooldownTimer;
	}
	public void setCooldownTimer(double time) {
		this.cooldownTimer = time;
	}
	
	/** Gets the time to reset timer to */
	public double getCooldown() {
		return this.cooldown;
	}
	public void setCooldown(double time) {
		this.cooldown = time;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Moves unit to position (x,y) as long as terrain does not block it.
	 * @param x Horizontal coordinate.
	 * @param y Vertical coordinate.
	 * @param w The world to move in.
	 */
	public void moveTo(double x, double y, World w) {
		if (!w.terrainBlocks(x, y)) {
			this.getPosition().setLocation(x, y);
		}
	}
	/**
	 * Renders the health bar with name of unit.
	 * @param g The graphics context.
	 */
	public void renderHealthbar(Graphics g) {
		 Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
		 Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp
		 Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);
		 
		 String text;                // Text to display
		 int text_x, text_y;         // Coordinates to draw text
	     int bar_x, bar_y;           // Coordinates to draw rectangles
	     int bar_width, bar_height;  // Size of rectangle to draw
	     int hp_bar_width;           // Size of red (HP) rectangle
	     float health_percent;
	     
	     text = this.name;
	     bar_width = Math.max(g.getFont().getWidth(text)+6, 70);
	     bar_height = 20;
	     bar_x = (int)this.getPosition().getX()-bar_width/2;
	     bar_y = (int)this.getPosition().getY()-50;
	     health_percent = this.getHP()/this.getMaxHP();
	     hp_bar_width = (int) (bar_width * health_percent);
	     text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
	     text_y = bar_y+2;
	     g.setColor(BAR_BG);
	     g.fillRect(bar_x, bar_y, bar_width, bar_height);
	     g.setColor(BAR);
	     g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
	     g.setColor(VALUE);
	     g.drawString(text, text_x, text_y);
	}
}
