/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: Kemble Song
 */

import java.awt.geom.Point2D;
import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 * @author Kemble Song
 */
public class World
{
    private static final int PLAYER_START_X = 756, PLAYER_START_Y = 684;
    private static final int PLAYER_START_HP = 100, PLAYER_START_COOLDOWN = 600;
    private static final int PLAYER_START_DAMAGE = 26;
    
    private static final int VILLAGER_HP = 1, VILLAGER_STAT = 4;
    private static final int ELVIRA_START_X = 738, ELVIRA_START_Y = 549;
    private static final int GARTH_START_X = 756, GARTH_START_Y = 870;
    private static final int PRINCE_START_X = 467, PRINCE_START_Y = 679;
    
    private static final int AMULET_START_X = 965, AMULET_START_Y = 3563;
    private static final int SWORD_START_X = 4791, SWORD_START_Y = 1253;
    private static final int TOME_START_X = 546, TOME_START_Y = 6707;
    private static final int ELIXIR_START_X = 1976, ELIXIR_START_Y = 402;
    
    private static final int DRAELIC_START_X = 2069, DRAELIC_START_Y = 510;
    private static final int DRAELIC_HP = 140, DRAELIC_COOLDOWN = 400;
    private static final int DRAELIC_DAMAGE = 30;
    
    private ArrayList<Point2D.Double> batLocations = DataReader.readData(RPG.DATA_PATH + "/bats.txt");
    private static final int GIANT_BAT_HP = 40, GIANT_BAT_STAT = 3, GIANT_BAT_FLEE_TIME = 5;
    
    private ArrayList<Point2D.Double> zombieLocations = DataReader.readData(RPG.DATA_PATH + "/zombies.txt");
    private static final int ZOMBIE_HP = 60, ZOMBIE_COOLDOWN = 800, ZOMBIE_DAMAGE = 10;
    
    private ArrayList<Point2D.Double> banditLocations = DataReader.readData(RPG.DATA_PATH + "/bandits.txt");
    private static final int BANDIT_HP = 40, BANDIT_COOLDOWN = 200, BANDIT_DAMAGE = 8;
    
    private ArrayList<Point2D.Double> skeletonLocations = DataReader.readData(RPG.DATA_PATH + "/skeletons.txt");
    private static final int SKELETON_HP = 100, SKELETON_COOLDOWN = 500, SKELETON_DAMAGE = 16;
    
    private Player player = null;
    private ArrayList<Villager> villagers;
    private ArrayList<Monster> monsters;
    private ArrayList<Item> items;
    private TiledMap map = null;
    private Camera camera = null;

    /** Map width, in pixels. */
    private int getMapWidth()
    {
        return map.getWidth() * getTileWidth();
    }

    /** Map height, in pixels. */
    private int getMapHeight()
    {
        return map.getHeight() * getTileHeight();
    }

    /** Tile width, in pixels. */
    private int getTileWidth()
    {
        return map.getTileWidth();
    }

    /** Tile height, in pixels. */
    private int getTileHeight()
    {
        return map.getTileHeight();
    }
    
    public Player getPlayer() {
    	return this.player;
    }
    
