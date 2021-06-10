import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {

	@Test
	void testOppositeZero() {
		assertEquals(Calculator.opposite(0), 0);
	}
	
	@Test
	void testOppositeOne() {
		assertEquals(Calculator.opposite(1), -1);
	}

}
