package com.gbz.JUnitTest;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import com.simpleprogrammer.proteintracker.InvalidGoalException;
import com.simpleprogrammer.proteintracker.TrackingService;

public class TrackingServiceTests {

	private TrackingService service;

	@Before
	public void setUp() {
		// System.out.println("before");
		service = new TrackingService();
	}

	@After
	public void tearDown() {
		// System.out.println("after");
	}

	@BeforeClass
	public static void onceExecutedBeforeAll() {

		// System.out.println("@BeforeClass: onceExecutedBeforeAll");

	}

	@AfterClass
	public static void afterClass() {
		// System.out.println("After Class");
	}

	@Test
	@Category({ GoodTestCategory.class, BadTestCategory.class })
	public void new_tracking_service_total_is_zero() {

		assertEquals("tracking service total was not zero", 0, service.getTotal());

	}

	@Test
	@Category(GoodTestCategory.class)
	public void when_adding_protein_total_increase_by_that_amount() {

		int amountProtein = 10;
		service.addProtein(amountProtein);
		assertEquals("Protein amount was not correct", amountProtein, service.getTotal());
		assertThat("Protein amount was not correct", service.getTotal(), is(10));
		
		assertThat(service.getTotal(), allOf(is(amountProtein), instanceOf(Integer.class))); 
	}

	@Test
	@Category(GoodTestCategory.class)
	public void when_removing_protein_total_cant_go_below_0() {
		int amountProtein = 5;
		service.removeProtein(amountProtein);
		assertEquals("Protein total is negative", 0, service.getTotal());
	}

	
	@Rule
	public ExpectedException thrown = ExpectedException.none(); 
	
	@Test
	@Category(GoodTestCategory.class)
	public void when_goal_value_is_negative_exception_is_thrown() throws InvalidGoalException {
		thrown.expect(InvalidGoalException.class);
		thrown.expectMessage(containsString("Goal"));
		service.setGoal(-5);
	}

	@Rule
	public Timeout timeout =  Timeout.millis(20); 
	
	
	@Test
	@Category(BadTestCategory.class)
	public void bad_test() {
		for (int i = 0; i < 1000000000; i++) {
			service.addProtein(1);
		}
	}

}
