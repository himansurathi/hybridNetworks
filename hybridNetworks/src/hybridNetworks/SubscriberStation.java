package hybridNetworks;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import hybridNetworks.Request;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubscriberStation {

    int id;
    double x;
    double y;
    double maxBandwidth;
    PriorityQueue<Request> requests;
    BaseStation baseObject;
    static int count;

    public SubscriberStation(int idVar, double xVar, double yVar, double maxBandwidthVar) {
        id = idVar ;
        x = xVar ;
        y = yVar ;
        maxBandwidth = maxBandwidthVar ;
    }
    

    public List<Request> scheduling(List<Request> nodeRequests) {
//		requests=Request.arrangeRequestsOnBasisOfTime(nodeRequests);
        int currentBandwidth = 0;
        List<Request> allowedRequest = new ArrayList<Request>();
        for (Request i : nodeRequests) {
            if ((currentBandwidth + i.maxRequiredRequest) < maxBandwidth) {
                allowedRequest.add(i);
                currentBandwidth += i.maxRequiredRequest;
            }
        }
        return allowedRequest;

    }

    public static SubscriberStation[] readInput() throws FileNotFoundException, IOException {
        FileInputStream fstream = new FileInputStream("");
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
        SubscriberStation subscriberStations[];
        int inputCount = 0;
        strLine = br.readLine();
        inputCount = Integer.parseInt(strLine);
        //System.out.println(inputCount);
        subscriberStations = new SubscriberStation[inputCount];
        for (int i = 0; i < inputCount; i++) {
            strLine = br.readLine();
            String[] splited = strLine.split("\\s+");
            int x = Integer.parseInt(splited[0]);
            int y = Integer.parseInt(splited[1]);
            int maxBandwidth = Integer.parseInt(splited[2]);
            subscriberStations[i] = new SubscriberStation(i, x, y, maxBandwidth);
        }

//Close the input stream
        br.close();

        return subscriberStations;

    }

}
