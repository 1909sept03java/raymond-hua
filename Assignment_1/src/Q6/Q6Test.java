package Q6;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Q6Test {
	private static Q6 q6;
	int input = 6;
	boolean output = true;
	
	@BeforeClass
	public static void initializeQ1() {
		q6 = new Q6();
	}
	
	@Test
	public void resultIsCorrect(){
		assertEquals(output, q6.even(input));
	}
}
