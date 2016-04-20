import pylab
import matplotlib.pyplot as plt
import os

onlyfiles =["output\AllocationClass\Allocation1.txt","output\AllocationClass\Allocation2.txt","output\AllocationClass\Allocation3.txt","output\AllocationClass\Allocation4.txt",
            "output\RequestClass\Number1.txt","output\RequestClass\Number2.txt","output\RequestClass\Number3.txt","output\RequestClass\Number4.txt","output\Total\Allocation.txt"
            ,"output\Total\Request.txt"]
def demand_served():
    labelx=["Bandwidth Demanded by Requests of BE Class","Bandwidth Demanded by Requests of nRTPS Class","Bandwidth Demanded by Requests of RTPS Class","Bandwidth Demanded by Requests of UGC Class",
        "Number of Requests Demanded in BE Class","Number of Requests Demanded in nRTPS Class","Number of Requests Demanded in RTPS Class","Number of Requests Demanded in UGC Class",
        "Total Bandwidth Demanded","Total Number of Requests Demanded"]
    labely=["Bandwidth Served by Requests of BE Class","Bandwidth Served by Requests of nRTPS Class","Bandwidth Served by Requests of RTPS Class","Bandwidth Served by Requests of UGC Class",
        "Number of Requests Served in BE Class","Number of Requests Served in nRTPS Class","Number of Requests Served in RTPS Class","Number of Requests Served in UGC Class",
        "Total Bandwidth Served","Total Number of Requests Served"]
    title=["Bandwidth Allocation for BE Class","Bandwidth Allocation for nRTPS Class","Bandwidth Allocation for RTPS Class","Bandwidth Allocation for UGC Class",
       "Number of Requests in BE Class","Number of Requests in nRTPS Class","Number of Requests in RTPS Class","Number of Requests in UGC Class","Total Bandwidth Allocation"
       ,"Total Number of Requests"]
    index=0
    for filename in onlyfiles:
        print filename
        datalist=sorted(open(filename).readlines(), key=lambda line: float(line.split(' ')[0]))
        x=[]
        y=[]
        prevx=0.0
        prevy=0.0
        count=1.0
        for data in datalist:
            num=data.strip().split(' ');
            if float(num[0])!=prevx:
                if prevx!=0:
                    x.append(prevx)
                    y.append(prevy/count)
                prevx=float(num[0])
                prevy=float(num[1])
                count=1.0
            else:
                prevy+=float(num[1])
                count+=1
        x.append(prevx)
        y.append(prevy/count)
        print x
        print y
        pylab.plot(x,y,'--bo')
        pylab.title(title[index])
        pylab.xlabel(labelx[index])
        pylab.ylabel(labely[index])
        newname=filename[filename.rfind("\\")+1:filename.rfind('.')]
        plt.savefig("output\\Plots\\"+title[index]+".png",bbox_inches='tight')
        plt.close()
        index+=1

def variable_alpha():
    labelx="Varying Alpha"
    labely=["Bandwidth Served by Requests of BE Class","Bandwidth Served by Requests of nRTPS Class","Bandwidth Served by Requests of RTPS Class","Bandwidth Served by Requests of UGC Class",
        "Number of Requests Served in BE Class","Number of Requests Served in nRTPS Class","Number of Requests Served in RTPS Class","Number of Requests Served in UGC Class",
        "Total Bandwidth Served","Total Number of Requests Served"]
    title=["Bandwidth Allocation for BE Class","Bandwidth Allocation for nRTPS Class","Bandwidth Allocation for RTPS Class","Bandwidth Allocation for UGC Class",
       "Number of Requests in BE Class","Number of Requests in nRTPS Class","Number of Requests in RTPS Class","Number of Requests in UGC Class","Total Bandwidth Allocation"
       ,"Total Number of Requests"]

    index=0
    for filename in onlyfiles:
        print filename
        datalist=sorted(open(filename).readlines(), key=lambda line: float(line.split(' ')[0]))
        y=[]
        x=[]
        count=0.0;
        step=1.0/len(datalist)
        for data in datalist:
            num=data.strip().split(' ');
            x.append(count);
            y.append(float(num[1]));
            count+=step;
        print x
        print y
        pylab.plot(x,y,'--bo')
        pylab.title(title[index])
        pylab.xlabel(labelx)
        pylab.ylabel(labely[index])
        newname=filename[filename.rfind("\\")+1:filename.rfind('.')]
        plt.savefig("output\\Plots\\"+title[index]+".png",bbox_inches='tight')
        plt.close()
        index+=1
        
