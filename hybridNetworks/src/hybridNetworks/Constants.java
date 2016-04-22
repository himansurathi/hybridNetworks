package hybridNetworks;

public class Constants {
	static final double SUBSCRIBER_BANDWIDTH=Double.MAX_VALUE; //Bandwidth of Subscriber Station
	static final double BASE_BANDWIDTH=Double.MAX_VALUE;//Bandwidth of Base Station
	static final double SIMULATION_TIME=10; //Simulation Running Time
	static final int NUMBER_OF_TESTCASES=100; //Number of Testcases
	static final int INIT_TEST_NO=6; //Initial TestCase Number
	static final String INPUT_DIR="/input"; // Input Directory
	static final String INPUT_SUBDIR="/testcase";
	static final String OUTPUT_DIR="/output"; // Output Directory
	static final String CURR_DIR = System.getProperty("user.dir"); // Current Directory of application
	static final String BS_NAME="/baseStations.txt";
	static final String SS_NAME="/subscriberStations.txt";
	static final String NODES_NAME="/nodes.txt";
	static final String REQUEST_NAME="/requests.txt";
	static final String MOVEMENT_NAME="/movements.txt";
	static String BS_FILE;
	static String SS_FILE;
	static String NODE_FILE;
	static String REQUEST_FILE;
	static String MOVEMENT_FILE;
	static final String[] DIR_NAME={"Total","RequestClass","AllocationClass","Plots"};
    static final boolean DEBUG = false;
    static final boolean READ = false;
    static final boolean LOG = false;
	static boolean REFRESH_RUN=true;
	static final String DELIMITER=" ";
	static final double FRAME_SIMULATION_TIME = 1;
	static int FRAME_SIZE = 300;
	static double FRAME_SIMULATION_GAP = 0.5;
	static double UGC_PERCENT = 0.6;
	static double RTP_PERCENT = 0.2;
	static double NRTP_PERCENT = 0.1;
	static double BE_PERCENT = 0.1;
	static double ALPHA = 0.0;
	static final int DURATION_REQUEST_TIME=10;
	static int SUBSCRIBER_BASE_BANDWIDTH=300;
	
	
	public static void setFile(int TEST_NO){
		BS_FILE=INPUT_DIR+INPUT_SUBDIR+TEST_NO+BS_NAME; //Base Station Input File
		SS_FILE=INPUT_DIR+INPUT_SUBDIR+TEST_NO+SS_NAME; //Subscriber Station Input File
		NODE_FILE=INPUT_DIR+INPUT_SUBDIR+TEST_NO+NODES_NAME; //Node Input File Name
		REQUEST_FILE=INPUT_DIR+INPUT_SUBDIR+TEST_NO+REQUEST_NAME;//Request Input File Name
		MOVEMENT_FILE=INPUT_DIR+INPUT_SUBDIR+TEST_NO+MOVEMENT_NAME;//Movement Input File Name	
	}
}
