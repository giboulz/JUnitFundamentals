package com.gbz.JUnitTest.runner;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import com.gbz.JUnitTest.TrackingServiceTest;

public class ConsoleRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JUnitCore junit = new JUnitCore(); 
		junit.addListener(new TextListener(System.out));
		
		junit.run(TrackingServiceTest.class); 
	}

}