def variable_framesize():
    labelx="Varying FrameSize"
    labely=["Bandwidth Served by Requests of BE Class","Bandwidth Served by Requests of nRTPS Class","Bandwidth Served by Requests of RTPS Class","Bandwidth Served by Requests of UGC Class",
        "Number of Requests Served in BE Class","Number of Requests Served in nRTPS Class","Number of Requests Served in RTPS Class","Number of Requests Served in UGC Class",
        "Total Bandwidth Served","Total Number of Requests Served"]
    title=["Bandwidth Allocation for BE Class","Bandwidth Allocation for nRTPS Class","Bandwidth Allocation for RTPS Class","Bandwidth Allocation for UGC Class",
       "Number of Requests in BE Class","Number of Requests in nRTPS Class","Number of Requests in RTPS Class","Number of Requests in UGC Class","Total Bandwidth Allocation"
       ,"Total Number of Requests"]

    index=0
    for filename in onlyfiles:
        print filename
        datalist=sorted(open(filename).readlines(), key=lambda line: float(line.split(' ')[0]))
        y=[]
        x=[]
        count=0
        step=10
        for data in datalist:
            num=data.strip().split(' ');
            x.append(count);
            y.append(float(num[1]));
            count+=step;
        print x
        print y
        pylab.plot(x,y,'--bo')
        pylab.title(title[index])
        pylab.xlabel(labelx)
        pylab.ylabel(labely[index])
        newname=filename[filename.rfind("\\")+1:filename.rfind('.')]
        plt.savefig("output\\Plots\\"+title[index]+".png",bbox_inches='tight')
        plt.close()
        index+=1


def variable_frametime():
    labelx="Varying Number of frames"
    labely=["Bandwidth Served by Requests of BE Class","Bandwidth Served by Requests of nRTPS Class","Bandwidth Served by Requests of RTPS Class","Bandwidth Served by Requests of UGC Class",
        "Number of Requests Served in BE Class","Number of Requests Served in nRTPS Class","Number of Requests Served in RTPS Class","Number of Requests Served in UGC Class",
        "Total Bandwidth Served","Total Number of Requests Served"]
    title=["Bandwidth Allocation for BE Class","Bandwidth Allocation for nRTPS Class","Bandwidth Allocation for RTPS Class","Bandwidth Allocation for UGC Class",
       "Number of Requests in BE Class","Number of Requests in nRTPS Class","Number of Requests in RTPS Class","Number of Requests in UGC Class","Total Bandwidth Allocation"
       ,"Total Number of Requests"]

    index=0
    for filename in onlyfiles:
        print filename
        datalist=sorted(open(filename).readlines(), key=lambda line: float(line.split(' ')[0]))
        y=[]
        x=[]
        count=0.05
        step=0.05
        for data in datalist:
            num=data.strip().split(' ');
            x.append(1+1.0/count);
            y.append(float(num[1]));
            count+=step;
        print x
        print y
        pylab.plot(x,y,'--bo')
        pylab.title(title[index])
        pylab.xlabel(labelx)
        pylab.ylabel(labely[index])
        newname=filename[filename.rfind("\\")+1:filename.rfind('.')]
        plt.savefig("output\\Plots\\"+title[index]+".png",bbox_inches='tight')
        plt.close()
        index+=1

variable_frametime()
