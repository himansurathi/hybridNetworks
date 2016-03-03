package hybridNetworks;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import hybridNetworks.Request;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubscriberStation {

    static int count;
    int id;
    double x;
    double y;
    double maxBandwidth;
    double range;
    int baseId;
    BaseStation baseObject;
    PriorityQueue<Request> requests;
    
    /**
     * Constructor
     * @param idVar
     * @param xVar
     * @param yVar
     * @param maxBandwidthVar
     * @param rangeVar
     * @param baseIdVar
     */
    public SubscriberStation(int idVar, double xVar, double yVar, double maxBandwidthVar,double rangeVar,int baseIdVar) {
        id = idVar;
        x = xVar;
        y = yVar;
        maxBandwidth = maxBandwidthVar;
        range=rangeVar;
        baseId=baseIdVar;
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
     * @return the x
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y
     */
    public double getY() {
        return y;
    }

    /**
     * @return the maxBandwidth
     */
    public double getMaxBandwidth() {
        return maxBandwidth;
    }

        /**
	 * @return the range
	 */
	public double getRange() {
		return range;
	}

	/**
	 * @return the baseId
	 */
	public int getBaseId() {
		return baseId;
	}

	/**
     * @return the baseObject
     */
    public BaseStation getBaseObject() {
        return baseObject;
    }

    /**
     * @return the requests
     */
    public PriorityQueue<Request> getRequests() {
        return requests;
    }

    /**
     * To convert SubscriberStation object to a String Object to display all the Requests
     */
    @Override
    public String toString() {
        return id + " : " +x+" : "+y+ " : " + maxBandwidth+ " : " + range + " : " + baseId;
    }

	/**
     * Scheduling of the requests available at each subscriber stations
     * according to maximum allowed requests that can be transferred for each of
     * the individual subscriber- base station links. The functions returns all
     * those allowed requests
     *
     * @param nodeRequests
     * @return
     */
    public List<Request> scheduling(List<Request> nodeRequests) {
        int currentBandwidth = 0;
        List<Request> allowedRequest = new ArrayList<Request>();
        for (Request i : nodeRequests) {
            if ((currentBandwidth + i.maxRequiredRequest) <= maxBandwidth) {
            	//Checking if the request can be transferred from the subscriber station via
                // the base-subscriber bandwidth.
                allowedRequest.add(i);
                currentBandwidth += i.maxRequiredRequest;
            }
        }
        return allowedRequest; // Returns List of Allowed Requests

    }
    
    /**
     * Read Subscriber Station File as Input
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static SubscriberStation[] readInput() throws FileNotFoundException, IOException {
        /**
         * Open file input stream for reading
         */

        FileInputStream fstream = new FileInputStream("");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        /**
         * Read the file line by line using br.readline() function Get the
         * number of inputs from corresponding file at first
         */
        String strLine;
        SubscriberStation subscriberStations[];
        int inputCount = 0;
        strLine = br.readLine();
        inputCount = Integer.parseInt(strLine);

        /**
         *
         * Assign objects using data from file input
         */
        subscriberStations = new SubscriberStation[inputCount];
        for (int i = 0; i < inputCount; i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            double x = Double.parseDouble(splited[0]);
            double y = Double.parseDouble(splited[1]);
            double maxBandwidth = Double.parseDouble(splited[2]);
            double range = Double.parseDouble(splited[3]);
            int baseId = Integer.parseInt(splited[4]);
            subscriberStations[i] = new SubscriberStation(i, x, y, maxBandwidth,range,baseId);
        }

        /**
         * Close input stream and return array of objects
         */
        br.close();

        return subscriberStations;

    }

}
