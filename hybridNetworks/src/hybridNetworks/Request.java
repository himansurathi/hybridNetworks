package hybridNetworks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

public class Request {

    static int count;
    int id;
    int priority;
    double currentAllocatedRequest;
    double maxRequiredRequest;
    double durationRequest;
    Node nodeObject;
    Date startDate;

    Request(int idVar, int priorityVar, double maxRequiredRequestVar, double durationRequestVar ) {
     //   count = countVar;
        id= idVar;
        priority = priorityVar ;
        currentAllocatedRequest = 0;
        maxRequiredRequest = maxRequiredRequestVar ;
        durationRequest = durationRequestVar ;
        
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
	 * @return the nodeObject
	 */
	public Node getNodeObject() {
		return nodeObject;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	public static Request[] readInput() throws FileNotFoundException, IOException {
        //Channge the  following file path to absolute path for the input 
    	FileInputStream fstream = new FileInputStream(Constants.CURR_DIR+Constants.REQUEST_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        Request requests[];
        int inputCount = 0;
        strLine = br.readLine();
        inputCount = Integer.parseInt(strLine);
        //System.out.println(inputCount);
        requests = new Request[inputCount];
        for (int i = 0; i < inputCount; i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            int priority = Integer.parseInt(splited[0]);
            int maxRequiredRequest = Integer.parseInt( splited[1]);
            int durationRequest = Integer.parseInt( splited[2]);
            requests[i] = new Request(i,priority, maxRequiredRequest , durationRequest );
        }

//Close the input stream
        br.close();

        return requests;
    }
    /**
     * To convert Request object to a String Object to display all the Requests
     */
    @Override
    public String toString() {
        return id + " : " + priority + " : " + currentAllocatedRequest + " : " + maxRequiredRequest + " : " + durationRequest + " : " + startDate + " : ";
    }
    /**
     * Arrange all the Requests on basis of time and store them in a priority queue
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
     * Arrange all the Requests on basis of priority and store them in a priority queue
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
