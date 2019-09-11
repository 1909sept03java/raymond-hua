package Q21;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Q21Test {
	private static Q21 q21;
	String inputString = "Hello World";
	String outputString = "Helo Wrd";
	
	@BeforeClass
	public static void initializeQ1() {
		q21 = new Q21();
	}
	
	@Test
	public void resultIsCorrect(){
		assertEquals(outputString, q21.function(inputString));
	}
}
