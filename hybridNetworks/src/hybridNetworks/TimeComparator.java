package hybridNetworks;
import java.util.Comparator;

public class TimeComparator implements Comparator<Request> {
	/**
	 * Compare two request object on basis of their time
	 */
	
	@Override
	public int compare(Request r1,Request r2){
		return (r1.startDate).compareTo(r2.startDate);
	}
}
