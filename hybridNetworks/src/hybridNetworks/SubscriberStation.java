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

    int id;
    double x;
    double y;
    double maxBandwidth;
    PriorityQueue<Request> requests;
    BaseStation baseObject;
    static int count;
    
    /**
     * Constructor 
     * @param idVar
     * @param xVar
     * @param yVar
     * @param maxBandwidthVar
     */
    public SubscriberStation(int idVar, double xVar, double yVar, double maxBandwidthVar) {
        id = idVar ;
        x = xVar ;
        y = yVar ;
        maxBandwidth = maxBandwidthVar ;
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
	 * @return the requests
	 */
	public PriorityQueue<Request> getRequests() {
		return requests;
	}

	/**
	 * @return the baseObject
	 */
	public BaseStation getBaseObject() {
		return baseObject;
	}

	/**
	 * @return the count
	 */
	public static int getCount() {
		return count;
	}

	/**
     * Scheduling of the requests available at each subscriber stations according to 
     * maximum allowed requests that can be transferred for each of the individual 
     * subscriber- base station links.
     * The functions returns all those allowed requests
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

    public static SubscriberStation[] readInput() throws FileNotFoundException, IOException {
        FileInputStream fstream = new FileInputStream("");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        SubscriberStation subscriberStations[];
        int inputCount = 0;
        strLine = br.readLine();
        inputCount = Integer.parseInt(strLine);
        //System.out.println(inputCount);
        subscriberStations = new SubscriberStation[inputCount];
        for (int i = 0; i < inputCount; i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            int x = Integer.parseInt(splited[0]);
            int y = Integer.parseInt(splited[1]);
            int maxBandwidth = Integer.parseInt(splited[2]);
            subscriberStations[i] = new SubscriberStation(i, x, y, maxBandwidth);
        }

//Close the input stream
        br.close();

        return subscriberStations;

    }

}
