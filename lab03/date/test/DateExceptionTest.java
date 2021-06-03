import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DateExceptionTest {

	private int year;
	private int month;
	private int day;

	public DateExceptionTest(int y, int m, int d) {
		year  = y;
		month = m;
		day   = d;
	}

	@Parameters
	public static List<Integer[]> data(){
		List<Integer[]> params = new LinkedList<Integer[]>();
		params.add(new Integer[] {1500,	02,	29});
		params.add(new Integer[] {-1,	10,	20});
		params.add(new Integer[] {1458,	15,	12});
		params.add(new Integer[] {1975, 6, -50});
		
		//Missing setDay/setMonth branches
		params.add(new Integer[] {2001, 5, 32});
		params.add(new Integer[] {2021, 6, 31});
		params.add(new Integer[] {2020, 2, 30});
		params.add(new Integer[] {1977, -1, 5});
		
		return params;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidDate() {
	    new Date(year, month, day);
	}
}