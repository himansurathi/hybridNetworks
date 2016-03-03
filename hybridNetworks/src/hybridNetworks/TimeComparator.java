package hybridNetworks;
import java.util.Comparator;

public class TimeComparator implements Comparator<Request> {
	/**
	 * Compare two request object on basis of their time
	 */
	
	@Override
	public int compare(Request r1,Request r2){
		if(r1.startTimeRequest<r2.startTimeRequest)
			return -1;
		else if(r1.startTimeRequest>r2.startTimeRequest)
			return 1;
		else 
			return 0;
	}
}
