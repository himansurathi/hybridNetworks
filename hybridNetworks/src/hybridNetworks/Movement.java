package hybridNetworks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Movement {

    static int count;
    int id;
    double angle;
    double speed;
    double durationMovement;
    int nodeId;
    Node nodeObject;

    /**
     * Constructor
     * @param idVar
     * @param angleVar
     * @param speedVar
     * @param durationMovementVar
     * @param nodeIdVar
     */
    public Movement(int idVar, double angleVar, double speedVar, double durationMovementVar,int nodeIdVar) {
        id = idVar;
        angle = angleVar;
        speed = speedVar;
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
     * To convert Movement object to a String Object to display all the Requests
     */
    @Override
    public String toString() {
        return id + " : " +angle+" : "+speed+ " : " +durationMovement+ " : " + nodeId;
    }
    
    /**
     * Read Movement File as Input
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Movement[] readInput() throws FileNotFoundException, IOException {
        FileInputStream fstream = new FileInputStream(Constants.CURR_DIR + Constants.MOVEMENT_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        /**
         * Read the file line by line using br.readline() function Get the
         * number of inputs from corresponding file at first
         */
        String strLine;
        Movement movements[];
        int inputCount = 0;
        strLine = br.readLine();
        inputCount = Integer.parseInt(strLine);

        /**
         *
         * Assign objects using data from file input
         */
        movements = new Movement[inputCount];
        for (int i = 0; i < inputCount; i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            double angle = Double.parseDouble(splited[0]);
            double speed = Double.parseDouble(splited[1]);
            double durationMovement = Double.parseDouble(splited[2]);
            int nodeId = Integer.parseInt(splited[3]);
            movements[i] = new Movement(i, angle, speed, durationMovement,nodeId);
        }

        /**
         * Close input stream and return array of objects
         */
        br.close();

        return movements;
    }

}
