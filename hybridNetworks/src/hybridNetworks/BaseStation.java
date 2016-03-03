package hybridNetworks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class BaseStation {

    static int count;
    int id;
    double x;
    double y;
    double maxBandwidth;
    double frameSize;
    PriorityQueue<Request> requests;

    public BaseStation(int idVar, double xVar, double yVar, double maxBandwidthVar, double frameSizeVar) {
        id = idVar;
        x = xVar;
        y = yVar;
        maxBandwidth = maxBandwidthVar;
        frameSize = frameSizeVar;
    }

    public List<Request> scheduling(List<Request> allowedRequests){
        requests=Request.arrangeRequestsOnBasisOfPriority(allowedRequests);
        List<Request> servedRequests=new ArrayList<Request>();
        for(Request i: requests){
            i.currentAllocatedRequest=i.maxRequiredRequest;
            servedRequests.add(i);
            requests.remove(i);
        }
        return servedRequests;
    }

    public static BaseStation[] readInput() throws FileNotFoundException, IOException {
        FileInputStream fstream = new FileInputStream("/home/ashis/Documents/Projects/hybridNetworks/hybridNetworks/src/hybridNetworks/input/baseStations.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        BaseStation baseStations[];
        int inputCount = 0;
        strLine = br.readLine();
        inputCount = Integer.parseInt(strLine);
        //System.out.println(inputCount);
        baseStations = new BaseStation[inputCount];
        for (int i = 0; i < inputCount; i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            int x = Integer.parseInt(splited[0]);
            int y = Integer.parseInt(splited[1]);
            int maxBandwidth = Integer.parseInt(splited[2]);
            int frameSize = Integer.parseInt(splited[3]);
            baseStations[i] = new BaseStation(i, x, y, maxBandwidth, frameSize);
        }

//Close the input stream
        br.close();

        return baseStations;

    }

}
