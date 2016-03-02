package hybridNetworks;
import hybridNetworks.Constants.*;

public class HybridNetwork {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * Read Input from all files in input directory
		 * and assign the values to respective member variables.
		 * 
		 * 
		 */
		
		for(int i=0;i<Constants.SIMULATION_TIME;i++){
			/**
			 * Assign all requests to their respective node Stations,
			 * assign all appropriate nodes to Subscriber Stations
			 * and assign all appropriate subscriber station to base station 
			 */
			
			/**
			 * Allot requests to priority queues of subscriber station
			 * and Allot allowed requests to priority queue of base station 
			 * by running scheduling function ofSubscriber Station
			 */
			
			/**
			 * Run Scheduling function of Base Station and record a
			 * log in the format: time \t requestid \t requestAllocated \t nodeid \t subscriberid \t basestationid
			 */
		}
	}

}
