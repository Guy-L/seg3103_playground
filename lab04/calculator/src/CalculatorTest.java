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
	
	@Test
	void testOppositeOfPositives() {
		assertEquals(Calculator.opposite(6), -6);
		assertEquals(Calculator.opposite(51), -15);
		assertEquals(Calculator.opposite(154), -451);
		assertEquals(Calculator.opposite(307), -703);
		assertEquals(Calculator.opposite(10000), -1);
		assertEquals(Calculator.opposite((int)00093f), -39);
	}
	
	@Test
	void testOppositeOfNegatives() {
		assertEquals(Calculator.opposite(-6), 6);
		assertEquals(Calculator.opposite(-51), 15);
		assertEquals(Calculator.opposite(-154), 451);
		assertEquals(Calculator.opposite(-307), 703);
		assertEquals(Calculator.opposite(-10000), 1);
		assertEquals(Calculator.opposite(-(int)00093f), 39);
	}

}
