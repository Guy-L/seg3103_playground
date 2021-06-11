import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CalculatorTest {

	@Test
	void percentGradeTests() {
		assertEquals(85, Calculator.percentageGrade(new float[] {0.8f}, new float[] {1f,  1f,  1f}, 0.7f, 0.9f));
		assertEquals(18, Calculator.percentageGrade(new float[0], new float[0], 0.3f, 0.4f));
	}
	
	@Test
	void letterGradeTests() {
		assertEquals("EIN", Calculator.letterGrade(new float[0], new float[0], 0f, 0f)); 
		assertEquals("A+", Calculator.letterGrade(new float[] {0.9f}, new float[] {1f,  1f,  1f}, 0.8f, 0.9f));
		assertEquals("A", Calculator.letterGrade(new float[] {0.8f}, new float[] {1f,  1f,  1f}, 0.7f, 0.9f));
		assertEquals("A-", Calculator.letterGrade(new float[] {0.8f}, new float[] {1f,  0.9f,  0.7f}, 0.7f, 0.9f));
		assertEquals("B+", Calculator.letterGrade(new float[] {0.7f}, new float[] {0.9f,  0.9f,  0.7f}, 0.7f, 0.8f));
		assertEquals("B", Calculator.letterGrade(new float[] {0.8f}, new float[] {0.7f,  0.6f,  0.7f}, 0.6f, 0.7f));
		assertEquals("C+", Calculator.letterGrade(new float[] {0.7f}, new float[] {0.7f,  0.6f,  0.7f}, 0.6f, 0.7f));
		assertEquals("C", Calculator.letterGrade(new float[] {0.7f}, new float[] {0.7f,  0.5f,  0.7f}, 0.6f, 0.5f));
		assertEquals("D+", Calculator.letterGrade(new float[] {0.5f}, new float[] {0.7f,  0.5f,  0.7f}, 0.6f, 0.5f));
		assertEquals("D", Calculator.letterGrade(new float[] {0.5f}, new float[] {0.7f,  0.2f,  0.7f, 0.5f}, 0.6f, 0.5f)); //!!
		assertEquals("E", Calculator.letterGrade(new float[] {0.5f}, new float[] {0.4f,  0.4f,  0.4f}, 0.5f, 0.5f));
		assertEquals("F", Calculator.letterGrade(new float[] {0.4f}, new float[] {0.4f,  0.4f,  0.4f}, 0.5f, 0.3f));
	}
	
	@Test
	void numericGradeTests() {
		assertEquals(0, Calculator.numericGrade(new float[0], new float[0], 0f, 0f)); 
		assertEquals(10, Calculator.numericGrade(new float[] {0.9f}, new float[] {1f,  1f,  1f}, 0.8f, 0.9f));
		assertEquals(9, Calculator.numericGrade(new float[] {0.8f}, new float[] {1f,  1f,  1f}, 0.7f, 0.9f));
		assertEquals(8, Calculator.numericGrade(new float[] {0.8f}, new float[] {1f,  0.9f,  0.7f}, 0.7f, 0.9f));
		assertEquals(7, Calculator.numericGrade(new float[] {0.7f}, new float[] {0.9f,  0.9f,  0.7f}, 0.7f, 0.8f));
		assertEquals(6, Calculator.numericGrade(new float[] {0.8f}, new float[] {0.7f,  0.6f,  0.7f}, 0.6f, 0.7f));
		assertEquals(5, Calculator.numericGrade(new float[] {0.7f}, new float[] {0.7f,  0.6f,  0.7f}, 0.6f, 0.7f));
		assertEquals(4, Calculator.numericGrade(new float[] {0.7f}, new float[] {0.7f,  0.5f,  0.7f}, 0.6f, 0.5f));
		assertEquals(3, Calculator.numericGrade(new float[] {0.5f}, new float[] {0.7f,  0.5f,  0.7f}, 0.6f, 0.5f));
		assertEquals(2, Calculator.numericGrade(new float[] {0.5f}, new float[] {0.7f,  0.2f,  0.7f, 0.5f}, 0.6f, 0.5f)); //!!
		assertEquals(1, Calculator.numericGrade(new float[] {0.5f}, new float[] {0.4f,  0.4f,  0.4f}, 0.5f, 0.5f));
		assertEquals(0, Calculator.numericGrade(new float[] {0.4f}, new float[] {0.4f,  0.4f,  0.4f}, 0.5f, 0.3f));
	}
}
