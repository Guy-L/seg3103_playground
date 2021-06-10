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

	@Test
	void testSumZero() {
		assertEquals(Calculator.sum(0, 0), 0);
	}
	
	@Test
	void testSumOneTwo() {
		assertEquals(Calculator.sum(1, 2), 2);
	}
	
	@Test
	void testSumSameNonPrime() {
		assertEquals(Calculator.sum(1, 1), 0);
		assertEquals(Calculator.sum(4, 4), 0);
		assertEquals(Calculator.sum(10, 10), 0);
		assertEquals(Calculator.sum(333, 333), 0);
		assertEquals(Calculator.sum(9999, 9999), 0);
	}
}
