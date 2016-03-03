package hybridNetworks;

import java.io.IOException;

public class HybridNetwork {

	private static Request[] requests;

	public static void main(String[] args) throws IOException {

		/*
		 * Read Input from all files in input directory and assign the values to
		 * respective member variables.
		 */

		/**
		 * Get all request data from request file
		 */
		requests = Request.readInput();
		int out = requests.length;
		System.out.println(out);
		System.out.println("Id : Priority : CurrentAllocatedRequest : MaxRequiredRequest : DurationRequest : StartTime : NodeId");
		for (Request i : requests) {
			System.out.println(i);
		}
		/**
		 * Get all node data from node file
		 */

		/**
		 * Get all Subscriber Station data from subscriberStation file
		 */

		/**
		 * Get all base station data from base station file
		 */

		/**
		 * Get all movement data from movement file
		 */

		/**
		 * Assign all obtained data to static array variables
		 */
		
		/**
		 * Sort all the Request with respect to time
		 * Sort all the Movement with respect to time
		 */

		/**
		 * We assume that the simulation is running for
		 * Constants.SIMULATION_TIME units of time. For each instance of loop we
		 * calculate the scenario for that instant and allocate accordingly
		 */
		for (int i = 0; i < Constants.SIMULATION_TIME; i++) {


			/**
			 * 1. Get all relevant requests for the current time in a List<Request>
			 * 2. Check to which node the request belong, assign the node 
			 * object to the request. 
			 * r.startTime<=i && (r.startTime+r.duration)>=i && r.currentAllocated!=r.maxAllocated
			 * 3.Get the current co-ordinates of the nodes
			*/

			/*
			 * 2. Assign all nodes to particular stations by checking
			 * the nearest possible station first base station and then subscriber
			 * station
			 */

			/**
			 * 1. Allot all requests under a subscriber station to priority
			 * queues of subscriber station 
			 * 2. Run the scheduling function of each subscriber station
			 * 3. Append the resulting array of requests to array of corresponding 
			 * base station 
			 */

			/**
			 * 1. After All subscriber stations are dealt with, run the scheduling 
			 * function of base station.
			 * 2. After running Scheduling function of Base Station, Record a
			 * log in the format: time \t requestid \t requestAllocated \t
			 * nodeid \t subscriberid \t basestationid Repeat the loop until
			 * time finishes
		   */
		}
	}

}
