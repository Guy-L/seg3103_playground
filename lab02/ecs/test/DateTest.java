import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DateTest {
	
	@Test
	void testcase_1() {
		assertEquals(new Date(1700, 6, 20).nextDate(), new Date(1700, 6, 21));
	}
	
	@Test
	void testcase_2() {
		assertEquals(new Date(2005, 4, 15).nextDate(), new Date(2005, 4, 16));
	}
	
	@Test
	void testcase_3() {
		assertEquals(new Date(1901, 7, 20).nextDate(), new Date(1901, 7, 21));
	}
	
	@Test
	void testcase_4() {
		assertEquals(new Date(3456, 3, 27).nextDate(), new Date(3456, 3, 28));
	}
	
	@Test
	void testcase_5() {
		assertEquals(new Date(1500, 2, 17).nextDate(), new Date(1500, 2, 18));
	}
	
	@Test
	void testcase_6() {
		assertEquals(new Date(1700, 6, 29).nextDate(), new Date(1700, 6, 30));
	}
	
	@Test
	void testcase_7() {
		assertEquals(new Date(1800, 11, 29).nextDate(), new Date(1800, 11, 30));
	}
	
	@Test
	void testcase_8() {
		assertEquals(new Date(3453, 1, 29).nextDate(), new Date(3453, 1, 30));
	}
	
	@Test
	void testcase_9() {
		assertEquals(new Date(444, 2, 29).nextDate(), new Date(444, 3, 1));
	}
	
	@Test
	void testcase_10() {
		assertEquals(new Date(2005, 4, 30).nextDate(), new Date(2005, 5, 1));
	}
	
	@Test
	void testcase_11() {
		assertEquals(new Date(3453, 1, 30).nextDate(), new Date(3453, 1, 31));
	}
	
	@Test
	void testcase_12() {
		assertEquals(new Date(3456, 3, 30).nextDate(), new Date(3456, 3, 31));
	}
	
	@Test
	void testcase_13() {
		assertEquals(new Date(1901, 7, 31).nextDate(), new Date(1901, 8, 1));
	}
	
	@Test
	void testcase_14() {
		assertEquals(new Date(3453, 1, 31).nextDate(), new Date(3453, 2, 1));
	}
	
	@Test
	void testcase_15() {
		assertEquals(new Date(3456, 12, 31).nextDate(), new Date(3457, 1, 1));
	}
	
	@Test
	void testcase_16() {
		assertThrows(IllegalArgumentException.class, () -> new Date(1500, 2, 31));
	}
	
	@Test
	void testcase_17() {
		assertThrows(IllegalArgumentException.class, () -> new Date(1500, 2, 29));
	}
	
	@Test
	void testcase_18() {
		assertThrows(IllegalArgumentException.class, () -> new Date(-1, 10, 20));
	}
	
	@Test
	void testcase_19() {
		assertThrows(IllegalArgumentException.class, () -> new Date(1458, 15, 12));
	}
	
	@Test
	void testcase_20() {
		assertThrows(IllegalArgumentException.class, () -> new Date(1975, 6, -50));
	}
}