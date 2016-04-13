package hybridNetworks;

import java.util.Comparator;

public class RequestWeightComparator implements Comparator<Request> {
	@Override
	public int compare(Request r1,Request r2){
		if(r1.getWeight()<r2.getWeight())
			return 1;
		else if(r1.getWeight()>r2.getWeight())
			return -1;
		else 
			return (r1.getPriority()-r2.getPriority());
	}
}
