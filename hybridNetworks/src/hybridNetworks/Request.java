package hybridNetworks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Request {

    private static int count;
    private int id;
    private int priority;
    private int age;
    private double currentAllocatedRequest;
    private double maxRequiredRequest;
    private double durationRequest;
    private double startTimeRequest;
    private int nodeId;
    private double weight;
    private Node nodeObject;

    private void calculateWeight(){
    	/**
    	 * Calculate the final measurement of priority of a request
    	 * alpha*age+(1-alpha)*deadline
    	 */
  
    }
    
    public static ArrayList<Request> sortByWeight(ArrayList<Request> requests){
    	/**
    	 * Sort all the requests available according to  weights
    	 */
    	
    	return null;
    }
       
	/**
     * 
     * @param idVar
     * @param priorityVar
     * @param maxRequiredRequestVar
     * @param durationRequestVar
     * @param startTimeRequestVar
     * @param nodeIdVar
     */
    Request(int idVar, int priorityVar, double maxRequiredRequestVar,
    		double durationRequestVar,double startTimeRequestVar,int nodeIdVar) {
        //   count = countVar;
        id = idVar;
        priority = priorityVar;
        currentAllocatedRequest = 0;
        maxRequiredRequest = maxRequiredRequestVar;
        durationRequest = durationRequestVar;
        startTimeRequest=startTimeRequestVar;
        nodeId=nodeIdVar;
        age=0;
        weight=priority;
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
	 * @param currentAllocatedRequest the currentAllocatedRequest to set
	 */
	public void setCurrentAllocatedRequest(double currentAllocatedRequest) {
		this.currentAllocatedRequest = currentAllocatedRequest;
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
    public static ArrayList<Request> readInput() throws FileNotFoundException, IOException {

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
        ArrayList<Request> requests;
        strLine = br.readLine();
        count = Integer.parseInt(strLine);

        /**
         *
         * Assign objects using data from file input
         */
        requests = new ArrayList<Request>();
        for (int i = 0; i < getCount(); i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            int priority = Integer.parseInt(splited[0]);
            double maxRequiredRequest = Double.parseDouble(splited[1]);
            double durationRequest = Double.parseDouble(splited[2]);
            double startTimeRequest = Double.parseDouble(splited[3]);
            int  nodeId = Integer.parseInt(splited[4]);
            requests.add(new Request(i, priority, maxRequiredRequest, durationRequest,startTimeRequest,nodeId));
        }

        /**
         * Close input stream and return array of objects
         */
        br.close();

        return requests;
    }

    /**
	 * Display Request File
     * @param title
     * @param format
     * @param requests
     */
	public static void displayRequest(String title,String format,ArrayList<Request> requests) {
		System.out.println(title);
		int out = requests.size();
		System.out.println(out);
		System.out.println(format);
		for (Request i : requests) {
			System.out.println(i);
		}
	}

	/**
     * Arrange all the Requests on basis of time and store them in a priority queue
     *
     * @param requests
     * @return
     */
    public static ArrayList<Request> arrangeRequestsOnBasisOfTime(ArrayList<Request> requests) {
        RequestTimeComparator timeComparator = new RequestTimeComparator(); //Creating a user defined comparator for time
        PriorityQueue<Request> sortedRequests = new PriorityQueue<Request>(count, timeComparator); // Store the requests in Priority Queue on basis of timeComparator defined.
        for (Request i : requests) {
            sortedRequests.add(i);
        }
        ArrayList<Request> requestList=new ArrayList<Request>();
        while(!sortedRequests.isEmpty()){
        	Request current=sortedRequests.poll();
        	requestList.add(current);
        }
        return requestList; //Return the priority Queue.
    }

    /**
     * Arrange all the Requests on basis of priority and store them in a
     * priority queue
     *
     * @param requests
     * @return
     */
    public static ArrayList<Request> arrangeRequestsOnBasisOfPriority(ArrayList<Request> requests) {
        RequestPriorityComparator priorityComparator = new RequestPriorityComparator();//Creating a user defined comparator for priority
        PriorityQueue<Request> sortedRequests = new PriorityQueue<Request>(getCount()+4, priorityComparator); // Store the requests in Priority Queue on basis of priorityComparator defined.
        for (Request i : requests) {
            sortedRequests.add(i);
        }
        ArrayList<Request> requestList=new ArrayList<Request>();
        while(!sortedRequests.isEmpty()){
        	Request current=sortedRequests.poll();
        	requestList.add(current);
        }
        return requestList; // Returns the Priority Queue
    }

	public String generateLog() {
		// TODO Auto-generated method stub
		String s=getId()+" \t\t "+getPriority()+" \t\t\t "+getCurrentAllocatedRequest()
		+"   \t\t    "+getNodeId()+"   \t\t "+getNodeObject().getSubscriberId()+"   \t\t\t "
		+getNodeObject().getStationObject().getBaseId();
		
		return s;
	}
}

