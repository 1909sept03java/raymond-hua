package Q1;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Q1Test {
	private static Q1 q1;
	int[] testArray = new int[]{1,0,5,6,3,2,3,7,9,8,4};
	int[] correctArray = new int[] {0,1,2,3,3,4,5,6,7,8,9};
	
	@BeforeClass
	public static void initializeQ1() {
		q1 = new Q1();
	}
	
	@Test
	public void resultIsCorrect(){
		assertArrayEquals(correctArray, q1.bubbleSort(testArray));
	}
}
