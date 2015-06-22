import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class for handling input of data.
 * @author Kemble Song
 *
 */
public class DataReader {
    /**
     * Used to read positional data of units from a file
     * @param file_path
     * @return an array of positional points
     * @throws FileNotFoundException
     */
    public static ArrayList<Point2D.Double> readData(String file_path){
    	File f = new File(file_path);
    	Scanner sc = null;
		try {
			sc = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
   		ArrayList<Point2D.Double> data = new ArrayList<Point2D.Double>();
   		while (sc.hasNextLine()) {
   			String line = sc.nextLine();
   			String[] points = line.split("\\D+");
   			data.add(new Point2D.Double(Double.parseDouble(points[1]), Double.parseDouble(points[2])));
    	}
    	sc.close();
   		return data;
    }
}
