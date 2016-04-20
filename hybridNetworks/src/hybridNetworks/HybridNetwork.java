package hybridNetworks;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class HybridNetwork {

	private static ArrayList<Request> requests;
	private static ArrayList<Node> nodes;
    private static ArrayList<Station> stations ;
	private static ArrayList<SubscriberStation> subscribers;
	private static ArrayList<BaseStation> bases;
	private static ArrayList<Movement> movements;
	
	public static void main(String[] args) throws IOException{
		varyFrameGap();
	}
	
	private static void varyFrameSize() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Constants.REFRESH_RUN=true;
		
		Constants.setFile(6);
		for(int i=0;i<=500;i=i+10){
			//System.out.println("Generating"+ i+"th Testcase");
		    //Testcase.generateTestcaseWithFrames(i);
			System.out.println("Processin g"+ i+"th Frame");
			Constants.FRAME_SIZE=i;
			wimax2WifiWithFrames();
			Constants.REFRESH_RUN=false;
		}		
	}

	private static void varyFrameGap() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Constants.REFRESH_RUN=true;
		
		Constants.setFile(6);
		for(double i=0.05;i<1;i=i+0.05){
			//System.out.println("Generating"+ i+"th Testcase");
		    //Testcase.generateTestcaseWithFrames(i);
			System.out.println("Processin g"+ i+"th Frame Time");
			Constants.FRAME_SIMULATION_GAP=i;
			wimax2WifiWithFrames();
			Constants.REFRESH_RUN=false;
		}		
	}

	private static void varyAlPHA() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Constants.REFRESH_RUN=true;
		
		Constants.setFile(6);
		for(double i=0.0;i<1.0;i=i+0.05){
			//System.out.println("Generating"+ i+"th Testcase");
		    //Testcase.generateTestcaseWithFrames(i);
			System.out.println("Processin g"+ i+"th Alpha");
			Constants.ALPHA=i;
			wimax2WifiWithFrames();
			Constants.REFRESH_RUN=false;
		}		
	}

	private static void frameRequest() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Constants.REFRESH_RUN=true;

		for(int i=Constants.INIT_TEST_NO;i<Constants.INIT_TEST_NO+Constants.NUMBER_OF_TESTCASES;i++){
			//System.out.println("Generating"+ i+"th Testcase");
		    //Testcase.generateTestcaseWithFrames(i);
			System.out.println("Processin g"+ i+"th Testcase");
			Constants.setFile(i);
			wimax2WifiWithFrames();
			Constants.REFRESH_RUN=false;
		}		
	}

	private static void wimax2WifiWithFrames() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		/*
		 * Read Input from all files in input directory and assign the values to
		 * respective member variables.
		 */
		requests=new ArrayList<Request>();
		nodes=new ArrayList<Node>();
		subscribers=new ArrayList<SubscriberStation>();
		bases=new ArrayList<BaseStation>();
		movements=new ArrayList<Movement>();
		
		/**
		 * Get all request data from request file
		 */
		requests = Request.readInput();
		String title="\nRequest File";
		String format="Id : Priority : CurrentAllocatedRequest : MaxRequiredRequest : DurationRequest : StartTime : NodeId";
		if(Constants.READ)
			Request.displayRequest(title,format,requests);

		/**
		 * Get all node data from node file
		 */
		nodes=Node.readInput();
		title="\nNode File";
		format="id : initialX : initialY : currentX : currentY : subscriberId";
		if(Constants.READ)
			Node.displayNodes(title,format,nodes);
		
		/**
		 * Get all Subscriber Station data from subscriberStation file
		 */
		subscribers=SubscriberStation.readInput();
		title="\nSubscriber Station File";
		format="id : x : y : maxBandwidth : range : baseId";
		if(Constants.READ)
			SubscriberStation.displaySubscriberStation(title,format,subscribers);

		/**
		 * Get all base station data from base station file
		 */
		bases=BaseStation.readInput();
		title="\nBase Station File";
		format="id : x : y : maxBandwidth : frameSize : range";
		if(Constants.READ)
			BaseStation.displayBaseStation(title,format,bases);

		/**
		 * Get all movement data from movement file
		 */
		movements=Movement.readInput();
		title="\nMovement File";
		format="id : angle : speed : startMovement : durationMovement : nodeId";
		if(Constants.READ)
			Movement.displayMovement(title,format,movements);

		/**
		 * Assign all obtained data to static array variables
		 * Did not Understand
		 */
		
		
		/**
		 * Sort all the Request with respect to time
		 * Sort all the Movement with respect to time
		 */
		ArrayList<Request> arrangedRequest=Request.arrangeRequestsOnBasisOfTime(requests);
