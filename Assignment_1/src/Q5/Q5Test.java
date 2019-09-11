package Q5;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Q5Test {
	private static Q5 q5;
	String inputString = "Hello World";
	int idx = 4;
	String outputString = "Hello";
	
	@BeforeClass
	public static void initializeQ1() {
		q5 = new Q5();
	}
	
	@Test
	public void resultIsCorrect(){
		assertEquals(outputString, q5.inclusive(inputString, idx));
	}
}
