package hybridNetworks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Node {

    static int count;
    int id;
    double initialX;
    double initialY;
    double currentX;
    double currentY;
    int subscriberId;
    SubscriberStation subscriberObject;

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
    public SubscriberStation getSubscriberObject() {
        return subscriberObject;
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
    public static Node[] readInput() throws FileNotFoundException, IOException {

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
        Node nodes[];
        int inputCount = 0;
        strLine = br.readLine();
        inputCount = Integer.parseInt(strLine);

        /**
         *
         * Assign objects using data from file input
         */
        nodes = new Node[inputCount];
        for (int i = 0; i < inputCount; i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            double x = Double.parseDouble(splited[0]);
            double y = Double.parseDouble(splited[1]);
            int subscriberId = Integer.parseInt(splited[2]);
            nodes[i] = new Node(i, x, y,subscriberId);
        }

        /**
         * Close input stream and return array of objects
         */
        br.close();

        return nodes;

    }
}
