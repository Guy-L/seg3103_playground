import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DateNextDateExceptionTest {
	
	private int year;
	private int month;
	private int day;

	public DateNextDateExceptionTest(int year, int month, int day){
		//Note: since system constraints make it impossible to test the "nextDay" method
		//with an invalid date, we're not so much testing this method as we are testing if
		//these test cases are of valid dates. This is reflected in the below method name.
		
		this.year  = year;
		this.month = month;
		this.day   = day;
	}

	@Parameters
	public static List<Integer[]> data() {
		List<Integer[]> params = new LinkedList<Integer[]>();

		params.add(new Integer[] {1500, 2,  31});
		params.add(new Integer[] {1500, 2,  29});
		params.add(new Integer[] {-1,   10, 20});
		params.add(new Integer[] {1458, 15, 12});
		params.add(new Integer[] {1975, 6, -50});
		return params;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testIsValidDate(){ 
		new Date(year, month, day);
	}
}