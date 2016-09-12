package org.jbltd.matchmaking.util;

import java.util.ArrayList;

public class UtilMath {

    
    public static double average(double... inputs)
    {
	int fin = 0;
	
	for(int i = 0; i < inputs.length; i++)
	{
	    fin+=inputs[i];
	}
	
	fin /= inputs.length;
	
	return fin;
	
    }
    
    public static double average(ArrayList<Integer> inputs)
    {
	int fin = 0;
	
	for(int i = 0; i < inputs.size(); i++)
	{
	    fin+=inputs.get(i);
	}
	
	fin /= inputs.size();
	
	return fin;
	
    }
    
}
