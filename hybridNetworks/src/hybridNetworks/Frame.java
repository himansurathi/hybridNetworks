package hybridNetworks;

import java.util.ArrayList;

public class Frame {
	private int id;
	private int time;
	private int freeSize;
	private double UGCsize;
	private double RTPSsize;
	private double NRTPSsize;
	private double BEsize;
	private ArrayList<Request> requests;
	private BaseStation baseStation;
	
	public Frame initializeFrame(ArrayList<Request> UGC,ArrayList<Request> RTP,
			ArrayList<Request> NRTP,ArrayList<Request> BE){
		/**
		 * 1. Calculate and Adjust frame bandwidth for each of the classes according to available
		 * request in each of the classes
		 * 2. Extract the topmost element from each of the queues according to 
		 * the bandwidth (60% UGC,20% RTPS,10% NRTPS, 10% BE)
		 */
		return this;
	}
	
	public void displayFrame(){
		
	}
	
	public void generateLogFrame(){
		
	}
}
