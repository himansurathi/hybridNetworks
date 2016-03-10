package hybridNetworks;

import java.util.Comparator;

public class RequestPriorityComparator implements Comparator<Request>{
	/**
	 * Compare two request object on basis of their priority
	 */
	@Override
	public int compare(Request r1,Request r2){
		if(r2.getPriority()>r1.getPriority())
			return 1;
		else if(r2.getPriority()<r1.getPriority())
			return -1;
		else 
			return (r1.getNodeId()-r2.getNodeId());
	}
}
