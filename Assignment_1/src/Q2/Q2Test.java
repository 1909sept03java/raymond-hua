package Q2;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Q2Test {
	private static Q2 q2;
	int[] testArray = new int[]{1,1,2,3,5,8,13,21,34,55,89,144,233,377,610,987,1597,2584,4181,6765,10946,17711,28657,46368,75025};
	int howMany = 25;
	
	@BeforeClass
	public static void initializeQ1() {
		q2 = new Q2();
	}
	
	@Test
	public void resultIsCorrect(){
		for (int i = 0; i < howMany; i++)
			assertEquals(testArray[i], q2.fib(i));
	}
}
