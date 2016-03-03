package hybridNetworks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Node {

    static int count;
    int id;
    double x;
    double y;
    SubscriberStation subscriberObject;
    
    /**
     * Constructor 
     * @param idVar
     * @param xVar
     * @param yVar
     */
    public Node(int idVar, int xVar, int yVar) {
        id = idVar;
        x = xVar ;
        y = yVar ;
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
	 * @return the x
	 */
	public double getX() {
		return x;
	}


	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}


	/**
	 * @return the subscriberObject
	 */
	public SubscriberStation getSubscriberObject() {
		return subscriberObject;
	}


	public static Node[] readInput() throws FileNotFoundException, IOException {
        FileInputStream fstream = new FileInputStream(Constants.CURR_DIR+Constants.NODE_FILE);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        Node nodes[];
        int inputCount = 0;
        strLine = br.readLine();
        inputCount = Integer.parseInt(strLine);
        //System.out.println(inputCount);
        nodes = new Node[inputCount];
        for (int i = 0; i < inputCount; i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            int x = Integer.parseInt(splited[0]);
            int y = Integer.parseInt(splited[1]);
            nodes[i] = new Node(i, x, y);
        }

//Close the input stream
        br.close();

        return nodes;

    }

}
