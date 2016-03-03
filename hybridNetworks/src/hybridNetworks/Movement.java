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

    public Movement(int idVar, double angleVar, double speedVar, double durationMovementVar ) {
        id = idVar ;
        angle =  angleVar ;
        speed = speedVar ;
        durationMovement = durationMovementVar ;
    }
	
        
	public static Movement[] readInput() throws FileNotFoundException, IOException{
        FileInputStream fstream = new FileInputStream("");
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
