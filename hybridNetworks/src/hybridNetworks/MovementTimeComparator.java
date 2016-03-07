package hybridNetworks;

import java.util.Comparator;

public class MovementTimeComparator implements Comparator<Movement>{
	@Override
	public int compare(Movement m1,Movement m2){
		if(m1.getStartMovement()<m2.getStartMovement())
			return -1;
		else if(m1.getStartMovement()>m2.getStartMovement())
			return 1;
		else 
			return 0;
	}

}
