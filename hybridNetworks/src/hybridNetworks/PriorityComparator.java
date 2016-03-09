package hybridNetworks;

import java.util.Comparator;

public class PriorityComparator implements Comparator<Request>{
	/**
	 * Compare two request object on basis of their priority
	 */
	@Override
	public int compare(Request r1,Request r2){
		return r2.getPriority()-r1.getPriority();
	}
}
