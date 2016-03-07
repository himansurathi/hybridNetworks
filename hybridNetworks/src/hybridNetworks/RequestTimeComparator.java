package hybridNetworks;
import java.util.Comparator;

public class RequestTimeComparator implements Comparator<Request> {
	/**
	 * Compare two request object on basis of their time
	 */
	
	@Override
	public int compare(Request r1,Request r2){
		if(r1.getStartTimeRequest()<r2.getStartTimeRequest())
			return -1;
		else if(r1.getStartTimeRequest()>r2.getStartTimeRequest())
			return 1;
		else 
			return (r1.getNodeId()-r2.getNodeId());
	}
}
