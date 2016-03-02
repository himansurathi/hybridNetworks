package hybridNetworks;

import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

public class Request{
	static int count;
	int id;
	int priority;
	double currentAllocatedRequest;
	double maxRequiredRequest;
	double durationRequest;
	Node nodeObject;
	Date startDate;
	
	public static Request[] readInput(){
		return null;
	}
	
	
	@Override
    public String toString()
    {
        return id+" : "+priority+" : "+currentAllocatedRequest+" : "+maxRequiredRequest+" : "+durationRequest+" : "+startDate+" : ";
    }
	
	
	public static PriorityQueue<Request> arrangeRequestsOnBasisOfTime(List<Request> requests){
		TimeComparator timeComparator = new TimeComparator();
		PriorityQueue<Request> sortedRequests=new PriorityQueue<Request>(count,timeComparator);
		for (Request i: requests){
			sortedRequests.add(i);
		}
		return sortedRequests;
	}

	public static PriorityQueue<Request> arrangeRequestsOnBasisOfPriority(List<Request> requests){
		PriorityComparator priorityComparator = new PriorityComparator();
		PriorityQueue<Request> sortedRequests=new PriorityQueue<Request>(count,priorityComparator);
		for (Request i: requests){
			sortedRequests.add(i);
		}
		return sortedRequests;
	}
	
}

