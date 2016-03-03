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

    public static Request[] readInput() throws FileNotFoundException, IOException {
        //Channge the  following file path to absolute path for the input 
        FileInputStream fstream = new FileInputStream("/home/ashis/Documents/Projects/hybridNetworks/hybridNetworks/src/hybridNetworks/input/requests.txt");
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

    @Override
    public String toString() {
        return id + " : " + priority + " : " + currentAllocatedRequest + " : " + maxRequiredRequest + " : " + durationRequest + " : " + startDate + " : ";
    }

    public static PriorityQueue<Request> arrangeRequestsOnBasisOfTime(List<Request> requests) {
        TimeComparator timeComparator = new TimeComparator();
        PriorityQueue<Request> sortedRequests = new PriorityQueue<Request>(count, timeComparator);
        for (Request i : requests) {
            sortedRequests.add(i);
        }
        return sortedRequests;
    }

    public static PriorityQueue<Request> arrangeRequestsOnBasisOfPriority(List<Request> requests) {
        PriorityComparator priorityComparator = new PriorityComparator();
        PriorityQueue<Request> sortedRequests = new PriorityQueue<Request>(count, priorityComparator);
        for (Request i : requests) {
            sortedRequests.add(i);
        }
        return sortedRequests;
    }

}
