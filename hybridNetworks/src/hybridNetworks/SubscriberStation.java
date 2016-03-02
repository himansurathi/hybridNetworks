package hybridNetworks;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import hybridNetworks.Request;

public class SubscriberStation {
	int id;
	double x;
	double y;
	double maxBandwidth;
	PriorityQueue<Request> requests;
	BaseStation baseObject;
	static int count;
	
	public List<Request> scheduling(List<Request> nodeRequests ){
//		requests=Request.arrangeRequestsOnBasisOfTime(nodeRequests);
		int currentBandwidth=0;
		List<Request> allowedRequest=new ArrayList<Request>();
		for(Request i: nodeRequests){
			if((currentBandwidth+i.maxRequiredRequest)<maxBandwidth){
				allowedRequest.add(i);
				currentBandwidth+=i.maxRequiredRequest;
			}
		}
		return allowedRequest;
		
	}

	public static SubscriberStation[] readInput(){
		return null;
	}

}
