import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DateMiscTest {

	@Test
	void testString() {
		assertEquals(new Date(2021, 6, 3).toString(), "2021/June/3");
	}
	
	@Test
	void testUnequalToNonDate() {
		assertNotEquals(new Date(2021, 6, 3), "2021/June/3");
	}
	
	@Test
	void testUnequalToWrongDate() {
		assertNotEquals(new Date(2021, 6, 3), new Date(2020, 6, 3));
		assertNotEquals(new Date(2021, 6, 3), new Date(2021, 5, 3));
		assertNotEquals(new Date(2021, 6, 3), new Date(2021, 6, 2));
	}

}
