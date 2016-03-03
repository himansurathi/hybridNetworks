package hybridNetworks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BaseStation {

    static int count;
    int id;
    double x;
    double y;
    double maxBandwidth;
    double frameSize;
    PriorityQueue<Request> requests;
    
    /**
     * Constructor 
     * @param idVar
     * @param xVar
     * @param yVar
     * @param maxBandwidthVar
     * @param frameSizeVar
     */
    public BaseStation(int idVar, double xVar, double yVar, double maxBandwidthVar, double frameSizeVar) {
        id = idVar;
        x = xVar;
        y = yVar;
        maxBandwidth = maxBandwidthVar;
        frameSize = frameSizeVar;
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
	 * @return the requests
	 */
	public PriorityQueue<Request> getRequests() {
		return requests;
	}
	/**
     * Scheduling of all Requests of Base Station on basis of priority of the request
     * and assign the requests in priority queue.
     * @param allowedRequests
     * @return
     */
    public List<Request> scheduling(List<Request> allowedRequests){
        requests=Request.arrangeRequestsOnBasisOfPriority(allowedRequests);// Sort the Requests on basis of priority
        List<Request> servedRequests=new ArrayList<Request>();
        for(Request i: requests){
            i.currentAllocatedRequest=i.maxRequiredRequest;// Allot the Required Bandwidth to the Current Request
            servedRequests.add(i); //Store the Served Requests in the queue 
            requests.remove(i); // Delete the requests already served 
        }
        return servedRequests; // Return the served Requests
    }

    public static BaseStation[] readInput() throws FileNotFoundException, IOException {
        FileInputStream fstream = new FileInputStream(Constants.CURR_DIR+Constants.BS_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        BaseStation baseStations[];
        int inputCount = 0;
        strLine = br.readLine();
        inputCount = Integer.parseInt(strLine);
        //System.out.println(inputCount);
        baseStations = new BaseStation[inputCount];
        for (int i = 0; i < inputCount; i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            int x = Integer.parseInt(splited[0]);
            int y = Integer.parseInt(splited[1]);
            int maxBandwidth = Integer.parseInt(splited[2]);
            int frameSize = Integer.parseInt(splited[3]);
            baseStations[i] = new BaseStation(i, x, y, maxBandwidth, frameSize);
        }

//Close the input stream
        br.close();

        return baseStations;

    }

}
