package hybridNetworks;
import hybridNetworks.Constants.*;
import hybridNetworks.Request;
import java.io.IOException;

public class HybridNetwork {
    private static Request[] requests;

   // Request[] requests;
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	//requests = new Request[10];	
            
		/*
		 * Read Input from all files in input directory
		 * and assign the values to respective member variables.
		 * 
		 * 
		 */
        requests = Request.readInput();
        int out  = requests.length;
        for(Request i:requests){
        	System.out.println(i);
        }
        System.out.println(out);
		
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
