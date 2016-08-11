import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.containsString;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import com.simpleprogrammer.proteintracker.HistoryItem;
import com.simpleprogrammer.proteintracker.InvalidGoalException;
import com.simpleprogrammer.proteintracker.Notifier;
import com.simpleprogrammer.proteintracker.NotifierStub;
import com.simpleprogrammer.proteintracker.TrackingService;


public class TrackingServiceTests {

	private TrackingService service;
	@BeforeClass
	public static void before()
	{

	}
	
	@AfterClass
	public static void after()
	{
	}
	
	@Before
	public void setUp() 
	{
		service = new TrackingService(new NotifierStub());
	}
	
	@After
	public void tearDown()
	{
	}
	
	@Test
	@Category({GoodTestsCategory.class, BadCategory.class})
	public void NewTrackingServiceTotalIsZero()
	{
		assertEquals("Tracking service total was not zero", 0, service.getTotal());
	}
	
	@Test
	@Category(GoodTestsCategory.class)
	public void WhenAddingProteinTotalIncreasesByThatAmount() 
	{
		service.addProtein(10);		
		assertThat(service.getTotal(), allOf(is(10), instanceOf(Integer.class))); 
	}
	
	@Test
	@Category(GoodTestsCategory.class)
	public void WhenRemovingProteinTotalRemainsZero() 
	{
		service.removeProtein(5);
		assertEquals(0, service.getTotal());
	}
	
	@Test
	public void WhenGoalIsMetHistoryIsUpdated() throws InvalidGoalException
	{
		Mockery context = new Mockery();
		final Notifier mockNotifier = context.mock(Notifier.class);
		service = new TrackingService(mockNotifier);
		
		context.checking(new Expectations() {{
			oneOf(mockNotifier).send("goal met");
			will(returnValue(true));
		}});
		
		service.setGoal(5);
		service.addProtein(6);
		
		HistoryItem result = service.getHistory().get(1);
		assertEquals("sent:goal met", result.getOperation());
		
		context.assertIsSatisfied();
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void WhenGoalIsSetToLessThanZeroExceptionIsThrown() throws InvalidGoalException
	{
		thrown.expect(InvalidGoalException.class);
		thrown.expectMessage(containsString("Goal"));
		service.setGoal(-5);
	}

	@Rule
	public Timeout timeout = new Timeout(2000);
	
	@Test
	public void BadTest()
	{
		for(int i = 0; i < 1000; i++)
			service.addProtein(1);
	}
}
