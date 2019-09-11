package Q10;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Q10Test {
	private static Q10 q10;
	int input1 = 100;
	int input2 = 1;
	int output = 1;
	
	@BeforeClass
	public static void initializeQ() {
		q10 = new Q10();
	}
	
	@Test
	public void resultIsCorrect1(){
		assertEquals(output, q10.min(input1, input2));
	}
}
