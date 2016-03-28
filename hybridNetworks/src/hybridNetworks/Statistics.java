package hybridNetworks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class Statistics {
	
	public static boolean deleteDirectory(File directory) {
	    if(directory.exists()){
	        File[] files = directory.listFiles();
	        if(files!=null){
	            for(int i=0; i<files.length; i++) {
	                if(files[i].isDirectory()) {
	                    deleteDirectory(files[i]);
	                }
	                else {
	                    files[i].delete();
	                }
	            }
	        }
	    }
	    return(directory.delete());
	}
    
	private static void findRequest(ArrayList<Request> allocatedRequests, ArrayList<Request> demandRequests){
    	
		HashMap<Integer,Integer> numberOfTotalRequestClass=new HashMap<Integer,Integer>();
    	HashMap<Integer,Double> demandTotalRequestClass=new HashMap<Integer,Double>();
    	HashMap<Integer,Integer> numberOfServedRequestClass=new HashMap<Integer,Integer>();
    	HashMap<Integer,Double> demandServedRequestClass=new HashMap<Integer,Double>();
    	int totalRequest=0;
    	int totalAllocationRequest=0;
    	int servedRequest=0;
    	int servedAllocationRequest=0;
    	
    	for(Request r: demandRequests){
    		int classes=r.getPriority();
    		
    		if(numberOfTotalRequestClass.containsKey(classes))
    			numberOfTotalRequestClass.put(classes, numberOfTotalRequestClass.get(classes)+1);
    		else
    			numberOfTotalRequestClass.put(classes, 1);
    			
    		if(demandTotalRequestClass.containsKey(classes))
    			demandTotalRequestClass.put(classes, demandTotalRequestClass.get(classes)+r.getMaxRequiredRequest());
    		else
    			demandTotalRequestClass.put(classes, r.getMaxRequiredRequest());
    			
        	totalRequest++;
        	totalAllocationRequest+=r.getMaxRequiredRequest();
         }

    	for(Request r: allocatedRequests){
    		int classes=r.getPriority();
    		
    		if(numberOfServedRequestClass.containsKey(classes))
    			numberOfServedRequestClass.put(classes, numberOfServedRequestClass.get(classes)+1);
	  		else
    			numberOfServedRequestClass.put(classes, 1);
    		
    		if(demandServedRequestClass.containsKey(classes))
    			demandServedRequestClass.put(classes, demandServedRequestClass.get(classes)+r.getCurrentAllocatedRequest());
        	else
    			demandServedRequestClass.put(classes, r.getCurrentAllocatedRequest());
    		
    		servedRequest++;
        	servedAllocationRequest+=r.getCurrentAllocatedRequest();
         }
    	
    	if(Constants.REFRESH_RUN){
        	File outputDir=new File(Constants.CURR_DIR,Constants.OUTPUT_DIR);
        	if(outputDir.exists()){
        		if(!deleteDirectory(outputDir))
        			System.out.println("Could not remove directory");
        	}
        	if(Constants.DEBUG)
        		System.out.println("Creating Directory ");
    		try{
    			outputDir.mkdir();
    			for(String s: Constants.DIR_NAME){
    				File subdir = new File(outputDir,s);
    				subdir.mkdir();
    			}
    		}
    		catch(SecurityException se){
    			System.out.println("Output Directory cannot be created!!! Please Check Permissions");
    		}
    	}
    	    	
    	try {
    		if(Constants.DEBUG){
    			System.out.println("Creating Files");
    		}
        	File file = new File(Constants.CURR_DIR+Constants.OUTPUT_DIR+"/Total/Request.txt");
        	if(!file.exists())
        		file.createNewFile();
			FileWriter writer = new FileWriter(file,true);
			writer.append(totalRequest+Constants.DELIMITER+servedRequest+"\n");
			writer.flush();
			writer.close();

        	file = new File(Constants.CURR_DIR+Constants.OUTPUT_DIR+"/Total/Allocation.txt");
        	if(!file.exists())
				file.createNewFile();
			writer = new FileWriter(file,true);
			writer.append(totalAllocationRequest+Constants.DELIMITER+servedAllocationRequest+"\n");
			writer.flush();
			writer.close();
			
			for(Integer i:numberOfTotalRequestClass.keySet()){

				file = new File(Constants.CURR_DIR+Constants.OUTPUT_DIR+"/RequestClass/Number"+i+".txt");
	        	if(!file.exists())
					file.createNewFile();
				writer = new FileWriter(file,true);
				if(numberOfServedRequestClass.get(i)!=null)
					writer.append(numberOfTotalRequestClass.get(i)+Constants.DELIMITER+numberOfServedRequestClass.get(i)+"\n");
				else
					writer.append(numberOfTotalRequestClass.get(i)+Constants.DELIMITER+"0\n");
				writer.flush();
				writer.close();
			}

			for(Integer i:demandTotalRequestClass.keySet()){

				file = new File(Constants.CURR_DIR+Constants.OUTPUT_DIR+"/AllocationClass/Allocation"+i+".txt");
	        	if(!file.exists())
					file.createNewFile();
				writer = new FileWriter(file,true);
				if(demandServedRequestClass.get(i)!=null)
					writer.append(demandTotalRequestClass.get(i)+Constants.DELIMITER+demandServedRequestClass.get(i)+"\n");
				else
					writer.append(demandTotalRequestClass.get(i)+Constants.DELIMITER+"0\n");
				writer.flush();
				writer.close();
			}

		} catch (IOException e) {
			 System.out.println("Error in displaying Testcase files!!!!!");
		}
    }
	
	public static void findResult(ArrayList<Request> srequest,ArrayList<Request> drequest){
		findRequest(srequest, drequest);
	}
}
