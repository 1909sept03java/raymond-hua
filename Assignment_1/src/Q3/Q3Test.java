package Q3;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Q3Test {
	private static Q3 q3;
	String inputString = "Hello World";
	String outputString = "dlroW olleH";
	
	@BeforeClass
	public static void initializeQ1() {
		q3 = new Q3();
	}
	
	@Test
	public void resultIsCorrect(){
		assertEquals(outputString, q3.function(inputString));
	}
}
