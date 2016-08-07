package com.gbz.JUnitTest;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.junit.Assume;

import com.simpleprogrammer.proteintracker.TrackingService;

@RunWith(Theories.class)
public class TrackingServiceTheory {

	@DataPoints
	public static int[] data(){
		return new int[]{
			1,5,10, 15, 20, 60, -4
		}; 
	}
	
	@Theory
	public void positive_value_should_always_always_have_positive_total(int value){
		TrackingService service = new TrackingService(); 
		service.addProtein(value);
		
		//vérification du input
		Assume.assumeTrue(value > 0);
		
		
		assertTrue(service.getTotal() > 0); 
	}

	
	
}

