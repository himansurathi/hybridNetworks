package hybridNetworks;

public class Constants {
	static final double SUBSCRIBER_BANDWIDTH=Double.MAX_VALUE; //Bandwidth of Subscriber Station
	static final double BASE_BANDWIDTH=Double.MAX_VALUE;//Bandwidth of Base Station
	static final double SIMULATION_TIME=1; //Simulation Running Time
	static final int TEST_NO=2; //Simulation Running Time
	static final String BS_FILE="/input/testcase"+TEST_NO+"/baseStations.txt"; //Base Station Input File
	static final String SS_FILE="/input/testcase"+TEST_NO+"/subscriberStations.txt"; //Subscriber Station Input File
	static final String NODE_FILE="/input/testcase"+TEST_NO+"/nodes.txt"; //Node Input File Name
	static final String REQUEST_FILE="/input/testcase"+TEST_NO+"/requests.txt";//Request Input File Name
	static final String MOVEMENT_FILE="/input/testcase"+TEST_NO+"/movements.txt";//Movement Input File Name
	static final String OUTPUT_DIR="/output"; // Output Directory
	static final String CURR_DIR = System.getProperty("user.dir"); // Current Directory of application
	static final boolean DEBUG = true;
}
