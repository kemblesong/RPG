/**
 * Cardinal directions.
 * @author Kemble Song
 *
 */
public enum DIRTYPE {
	NORTH, SOUTH, EAST, WEST, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST, NONE;
	
	/**
	 * Gets a random direction type
	 * @return direction
	 */
	public static DIRTYPE getRandom() {
		return values()[(int) (Math.random() * values().length)];
	}
}
