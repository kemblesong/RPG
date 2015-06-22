/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: Kemble Song
 */

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/** The character which the user plays as.
 * @author Kemble Song
 */
public class Player extends Unit {
	/** The position to respawn in */
	private static final int RESPAWN_X = 738, RESPAWN_Y = 549;
	private Image panel = new Image(RPG.ASSETS_PATH + "/panel.png");
	private Image sprite_flipped = null;
    private float damage;
    
    // In pixels
    private double width, height;
    private boolean face_left = false;

    /** The player's inventory of items collected */
    private ArrayList<Item> inventory;
    
    // Pixels per millisecond
    private static final double SPEED = 0.25;
    
    /** Returns the player's damage */
    public float getDamage() {
    	return this.damage;
    }
    /** Set the player's damage */
    public void setDamage(float damage) {
    	this.damage = damage;
    }

    /** Returns the player's inventory */
    public ArrayList<Item> getInventory() {
    	return this.inventory;
    }
    
    /** Creates a new Player.
     * @param image_path Path of player's image file.
     * @param x The Player's starting x location in pixels.
     * @param y The Player's starting y location in pixels.
     * @param maxHP The Player's maximum HP.
     * @param cooldownTime The delay between the Player's attacks.
     * @param damage The Player's damage.
     */
    public Player(String img_path, double x, double y, int maxHP, int cooldownTime, float damage)
        throws SlickException
    {
        super(img_path, x, y, maxHP, cooldownTime);
        sprite_flipped = this.getSprite().getFlippedCopy(true, false);
        this.damage = damage;
        this.width = this.getSprite().getWidth()-20;
        this.height = this.getSprite().getHeight()-20;
        this.inventory = new ArrayList<Item>();
    }

    /** Move the player in a given direction.
     * Prevents the player from moving outside the map space, and also updates
     * the direction the player is facing.
     * @param world The world the player is on (to check blocking).
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(World world, double dir_x, double dir_y, double delta)
    {
        
    	if (this.getHP() <= 0) {
    		this.setHP(this.getMaxHP());
    		this.getPosition().setLocation(RESPAWN_X, RESPAWN_Y);
    	}
    	
    	// Update player facing based on X direction
        if (dir_x > 0)
            this.face_left = false;
        else if (dir_x < 0)
            this.face_left = true;

        // Move the player by dir_x, dir_y, as a multiple of delta * speed
        double new_x = this.getPosition().getX() + dir_x * delta * SPEED;
        double new_y = this.getPosition().getY() + dir_y * delta * SPEED;

        // Move in x first
        double x_sign = Math.signum(dir_x);
        if(!world.terrainBlocks(new_x + x_sign * width / 2, this.getPosition().getY() + height / 2) 
                && !world.terrainBlocks(new_x + x_sign * width / 2, this.getPosition().getY() - height / 2)) {
            this.getPosition().x = new_x;
        }
        
        // Then move in y
        double y_sign = Math.signum(dir_y);
        if(!world.terrainBlocks(this.getPosition().getX() + width / 2, new_y + y_sign * height / 2) 
                && !world.terrainBlocks(this.getPosition().getX() - width / 2, new_y + y_sign * height / 2)){
            this.getPosition().y = new_y;
        }
        
        // Decrement cooldown timer
        if (this.getCooldownTimer() >= 0) {
        	this.setCooldownTimer(this.getCooldownTimer()-delta);
        }
    }
    /**
     * Player attacks monster.
     * @param m the unit being attacked
     */
    public void attack(Monster m) {
    	if (this.getCooldownTimer() <= 0) {
    		m.setHP((int)(m.getHP()-Math.random()*this.getDamage()));
    		m.setIsHit(true);
    		this.setCooldownTimer(this.getCooldown());
    	}
    	
    }

    @Override
    /** Draw the player to the screen at the correct place.
     * @param g The current Graphics context.
     * @param cam_x Camera x position in pixels.
     * @param cam_y Camera y position in pixels.
     */
    public void render()
    {
        Image which_img;
        which_img = this.face_left ? this.sprite_flipped : this.getSprite();
        which_img.drawCentered((int) this.getPosition().getX(), (int) this.getPosition().getY());
    }
    
    /** Renders the player's status panel.
     * @param g The current Slick graphics context.
     */
    public void renderPanel(Graphics g)
    {
        // Panel colours
        Color LABEL = new Color(0.9f, 0.9f, 0.4f);          // Gold
        Color VALUE = new Color(1.0f, 1.0f, 1.0f);          // White
        Color BAR_BG = new Color(0.0f, 0.0f, 0.0f, 0.8f);   // Black, transp
        Color BAR = new Color(0.8f, 0.0f, 0.0f, 0.8f);      // Red, transp

        // Variables for layout
        String text;                // Text to display
        int text_x, text_y;         // Coordinates to draw text
        int bar_x, bar_y;           // Coordinates to draw rectangles
        int bar_width, bar_height;  // Size of rectangle to draw
        int hp_bar_width;           // Size of red (HP) rectangle
        int inv_x, inv_y;           // Coordinates to draw inventory item

        float health_percent;       // Player's health, as a percentage

        // Panel background image
        panel.draw(0, RPG.SCREEN_HEIGHT - RPG.PANELHEIGHT);

        // Display the player's health
        text_x = 15;
        text_y = RPG.SCREEN_HEIGHT - RPG.PANELHEIGHT + 25;
        g.setColor(LABEL);
        g.drawString("Health:", text_x, text_y);
        text = Integer.toString((int)this.getHP()) + "/" + Integer.toString(this.getMaxHP());                                 // TODO: HP / Max-HP

        bar_x = 90;
        bar_y = RPG.SCREEN_HEIGHT - RPG.PANELHEIGHT + 20;
        bar_width = 90;
        bar_height = 30;
        health_percent = this.getHP()/this.getMaxHP();                         
        hp_bar_width = (int) (bar_width * health_percent);
        text_x = bar_x + (bar_width - g.getFont().getWidth(text)) / 2;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);
        g.setColor(BAR);
        g.fillRect(bar_x, bar_y, hp_bar_width, bar_height);
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's damage and cooldown
        text_x = 200;
        g.setColor(LABEL);
        g.drawString("Damage:", text_x, text_y);
        text_x += 80;
        text = Integer.toString((int)this.getDamage());                                    
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);
        text_x += 40;
        g.setColor(LABEL);
        g.drawString("Rate:", text_x, text_y);
        text_x += 55;
        text = Integer.toString((int)this.getCooldown());       
        g.setColor(VALUE);
        g.drawString(text, text_x, text_y);

        // Display the player's inventory
        g.setColor(LABEL);
        g.drawString("Items:", 420, text_y);
        bar_x = 490;
        bar_y = RPG.SCREEN_HEIGHT - RPG.PANELHEIGHT + 10;
        bar_width = 288;
        bar_height = bar_height + 20;
        g.setColor(BAR_BG);
        g.fillRect(bar_x, bar_y, bar_width, bar_height);

        inv_x = 490;
        inv_y = RPG.SCREEN_HEIGHT - RPG.PANELHEIGHT
            + ((RPG.PANELHEIGHT - 72) / 2);
        // for (each item in the player's inventory)        
        for (Item i : this.inventory)
        {
            // Render the item to (inv_x, inv_y)
            i.getSprite().draw(inv_x, inv_y);
        	inv_x += 72;
        }
    }

}
