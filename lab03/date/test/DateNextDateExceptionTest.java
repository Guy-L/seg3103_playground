import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DateNextDateExceptionTest
{

  private int year;
  private int month;
  private int day;

  private int expectedYear;
  private int expectedMonth;
  private int expectedDay;

  public DateNextDateExceptionTest(int year, int month, int day, int ey, int em, int ed) {
    this.year  = year;
    this.month = month;
    this.day   = day;
    
	expectedYear  = ey;
	expectedMonth = em;
	expectedDay   = ed;
  }

  @Parameters
  public static List<Integer[]> data() {
    List<Integer[]> params = new LinkedList<Integer[]>();
    return params;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNextDate(){
    Date date = new Date(year, month, day);
	Date next = date.nextDate();
	Assert.assertEquals(expectedYear, next.getYear());
	Assert.assertEquals(expectedMonth, next.getMonth());
	Assert.assertEquals(expectedDay, next.getDay());
  }
}
