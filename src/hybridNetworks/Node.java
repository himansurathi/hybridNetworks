package hybridNetworks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Node {

    private static int count;
    private int id;
    private double initialX;
    private double initialY;
    private double currentX;
    private double currentY;
    private int subscriberId;
    Station stationObject;

    /**
     * 
     * @param idVar
     * @param xVar
     * @param yVar
     * @param subscriberIdVar
     */
    public Node(int idVar, double xVar, double yVar,int subscriberIdVar) {
        id = idVar;
        initialX = currentX=xVar;
        initialY = currentY=yVar;
        subscriberId=subscriberIdVar;
    }

    /**
     * @return the count
     */
    public static int getCount() {
        return count;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }


    /**
	 * @return the initialX
	 */
	public double getInitialX() {
		return initialX;
	}

	/**
	 * @return the initialY
	 */
	public double getInitialY() {
		return initialY;
	}

	/**
	 * @return the currentX
	 */
	public double getCurrentX() {
		return currentX;
	}

	/**
	 * @return the currentY
	 */
	public double getCurrentY() {
		return currentY;
	}

	/**
	 * @return the subscriberId
	 */
	public int getSubscriberId() {
		return subscriberId;
	}

	/**
     * @return the subscriberObject
     */
    public Station getStationObject() {
        return stationObject;
    }
    
    /**
	 * @param subscriberId the subscriberId to set
	 */
	public void setSubscriberId(int subscriberId) {
		this.subscriberId = subscriberId;
	}

	/**
	 * @param subscriberObject the subscriberObject to set
	 */
	public void setSubscriberObject(Station stationObject) {
		this.stationObject = stationObject;
	}

	/**
     * To convert Node object to a String Object to display all the Requests
     */
    @Override
    public String toString() {
        return id + " : " + initialX+" : "+initialY+ " : " + currentX+ " : " + currentY + " : " + subscriberId;
    }

    /**
     * Read Node File as Input
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<Node> readInput() throws FileNotFoundException, IOException {

        /**
         * Open file input stream for reading
         */
        FileInputStream fstream = new FileInputStream(Constants.CURR_DIR + Constants.NODE_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        /**
         * Read the file line by line using br.readline() function Get the
         * number of inputs from corresponding file at first
         */
        String strLine;
        ArrayList<Node> nodes;
        strLine = br.readLine();
        count = Integer.parseInt(strLine);

        /**
         *
         * Assign objects using data from file input
         */
        nodes = new ArrayList<Node>();
        for (int i = 0; i < getCount(); i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            double x = Double.parseDouble(splited[0]);
            double y = Double.parseDouble(splited[1]);
            int subscriberId = Integer.parseInt(splited[2]);
            nodes.add(new Node(i, x, y,subscriberId));
        }

        /**
         * Close input stream and return array of objects
         */
        br.close();

        return nodes;
    }

    /**
	 * Display Nodes File
     * @param title
     * @param format
     * @param nodes
     */
	public static void displayNodes(String title,String format,ArrayList<Node> nodes) {
		System.out.println(title);
		int size = nodes.size();
		System.out.println(size);
		System.out.println(format);
		for (Node i : nodes) {
			System.out.println(i);
		}
	}
	

	public double calcDistance(Station s) {
		// TODO Auto-generated method stub
		double differenceX=getCurrentX()-s.getX();
		double differenceY=getCurrentY()-s.getY();
		double distance=Math.sqrt(Math.pow(differenceX, 2)+Math.pow(differenceY, 2));
		return distance;
	}
}
