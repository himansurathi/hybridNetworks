package hybridNetworks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Testcase {
    
	public static void generateTestcase(int i) {
		
			String specificTestcase=Constants.INPUT_SUBDIR+i;
			File inputDir=new File(Constants.CURR_DIR+Constants.INPUT_DIR,specificTestcase);
		
			try{
				if(inputDir.exists()){
	        		if(!Statistics.deleteDirectory(inputDir))
	        			System.out.println("Could not remove directory");
	        	}
    			inputDir.mkdir();
    		}
    		catch(SecurityException se){
    			System.out.println("Output Directory cannot be created!!! Please Check Permissions");
    		}
			
        	try{
        		Constants.setFile(i);
	        	File file = new File(Constants.CURR_DIR+Constants.BS_FILE);
	        	if(!file.exists())
	        		file.createNewFile();
				FileWriter writer = new FileWriter(file);
				int number=1;
				String data="0.0 0.0 8000 10 5000.0";
				writer.append(number+"\n"+data);
				writer.flush();
				writer.close();

				file = new File(Constants.CURR_DIR+Constants.SS_FILE);
	        	if(!file.exists())
	        		file.createNewFile();
				writer = new FileWriter(file);
				number=2;
				data="5000.0 0.0 300 20 0";
				writer.append(number+"\n"+data+"\n");				
				data="0.0 5000.0 300 20 0";
				writer.append(data);
				writer.flush();
				writer.close();
	        	
	        	file = new File(Constants.CURR_DIR+Constants.MOVEMENT_FILE);
	        	if(!file.exists())
	        		file.createNewFile();
				writer = new FileWriter(file);
				number=0;
				writer.append(number+"\n");
				writer.flush();
				writer.close();

				file = new File(Constants.CURR_DIR+Constants.NODE_FILE);
	        	if(!file.exists())
	        		file.createNewFile();
				writer = new FileWriter(file);
				number=20;
				double initX=4990.0;
				double initY=0.0;
				data=initX+" "+initY+" -1";
				writer.append(number+"\n"+data+"\n");
				initX=5000.0;
				initY=1.0;
				for(int j=1;j<10;j++){
					data=initX+" "+initY+" -1";
					writer.append(data+"\n");				
					initX+=1;
					initY+=1;
				}
				initX=0.0;
				initY=4990.0;
				data=initX+" "+initY+" -1";
				writer.append(data+"\n");
				initX=1.0;
				initY=5000.0;
				for(int j=1;j<10;j++){
					data=initX+" "+initY+" -1";
					writer.append(data+"\n");				
					initX+=1;
					initY+=1;
				}
				writer.flush();
				writer.close();
				
				file = new File(Constants.CURR_DIR+Constants.REQUEST_FILE);
	        	if(!file.exists())
	        		file.createNewFile();
				writer = new FileWriter(file);
				number=20+(int)(Math.random()*20);
				writer.append(number+"\n");
				int count=1;
				int priorityNumber=(int)(0.1*number);
				for(int j=1;j<=priorityNumber;j++){
					int requestSize=64+(int)(Math.random()*63);
					int nodeNumber=(int)(Math.random()*20);
					data="4 "+requestSize+" 1 0 "+nodeNumber;
					writer.append(data+"\n");
					count++;
				}
				priorityNumber=(int)(0.2*number);
				for(int j=1;j<=priorityNumber;j++){
					int requestSize=30+(int)(Math.random()*35);
					int nodeNumber=(int)(Math.random()*20);
					data="3 "+requestSize+" 1 0 "+nodeNumber;
					writer.append(data+"\n");
					count++;
				}
				for(int j=count;j<=number;j++){
					int priority=1+(int)(Math.random()*2);
					int requestSize=1+(int)(Math.random()*30);
					int nodeNumber=(int)(Math.random()*20);
					data=priority+" "+requestSize+" 1 0 "+nodeNumber;
					writer.append(data+"\n");
				}
				writer.flush();
				writer.close();
	        	
        	}
        	 catch (IOException e) {
        		 System.out.println("Error in creating Testcase"+i+" File");
     		}

	}

	public static void generateTestcaseWithFrames(int i) {
		
		String specificTestcase=Constants.INPUT_SUBDIR+i;
		File inputDir=new File(Constants.CURR_DIR+Constants.INPUT_DIR,specificTestcase);
	
		try{
			if(inputDir.exists()){
        		if(!Statistics.deleteDirectory(inputDir))
        			System.out.println("Could not remove directory");
        	}
			inputDir.mkdir();
		}
		catch(SecurityException se){
			System.out.println("Output Directory cannot be created!!! Please Check Permissions");
		}
		
    	try{
    		Constants.setFile(i);
        	File file = new File(Constants.CURR_DIR+Constants.BS_FILE);
        	if(!file.exists())
        		file.createNewFile();
			FileWriter writer = new FileWriter(file);
			int number=1;
			String data="0.0 0.0 8000 10 5000.0";
			writer.append(number+"\n"+data);
			writer.flush();
			writer.close();

			file = new File(Constants.CURR_DIR+Constants.SS_FILE);
        	if(!file.exists())
        		file.createNewFile();
			writer = new FileWriter(file);
			number=2;
			data="5000.0 0.0 128 20 0";
			writer.append(number+"\n"+data+"\n");				
			data="0.0 5000.0 128 20 0";
			writer.append(data);
			writer.flush();
			writer.close();
        	
        	file = new File(Constants.CURR_DIR+Constants.MOVEMENT_FILE);
        	if(!file.exists())
        		file.createNewFile();
			writer = new FileWriter(file);
			number=0;
			writer.append(number+"\n");
			writer.flush();
			writer.close();

			file = new File(Constants.CURR_DIR+Constants.NODE_FILE);
        	if(!file.exists())
        		file.createNewFile();
			writer = new FileWriter(file);
			number=20;
			double initX=4990.0;
			double initY=0.0;
			data=initX+" "+initY+" -1";
			writer.append(number+"\n"+data+"\n");
			initX=5000.0;
			initY=1.0;
			for(int j=1;j<10;j++){
				data=initX+" "+initY+" -1";
				writer.append(data+"\n");				
				initX+=1;
				initY+=1;
			}
			initX=0.0;
			initY=4990.0;
			data=initX+" "+initY+" -1";
			writer.append(data+"\n");
			initX=1.0;
			initY=5000.0;
			for(int j=1;j<10;j++){
				data=initX+" "+initY+" -1";
				writer.append(data+"\n");				
				initX+=1;
				initY+=1;
			}
			writer.flush();
			writer.close();
			
			file = new File(Constants.CURR_DIR+Constants.REQUEST_FILE);
        	if(!file.exists())
        		file.createNewFile();
			writer = new FileWriter(file);
			number=80+(int)(Math.random()*20);
			writer.append(number+"\n");
			int count=1;
			int priorityNumber=(int)(0.1*number);
			for(int j=1;j<=priorityNumber;j++){
				int requestSize=64+(int)(Math.random()*65);
				int nodeNumber=(int)(Math.random()*20);
				int duration=1+(int)(Math.random()*10);
				data="4 "+requestSize+" "+duration+" 0 "+nodeNumber;
				writer.append(data+"\n");
				count++;
			}
			priorityNumber=(int)(0.2*number);
			for(int j=1;j<=priorityNumber;j++){
				int requestSize=30+(int)(Math.random()*35);
				int nodeNumber=(int)(Math.random()*20);
				int duration=1+(int)(Math.random()*10);
				data="3 "+requestSize+" "+duration+" 0 "+nodeNumber;
				writer.append(data+"\n");
				count++;
			}
			for(int j=count;j<=number;j++){
				int priority=1+(int)(Math.random()*2);
				int requestSize=1+(int)(Math.random()*30);
				int nodeNumber=(int)(Math.random()*20);
				int duration=1+(int)(Math.random()*10);
				data=priority+" "+requestSize+" "+duration+" 0 "+nodeNumber;
				writer.append(data+"\n");
			}
			writer.flush();
			writer.close();
        	
    	}
    	 catch (IOException e) {
    		 System.out.println("Error in creating Testcase"+i+" File");
 		}

}

}