    /** Create a new World object. */
    public World()
    throws SlickException
    {
        // Initialize the map, player and camera
    	map = new TiledMap(RPG.ASSETS_PATH + "/map.tmx", RPG.ASSETS_PATH);
        player = new Player(RPG.ASSETS_PATH + "/units/player.png", PLAYER_START_X, PLAYER_START_Y, PLAYER_START_HP, PLAYER_START_COOLDOWN, PLAYER_START_DAMAGE);
        camera = new Camera(player, RPG.SCREEN_WIDTH, RPG.SCREEN_HEIGHT);
        
        // Initialize villagers
        villagers = new ArrayList<Villager>();
        villagers.add(new Elvira(RPG.ASSETS_PATH + "/units/shaman.png", ELVIRA_START_X, ELVIRA_START_Y, VILLAGER_HP, VILLAGER_STAT));
        villagers.add(new Garth(RPG.ASSETS_PATH + "/units/peasant.png", GARTH_START_X, GARTH_START_Y, VILLAGER_HP, VILLAGER_STAT));
        villagers.add(new Prince(RPG.ASSETS_PATH + "/units/prince.png", PRINCE_START_X, PRINCE_START_Y, VILLAGER_HP, VILLAGER_STAT));
        
        // Initialize monsters
        monsters = new ArrayList<Monster>();
        monsters.add(new Draelic(RPG.ASSETS_PATH + "/units/necromancer.png", DRAELIC_START_X, DRAELIC_START_Y, DRAELIC_HP, DRAELIC_COOLDOWN, DRAELIC_DAMAGE));
        for (Point2D.Double p : batLocations) {
        	monsters.add(new GiantBat(RPG.ASSETS_PATH + "/units/dreadbat.png", p.getX(), p.getY(), GIANT_BAT_HP, GIANT_BAT_STAT, GIANT_BAT_FLEE_TIME));
        }
        for (Point2D.Double p : zombieLocations) {
        	monsters.add(new Zombie(RPG.ASSETS_PATH + "/units/zombie.png", p.getX(), p.getY(), ZOMBIE_HP, ZOMBIE_COOLDOWN, ZOMBIE_DAMAGE));
        }
        for (Point2D.Double p : banditLocations) {
        	monsters.add(new Bandit(RPG.ASSETS_PATH + "/units/bandit.png", p.getX(), p.getY(), BANDIT_HP, BANDIT_COOLDOWN, BANDIT_DAMAGE));
        }
        for (Point2D.Double p : skeletonLocations) {
        	monsters.add(new Skeleton(RPG.ASSETS_PATH + "/units/skeleton.png", p.getX(), p.getY(), SKELETON_HP, SKELETON_COOLDOWN, SKELETON_DAMAGE));
        }
        
        // Initialize items
        items = new ArrayList<Item>();
        items.add(new Amulet(RPG.ASSETS_PATH + "/items/amulet.png", AMULET_START_X, AMULET_START_Y));
        items.add(new Sword(RPG.ASSETS_PATH + "/items/sword.png", SWORD_START_X, SWORD_START_Y));
        items.add(new Tome(RPG.ASSETS_PATH + "/items/book.png", TOME_START_X, TOME_START_Y));
        items.add(new Elixir(RPG.ASSETS_PATH + "/items/elixir.png", ELIXIR_START_X, ELIXIR_START_Y));
        
        
    }

    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param attack True if player has initiated attack
     * @param talk True if player has initiated talk
     * @param delta Time passed since last frame (milliseconds).
     */
    public void update(int dir_x, int dir_y, boolean attack, boolean talk, int delta)
    throws SlickException
    {
        player.update(this, dir_x, dir_y, delta);
        camera.update();
        
        
        for (Monster m : monsters) {
        	// Check if the player is attacking it
        	if (attack && player.distTo(m) <= GameObject.INTERACT_DIST) {
        		player.attack(m);
        	}
        	// Update monster's state if its not dead
        	if (!m.getIsDead()) {
        		m.update(this, delta);
        	}
        }
        
        for (Villager v : villagers) {
        	// Check if the player is talking to it
        	if (talk && player.distTo(v) <= GameObject.INTERACT_DIST) {
        		v.interact(player);
        	}
        	// Decrement the timer for dialogue display
        	v.setCooldownTimer(v.getCooldownTimer()-((double)delta/1000));
        }
        
        for (Item i : items) {
        	// Check if the player is close the item
        	if (player.distTo(i) <= GameObject.INTERACT_DIST) {
        		// If item hasn't been picked up, player picks it up.
        		if (!i.getPickedUp()) {
        			i.pickup(player);
        		}
        	}
        }
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(Graphics g)
    throws SlickException
    {
        // Render the relevant section of the map
        int x = -(camera.getMinX() % getTileWidth());
        int y = -(camera.getMinY() % getTileHeight());
        int sx = camera.getMinX() / getTileWidth();
        int sy = camera.getMinY()/ getTileHeight();
        int w = (camera.getMaxX() / getTileWidth()) - (camera.getMinX() / getTileWidth()) + 1;
        int h = (camera.getMaxY() / getTileHeight()) - (camera.getMinY() / getTileHeight()) + 1;
        map.render(x, y, sx, sy, w, h);
        
        // Render the player's status panel
        player.renderPanel(g);
        
        // Translate the Graphics object
        g.translate(-camera.getMinX(), -camera.getMinY());

        // Render the player
        player.render();
        
        // Render the villagers
        for (Villager v : villagers) {
        	v.render();
        	v.renderHealthbar(g);
        	if (v.getCooldownTimer() > 0) {
        		v.renderDialogue(g);
        	}
        }
        
        // Render the monsters
        for (Monster m : monsters) {
        	if (!m.getIsDead()) {
        		m.render();
        		m.renderHealthbar(g);
        	}
        }
        
        // Render the items
        for (Item i : items) {
        	if (!i.getPickedUp())
        		i.render();
        }
    }

    /** Determines whether a particular map coordinate blocks movement.
     * @param x Map x coordinate (in pixels).
     * @param y Map y coordinate (in pixels).
     * @return true if the coordinate blocks movement.
     */
    public boolean terrainBlocks(double x, double y)
    {
        // Check we are within the bounds of the map
        if (x < 0 || y < 0 || x > getMapWidth() || y > getMapHeight()) {
            return true;
        }
        
        // Check the tile properties
        int tile_x = (int) x / getTileWidth();
        int tile_y = (int) y / getTileHeight();
        int tileid = map.getTileId(tile_x, tile_y, 0);
        String block = map.getTileProperty(tileid, "block", "0");
        return !block.equals("0");
    }    
}
