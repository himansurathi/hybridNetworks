package hybridNetworks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Movement {

    private static int count;
    private int id;
    private double angle;
    private double speed;
    private double startMovement;
    private double durationMovement;
    private int nodeId;
    private Node nodeObject;

    /**
     * Constructor
     * @param idVar
     * @param angleVar
     * @param speedVar
     * @param durationMovementVar
     * @param nodeIdVar
     */
    public Movement(int idVar, double angleVar, double speedVar, double startMovementVar,double durationMovementVar,int nodeIdVar) {
        id = idVar;
        angle = angleVar;
        speed = speedVar;
        startMovement=startMovementVar;
        durationMovement = durationMovementVar;
        nodeId=nodeIdVar;
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
     * @return the angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * @return the speed
     */
    public double getSpeed() {
        return speed;
    }
	/**
	 * @return the startMovemnt
	 */
	public double getStartMovement() {
		return startMovement;
	}

	/**
     * @return the durationMovement
     */
    public double getDurationMovement() {
        return durationMovement;
    }

    /**
	 * @return the nodeId
	 */
	public int getNodeId() {
		return nodeId;
	}

    /**
     * @return the nodeObject
     */
    public Node getNodeObject() {
        return nodeObject;
    }

    /**
	 * @param nodeId the nodeId to set
	 */
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * @param nodeObject the nodeObject to set
	 */
	public void setNodeObject(Node nodeObject) {
		this.nodeObject = nodeObject;
	}

	/**
     * To convert Movement object to a String Object to display all the Requests
     */
    @Override
    public String toString() {
        return id + " : " +angle+" : "+speed+ " : " +startMovement+" : "+durationMovement+ " : " + nodeId;
    }
    
    /**
     * Read Movement File as Input
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<Movement> readInput() throws FileNotFoundException, IOException {
        FileInputStream fstream = new FileInputStream(Constants.CURR_DIR + Constants.MOVEMENT_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        /**
         * Read the file line by line using br.readline() function Get the
         * number of inputs from corresponding file at first
         */
        String strLine;
        ArrayList<Movement> movements;
        strLine = br.readLine();
        count= Integer.parseInt(strLine);

        /**
         *
         * Assign objects using data from file input
         */
        movements = new ArrayList<Movement>();
        for (int i = 0; i < getCount(); i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            double angle = Double.parseDouble(splited[0]);
            double speed = Double.parseDouble(splited[1]);
            double startMovement = Double.parseDouble(splited[2]);
            double durationMovement = Double.parseDouble(splited[3]);
            int nodeId = Integer.parseInt(splited[4]);
            movements.add(new Movement(i, angle, speed, startMovement,durationMovement,nodeId));
        }

        /**
         * Close input stream and return array of objects
         */
        br.close();

        return movements;
    }

    /**
	 * Display Movement File
     * @param title
     * @param format
     * @param movements
     */
	public static void displayMovement(String title,String format,ArrayList<Movement> movements) {
		System.out.println(title);
		int size = movements.size();
		System.out.println(size);
		System.out.println(format);
		for (Movement i : movements) {
			System.out.println(i);
		}
	}

	public static PriorityQueue<Movement> arrangeMovementOnBasisOfTime(ArrayList<Movement> movements) {
		// TODO Auto-generated method stub
		 MovementTimeComparator movementTimeComparator = new MovementTimeComparator(); //Creating a user defined comparator for time
			PriorityQueue<Movement> sortedMovements = new PriorityQueue<Movement>(count, movementTimeComparator); // Store the requests in Priority Queue on basis of timeComparator defined.
	        for (Movement i : movements) {
	            sortedMovements.add(i);
	        }
         return sortedMovements; //Return the priority Queue.
	}

}
