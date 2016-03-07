package hybridNetworks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BaseStation {

    private static int count;
    private int id;
    private double x;
    private double y;
    private double maxBandwidth;
    private double frameSize;
    private double range; 
    private ArrayList<Request> requests;

    /**
     * Constructor
	 *
     * @param idVar
     * @param xVar
     * @param yVar
     * @param maxBandwidthVar
     * @param frameSizeVar
     * @param rangeVar
     */
    public BaseStation(int idVar, double xVar, double yVar, double maxBandwidthVar, double frameSizeVar,double rangeVar) {
        id = idVar;
        x = xVar;
        y = yVar;
        maxBandwidth = maxBandwidthVar;
        frameSize = frameSizeVar;
        range=rangeVar;
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
     * @return the frameSize
     */
    public double getFrameSize() {
        return frameSize;
    }

    /**
	 * @return the range
	 */
	public double getRange() {
		return range;
	}


    /**
     * @return the requests
     */
    public ArrayList<Request> getRequests() {
        return requests;
    }
    
    /**
     * To convert SubscriberStation object to a String Object to display all the Requests
     */
    @Override
    public String toString() {
        return id + " : " +x+" : "+y+ " : " + maxBandwidth+ " : " + frameSize+" : "+range;
    }

    /**
     * Scheduling of all Requests of Base Station on basis of priority of the
     * request and assign the requests in priority queue.
     *
     * @param allowedRequests
     * @return
     */
    public ArrayList<Request> scheduling(ArrayList<Request> allowedRequests) {
    	requests = Request.arrangeRequestsOnBasisOfPriority(allowedRequests);// Sort the Requests on basis of priority
    	ArrayList<Request> servedRequests = new ArrayList<Request>();
        for (Request i : requests) {
            i.setCurrentAllocatedRequest(i.getMaxRequiredRequest());// Allot the Required Bandwidth to the Current Request
            servedRequests.add(i); //Store the Served Requests in the queue 
        }
        return servedRequests; // Return the served Requests
    }
    
    /**
     * Read Base Station File as Input
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static ArrayList<BaseStation> readInput() throws FileNotFoundException, IOException {
        FileInputStream fstream = new FileInputStream(Constants.CURR_DIR + Constants.BS_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        /**
         * Read the file line by line using br.readline() function Get the
         * number of inputs from corresponding file at first
         */
        String strLine;
        ArrayList<BaseStation> baseStations;
        strLine = br.readLine();
        count= Integer.parseInt(strLine);

        /**
         *
         * Assign objects using data from file input
         */
        baseStations = new ArrayList<BaseStation>();
        for (int i = 0; i < getCount(); i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            double x = Double.parseDouble(splited[0]);
            double y = Double.parseDouble(splited[1]);
            double maxBandwidth = Double.parseDouble(splited[2]);
            double frameSize = Double.parseDouble(splited[3]);
            double range = Double.parseDouble(splited[4]);
            baseStations.add(new BaseStation(i, x, y, maxBandwidth, frameSize,range));
        }

        /**
         * Close input stream and return array of objects
         */
        br.close();

        return baseStations;
    }
    
    /**
	 * Display Base Station File
     * @param title
     * @param format
     * @param bases
     */
	public static void displayBaseStation(String title,String format,ArrayList<BaseStation> bases) {
		System.out.println(title);
		int size = bases.size();
		System.out.println(size);
		System.out.println(format);
		for (BaseStation i : bases) {
			System.out.println(i);
		}
	}


}
