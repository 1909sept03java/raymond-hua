package Q4;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Q4Test {
	private static Q4 q4;
	int input = 4;
	int output = 24;
	
	@BeforeClass
	public static void initializeQ1() {
		q4 = new Q4();
	}
	
	@Test
	public void resultIsCorrect(){
		assertEquals(output, q4.factorial(input));
	}
}
