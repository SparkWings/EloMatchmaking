package org.jbltd.matchmaking.util;

public class UtilMethodTiming{

    private long _startTime;
    
    public void start()
    {
	_startTime = System.nanoTime();
    }
    
    public void stop()
    {
	long finishTime = System.nanoTime();
	
	long miliseconds = (finishTime - _startTime) / 1000000;
	double fin = miliseconds * 0.001;
	
	System.out.println("Method took "+fin+" seconds to complete.");
	
    }

}
