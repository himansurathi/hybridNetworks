package hybridNetworks;
import java.util.Comparator;

public class TimeComparator implements Comparator<Request> {
	
	@Override
	public int compare(Request r1,Request r2){
		return (r1.startDate).compareTo(r2.startDate);
	}
}
