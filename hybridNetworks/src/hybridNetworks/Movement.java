package hybridNetworks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Movement {
	static int count;
	int id;
	double angle;
	double speed;
	double durationMovement;
	Node nodeObject;

	/**
     * Constructor 
	 * @param idVar
	 * @param angleVar
	 * @param speedVar
	 * @param durationMovementVar
	 */
    public Movement(int idVar, double angleVar, double speedVar, double durationMovementVar ) {
        id = idVar ;
        angle =  angleVar ;
        speed = speedVar ;
        durationMovement = durationMovementVar ;
    }
	
        
	/**
	 * @return the count
	 */
	public static int getCount() {
		return count;
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}


	/**
	 * @return the speed
	 */
	public double getSpeed() {
		return speed;
	}


	/**
	 * @return the durationMovement
	 */
	public double getDurationMovement() {
		return durationMovement;
	}


	/**
	 * @return the nodeObject
	 */
	public Node getNodeObject() {
		return nodeObject;
	}


	public static Movement[] readInput() throws FileNotFoundException, IOException{
        FileInputStream fstream = new FileInputStream(Constants.CURR_DIR+Constants.MOVEMENT_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        Movement movements[];
        int inputCount = 0;
        strLine = br.readLine();
        inputCount = Integer.parseInt(strLine);
        //System.out.println(inputCount);
        movements = new Movement[inputCount];
        for (int i = 0; i < inputCount; i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            int angle = Integer.parseInt(splited[0]);
            int speed = Integer.parseInt(splited[1]);
            int durationMovement = Integer.parseInt(splited[2]);
            movements[i] = new Movement(i, angle, speed, durationMovement);
        }

//Close the input stream
        br.close();

        return movements;
	}

}