//		PriorityQueue<Movement> arrangedMovement=Movement.arrangeMovementOnBasisOfTime(movements);
		
		subscribers=allotSubscriberToBaseStation(subscribers,bases);
                
		
		/**
		 * Combining base and subscriber stations to a common arraylist
		 */
	        stations = new ArrayList<Station>();
	        stations.addAll(bases);
	        stations.addAll(subscribers);

		/**
		 * We assume that the simulation is running for
		 * Constants.SIMULATION_TIME units of time. For each instance of loop we
		 * calculate the scenario for that instant and allocate accordingly
		 */
                
			ArrayList<Request> totalRequestAtTime=new ArrayList<Request>();
        
		for (double i = 0; i < Constants.SIMULATION_TIME; i++) {

            for(double j=0;j<Constants.FRAME_SIMULATION_TIME;j+=Constants.FRAME_SIMULATION_GAP){

            	ArrayList<Request> requestAtTime=getRequestAtSpecificTime(arrangedRequest,i+j);
            	requestAtTime=allotRequestToSpecificNodes(requestAtTime, nodes);

            	nodes=calculateDistance(nodes,stations); 
			
				if(Constants.DEBUG){
					title="\n\nNode Assignment";
					format="id : initialX : initialY : currentX : currentY : subscriberId";
					Node.displayNodes(title,format,nodes);
				}
			
				for(BaseStation base:bases){
					ArrayList<Request> requestAllowedAtTime=totalRequestWithFrameSubscriberStation(requestAtTime,subscribers,base.getId(),i+j);
		                
	                if(Constants.DEBUG){
						title="\n\nSubscriber Station Allowed Requests";
						format="Id : Priority : CurrentAllocatedRequest : MaxRequiredRequest : DurationRequest : StartTime : NodeId";
						Request.displayRequest(title,format,requestAllowedAtTime);
					}
				
	                /**
	                 * Assigning requests under the base stations connected directly to node 
	                 */
                
	                if(Constants.DEBUG){
	                	System.out.println("\n \n Requests from nodes connected to  base station in any way served");
	                	System.out.println("Id : Priority : CurrentAllocatedRequest : MaxRequiredRequest : DurationRequest : StartTime : NodeId");
	                }
                    for(Request request:requestAtTime){
                    	if(request.getNodeObject().getStationObject() instanceof BaseStation)
                    	{
                    		if(request.getNodeObject().getStationObject() == base)
                			{
                				requestAllowedAtTime.add(request);
                				if(Constants.DEBUG)
                				System.out.println(request);
                			}
                    	}
                    }
                
    
                ArrayList<Request> requestServedAtTime=base.schedulingWithWeight(requestAllowedAtTime,i+j);
				if(Constants.DEBUG){
					title="\n\nBase Station Served Requests";
					format="Id : Priority : CurrentAllocatedRequest : MaxRequiredRequest : DurationRequest : StartTime : NodeId";
					Request.displayRequest(title,format,requestServedAtTime);
				}
				
				ArrayList<Request> tempRequest=new ArrayList<Request>();
				for(Request r1: totalRequestAtTime){
					int flag=0;
					for(Request r2: requestServedAtTime){
						if(r1.getId()==r2.getId()){
							flag=1;
						}
					}
					if(flag==0)
						tempRequest.add(r1);
				}
				totalRequestAtTime=tempRequest;
				totalRequestAtTime.addAll(requestServedAtTime);
				
			}
		}
	}
	Statistics.findResult(totalRequestAtTime,arrangedRequest);
}

	private static void staticRequest() throws IOException{
		Constants.REFRESH_RUN=true;

		for(int i=Constants.INIT_TEST_NO;i<Constants.INIT_TEST_NO+Constants.NUMBER_OF_TESTCASES;i++){
			System.out.println("Generating"+ i+"th Testcase");
			Testcase.generateTestcase(i);
			System.out.println("Processing"+ i+"th Testcase");
			wimax2Wifi();
			Constants.REFRESH_RUN=false;
		}
	}
	
	private static void wimax2Wifi() throws FileNotFoundException, IOException {

		/*
		 * Read Input from all files in input directory and assign the values to
		 * respective member variables.
		 */
		requests=new ArrayList<Request>();
		nodes=new ArrayList<Node>();
		subscribers=new ArrayList<SubscriberStation>();
		bases=new ArrayList<BaseStation>();
		movements=new ArrayList<Movement>();
		
		/**
		 * Get all request data from request file
		 */
		requests = Request.readInput();
		String title="\nRequest File";
		String format="Id : Priority : CurrentAllocatedRequest : MaxRequiredRequest : DurationRequest : StartTime : NodeId";
		Request.displayRequest(title,format,requests);

		/**
		 * Get all node data from node file
		 */
		nodes=Node.readInput();
		title="\nNode File";
		format="id : initialX : initialY : currentX : currentY : subscriberId";
		Node.displayNodes(title,format,nodes);
		
		/**
		 * Get all Subscriber Station data from subscriberStation file
		 */
		subscribers=SubscriberStation.readInput();
		title="\nSubscriber Station File";
		format="id : x : y : maxBandwidth : range : baseId";
		SubscriberStation.displaySubscriberStation(title,format,subscribers);

		/**
		 * Get all base station data from base station file
		 */
		bases=BaseStation.readInput();
		title="\nBase Station File";
		format="id : x : y : maxBandwidth : frameSize : range";
		BaseStation.displayBaseStation(title,format,bases);

		/**
		 * Get all movement data from movement file
		 */
		movements=Movement.readInput();
		title="\nMovement File";
		format="id : angle : speed : startMovement : durationMovement : nodeId";
		Movement.displayMovement(title,format,movements);

		/**
		 * Assign all obtained data to static array variables
		 * Did not Understand
		 */
		
		
		/**
		 * Sort all the Request with respect to time
		 * Sort all the Movement with respect to time
		 */
		ArrayList<Request> arrangedRequest=Request.arrangeRequestsOnBasisOfTime(requests);
//		PriorityQueue<Movement> arrangedMovement=Movement.arrangeMovementOnBasisOfTime(movements);
		
		subscribers=allotSubscriberToBaseStation(subscribers,bases);
                
		
		/**
		 * Combining base and subscriber stations to a common arraylist
		 */
                stations = new ArrayList<Station>();
		stations.addAll(bases);
                stations.addAll(subscribers);
		
		/**
		 * We assume that the simulation is running for
		 * Constants.SIMULATION_TIME units of time. For each instance of loop we
		 * calculate the scenario for that instant and allocate accordingly
		 */
		for (double i = 0; i < Constants.SIMULATION_TIME; i++) {


			/**
			 * 1. Get all relevant requests for the current time in a List<Request>
			 * 2. Check to which node the request belong, assign the node 
			 * object to the request. 
			 * r.startTime<=i && (r.startTime+r.duration)>=i && r.currentAllocated!=r.maxAllocated
			 * 3.Get the current co-ordinates of the nodes
			*/
			ArrayList<Request> requestAtTime=getRequestAtSpecificTime(arrangedRequest,i);
			requestAtTime=allotRequestToSpecificNodes(requestAtTime, nodes);

			/**
			 * 1. Assign all nodes to particular stations by checking
			 * the nearest possible station subscriber station
			 */
			nodes=calculateDistance(nodes,stations); 
			
			if(Constants.DEBUG){
				title="\n\nNode Assignment";
				format="id : initialX : initialY : currentX : currentY : subscriberId";
				Node.displayNodes(title,format,nodes);
			}
			
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
			ArrayList<Request> totalRequestAtTime=new ArrayList<Request>();
			for(BaseStation base:bases){
				ArrayList<Request> requestAllowedAtTime=totalRequestSubscriberStation(requestAtTime,subscribers,base.getId());
		                
                if(Constants.DEBUG){
					title="\n\nSubscriber Station Allowed Requests";
					format="Id : Priority : CurrentAllocatedRequest : MaxRequiredRequest : DurationRequest : StartTime : NodeId";
					Request.displayRequest(title,format,requestAllowedAtTime);
				}
				
                                /**
                                 * Assigning requests under the base stations connected directly to node 
                                 */
                                
                                System.out.println("\n \n Requests from nodes connected to  base station in any way served");
                                       System.out.println("Id : Priority : CurrentAllocatedRequest : MaxRequiredRequest : DurationRequest : StartTime : NodeId");
                                for(Request request:requests){
                                    if(request.getNodeObject().getStationObject() instanceof BaseStation)
                                    {
                                        if(request.getNodeObject().getStationObject() == base)
                                        {
                                            requestAllowedAtTime.add(request);
                                     
                                            System.out.println(request);
                                        }
                                    }
                                }
                                
                ArrayList<Request> requestServedAtTime=base.scheduling(requestAllowedAtTime);
				if(Constants.DEBUG){
					title="\n\nBase Station Served Requests";
					format="Id : Priority : CurrentAllocatedRequest : MaxRequiredRequest : DurationRequest : StartTime : NodeId";
					Request.displayRequest(title,format,requestServedAtTime);
				}
				totalRequestAtTime.addAll(requestServedAtTime);
			}

			Statistics.findResult(totalRequestAtTime,requestAtTime);
			
			System.out.println("\n********************************\t\t Log Format \t\t************************************\n");
			System.out.println("time \t requestId \t requestPriority \t requestAllocated \t nodeid \t subscriberid \t\t basestationid\n");
			for(Request log: totalRequestAtTime)
			System.out.println(i+" \t\t "+log.generateLog());

		}

	}

	private static ArrayList<SubscriberStation> allotSubscriberToBaseStation(
			ArrayList<SubscriberStation> subscribers,ArrayList<BaseStation> bases) {

		for(BaseStation base:bases){
			for(SubscriberStation subscriber:subscribers){
				if(subscriber.getBaseId()==base.getId()){
					subscriber.setBaseObject(base);
				}
			}
		}
		return subscribers;
	}
	private static ArrayList<Request> getRequestAtSpecificTime(ArrayList<Request> arrangedRequest,double time) {
		ArrayList<Request> requestAtTime=new ArrayList<Request>();
		for(Request r: arrangedRequest){
			
			if(r.getStartTimeRequest()<=time && 
			(r.getStartTimeRequest()+r.getDurationRequest())>=time &&
			(r.getCurrentAllocatedRequest()<r.getMaxRequiredRequest())){
				requestAtTime.add(r);
			}
		}
		return requestAtTime;
	}
	private static ArrayList<Request> allotRequestToSpecificNodes(ArrayList<Request> requestlist,
																  ArrayList<Node> nodelist){
		for(Request r: requestlist){
			for(Node n:nodelist){
				if(r.getNodeId()==n.getId()){
					r.setNodeObject(n);
					break;
				}
			}
		}
		return requestlist;
	}
	private  static ArrayList<Node> calculateDistance(ArrayList<Node> nodeList,ArrayList<Station> stationList){
		for(Node n:nodeList){
			int flag=0;
			for(Station station:stationList){
				
				if(n.calcDistance(station)<=station.getRange()){
					n.setSubscriberObject(station);
                                        if (station instanceof SubscriberStation)
                                        {
					n.setSubscriberId(station.getId());}
                                        else
                                        {
                                            n.setSubscriberId(-1);
                                        }
					flag=1;
					break;
				}
			}
			if(flag==0){
				n.setSubscriberId(-1);
				n.setSubscriberObject(null);				
			}
		}
		return nodeList;
	}
	private static ArrayList<Request> totalRequestSubscriberStation(ArrayList<Request> requestlist
												,ArrayList<SubscriberStation> subscriberlist,
												int baseId){

		ArrayList<Request> totalStationRequest=new ArrayList<Request>();
		for(SubscriberStation station:subscriberlist){
			ArrayList<Request> p=new ArrayList<Request>();
			if(station.getBaseId()!=baseId)
				continue;
			for(Request r:requestlist){
				if(r.getNodeObject().getSubscriberId()==station.getId()){
					p.add(r);
				}
			}
			ArrayList<Request> individualStationRequest=station.scheduling(p);
			totalStationRequest.addAll(individualStationRequest);
		}
		return totalStationRequest;
	}
	private static ArrayList<Request> totalRequestWithFrameSubscriberStation(ArrayList<Request> requestlist
			,ArrayList<SubscriberStation> subscriberlist,
			int baseId,double time){

		ArrayList<Request> totalStationRequest=new ArrayList<Request>();
		for(SubscriberStation station:subscriberlist){
		ArrayList<Request> p=new ArrayList<Request>();
		if(station.getBaseId()!=baseId)
		continue;
		for(Request r:requestlist){
		if(r.getNodeObject().getSubscriberId()==station.getId()){
		p.add(r);
		}
		}
		ArrayList<Request> individualStationRequest=station.schedulingWithFrames(p,time);
		totalStationRequest.addAll(individualStationRequest);
		}
		return totalStationRequest;
	}

}

