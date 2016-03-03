package hybridNetworks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.PriorityQueue;

public class Request {

    static int count;
    int id;
    int priority;
    double currentAllocatedRequest;
    double maxRequiredRequest;
    double durationRequest;
    double startTimeRequest;
	int nodeId;
    Node nodeObject;
    
    /**
     * 
     * @param idVar
     * @param priorityVar
     * @param maxRequiredRequestVar
     * @param durationRequestVar
     * @param startTimeRequestVar
     * @param nodeIdVar
     */
    Request(int idVar, int priorityVar, double maxRequiredRequestVar, double durationRequestVar,double startTimeRequestVar,int nodeIdVar) {
        //   count = countVar;
        id = idVar;
        priority = priorityVar;
        currentAllocatedRequest = 0;
        maxRequiredRequest = maxRequiredRequestVar;
        durationRequest = durationRequestVar;
        startTimeRequest=startTimeRequestVar;
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
     * @return the priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * @return the currentAllocatedRequest
     */
    public double getCurrentAllocatedRequest() {
        return currentAllocatedRequest;
    }

    /**
     * @return the maxRequiredRequest
     */
    public double getMaxRequiredRequest() {
        return maxRequiredRequest;
    }

    /**
     * @return the durationRequest
     */
    public double getDurationRequest() {
        return durationRequest;
    }

    /**
	 * @return the startTimeRequest
	 */
	public double getStartTimeRequest() {
		return startTimeRequest;
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
     * To convert Request object to a String Object to display all the Requests
     */
    @Override
    public String toString() {
        return id + " : " + priority + " : " + currentAllocatedRequest + " : " + maxRequiredRequest + " : " + durationRequest + " : " + startTimeRequest+" : "+nodeId;
    }
    
    /**
     * Read Request File as Input
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Request[] readInput() throws FileNotFoundException, IOException {

        /**
         * Open file input stream for reading
         */
        FileInputStream fstream = new FileInputStream(Constants.CURR_DIR + Constants.REQUEST_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        /**
         * Read the file line by line using br.readline() function Get the
         * number of inputs from corresponding file at first
         */
        String strLine;
        Request requests[];
        int inputCount = 0;
        strLine = br.readLine();
        inputCount = Integer.parseInt(strLine);

        /**
         *
         * Assign objects using data from file input
         */
        requests = new Request[inputCount];
        for (int i = 0; i < inputCount; i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            int priority = Integer.parseInt(splited[0]);
            double maxRequiredRequest = Double.parseDouble(splited[1]);
            double durationRequest = Double.parseDouble(splited[2]);
            double startTimeRequest = Double.parseDouble(splited[3]);
            int  nodeId = Integer.parseInt(splited[4]);
            requests[i] = new Request(i, priority, maxRequiredRequest, durationRequest,startTimeRequest,nodeId);
        }

        /**
         * Close input stream and return array of objects
         */
        br.close();

        return requests;
    }

    /**
     * Arrange all the Requests on basis of time and store them in a priority queue
     *
     * @param requests
     * @return
     */
    public static PriorityQueue<Request> arrangeRequestsOnBasisOfTime(List<Request> requests) {
        TimeComparator timeComparator = new TimeComparator(); //Creating a user defined comparator for time
        PriorityQueue<Request> sortedRequests = new PriorityQueue<Request>(count, timeComparator); // Store the requests in Priority Queue on basis of timeComparator defined.
        for (Request i : requests) {
            sortedRequests.add(i);
        }
        return sortedRequests; //Return the priority Queue.
    }

    /**
     * Arrange all the Requests on basis of priority and store them in a
     * priority queue
     *
     * @param requests
     * @return
     */
    public static PriorityQueue<Request> arrangeRequestsOnBasisOfPriority(List<Request> requests) {
        PriorityComparator priorityComparator = new PriorityComparator();//Creating a user defined comparator for priority
        PriorityQueue<Request> sortedRequests = new PriorityQueue<Request>(count, priorityComparator); // Store the requests in Priority Queue on basis of priorityComparator defined.
        for (Request i : requests) {
            sortedRequests.add(i);
        }
        return sortedRequests; // Returns the Priority Queue
    }

}
