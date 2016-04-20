package hybridNetworks;

import java.util.ArrayList;

public class Frame {
	private static int count;
	private int id;
	private double time;
	private double freeSize;
	private double UGCsize;
	private double RTPSsize;
	private double NRTPSsize;
	private double BEsize;
	private ArrayList<Request> requests;
	private BaseStation baseStation;
	
	Frame(double time,BaseStation baseObject){
		this.id=count;
		count++;
		this.time=time;
		freeSize=Constants.FRAME_SIZE;
		UGCsize=Constants.UGC_PERCENT*Constants.FRAME_SIZE;
		RTPSsize=Constants.RTP_PERCENT*Constants.FRAME_SIZE;
		NRTPSsize=Constants.NRTP_PERCENT*Constants.FRAME_SIZE;
		BEsize=Constants.BE_PERCENT*Constants.FRAME_SIZE;
		requests=new ArrayList<Request>();
		this.baseStation=baseObject;
	}
	
	public Frame initializeFrame(ArrayList<Request> UGC,ArrayList<Request> RTP,
			ArrayList<Request> NRTP,ArrayList<Request> BE){
		/**
		 * 1. Calculate and Adjust frame bandwidth for each of the classes according to available
		 * request in each of the classes
		 * 2. Extract the topmost element from each of the queues according to 
		 * the bandwidth (60% UGC,20% RTPS,10% NRTPS, 10% BE)
		 */

		requests=allocationFrame(UGC,UGCsize,4);
		RTPSsize+=UGCsize;
		requests.addAll(allocationFrame(RTP,RTPSsize ,3));
		NRTPSsize+=RTPSsize;
		requests.addAll(allocationFrame(NRTP, NRTPSsize,2));
		BEsize+=NRTPSsize;
		requests.addAll(allocationFrame(BE, BEsize,1));
		freeSize=BEsize;
		return this;
	}

	public Frame initializeFrameSecondStratergy(ArrayList<Request> UGC,ArrayList<Request> RTP,
			ArrayList<Request> NRTP,ArrayList<Request> BE){
		/**
		 * 1. Calculate and Adjust frame bandwidth for each of the classes according to available
		 * request in each of the classes
		 * 2. Extract the topmost element from each of the queues according to 
		 * the bandwidth (60% UGC,20% RTPS,10% NRTPS, 10% BE)
		 */

		requests=allocationFrame(UGC,UGCsize,4);
		double total=(Constants.RTP_PERCENT+Constants.NRTP_PERCENT+Constants.BE_PERCENT);
		RTPSsize+=(UGCsize*Constants.RTP_PERCENT/total);
		NRTPSsize+=(UGCsize*Constants.NRTP_PERCENT/total);
		BEsize+=(UGCsize*Constants.BE_PERCENT/total);
		
		requests.addAll(allocationFrame(RTP,RTPSsize ,3));
		total=(Constants.NRTP_PERCENT+Constants.BE_PERCENT);
		NRTPSsize+=(RTPSsize*Constants.NRTP_PERCENT/total);
		BEsize+=(RTPSsize*Constants.BE_PERCENT/total);
		
		requests.addAll(allocationFrame(NRTP, NRTPSsize,2));
		BEsize+=NRTPSsize;
		requests.addAll(allocationFrame(BE, BEsize,1));
		freeSize=BEsize;
		return this;
	}
	
	/**
	 * 
	 * @param request
	 * @param size
	 * @param priority
	 * @return
	 */
	private ArrayList<Request> allocationFrame(ArrayList<Request> request,double size,int priority) {
		double currentAllocation=0.0;
		ArrayList<Request> requestFrameAllocation=new ArrayList<Request>();
		for(Request r: request){
			if((r.getMaxRequiredRequest()-r.getCurrentAllocatedRequest())<=size){
				currentAllocation=r.getMaxRequiredRequest()-r.getCurrentAllocatedRequest();
			}
			else{
				currentAllocation=size;
			}
			r.setCurrentAllocatedRequest(currentAllocation);
			requestFrameAllocation.add(r);
			size-=currentAllocation;
		}
		switch(priority) {
		case 1:
			BEsize=size;
			break;
		case 2:
			NRTPSsize=size;
			break;
		case 3:
			RTPSsize=size;
			break;
		case 4:
			UGCsize=size;
			break;
		default:
			UGCsize=size;
			break;
		}
		return requestFrameAllocation;
	}
	
    @Override
    public String toString() {
        return id + " : " +time+" : "+freeSize+ " : " + UGCsize+ " : " + RTPSsize+" : "+NRTPSsize+" : "+BEsize;
    }

	public static void displayFrame(String title,String format,ArrayList<Frame> frames){
		System.out.println(title);
		int size = frames.size();
		System.out.println(size);
		System.out.println(format);
		for (Frame i : frames) {
			System.out.println(i);
		}
	}
	
	public String generateLogFrame(){
		// TODO Auto-generated method stub
		
		String s="\n********************************\t\t Log Format \t\t************************************\n";
		s+="id \t\t time \t\t freeSize \t\t UGCsize \t\t RTPSsize \t\t NRTPSsize \t\t BEsize\n";
		
		s+="\n"+getId()+" \t\t "+getTime()+" \t\t\t "+getFreeSize()
		+"   \t\t    "+getUGCsize()+"   \t\t "+getRTPSsize()+"   \t\t\t "
		+getNRTPSsize()+"   \t\t   "+getBEsize()+"\n\n";
		
		s+="requestId \t\t\t requestPriority \t\t\t requestAllocated \t\t\t nodeid \t\t\t subscriberid \t\t\t basestationid\n";
		
		for(Request r: requests){
			s+=r.generateLog()+"\n";
		}
		return s;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the time
	 */
	public double getTime() {
		return time;
	}

	/**
	 * @return the freeSize
	 */
	public double getFreeSize() {
		return freeSize;
	}

	/**
	 * @return the uGCsize
	 */
	public double getUGCsize() {
		return UGCsize;
	}

	/**
	 * @return the rTPSsize
	 */
	public double getRTPSsize() {
		return RTPSsize;
	}

	/**
	 * @return the nRTPSsize
	 */
	public double getNRTPSsize() {
		return NRTPSsize;
	}

	/**
	 * @return the bEsize
	 */
	public double getBEsize() {
		return BEsize;
	}

	/**
	 * @return the requests
	 */
	public ArrayList<Request> getRequests() {
		return requests;
	}

	/**
	 * @return the baseStation
	 */
	public BaseStation getBaseStation() {
		return baseStation;
	}
}
