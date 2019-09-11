package Q15;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Q15Test {
	private static Q15 q15;
	int x = 1;
	int y = 2;
	
	@BeforeClass
	public static void initializeQ() {
		q15 = new Q15();
	}
	@Test
	public void resultIsCorrect1(){
		assertEquals(3,q15.addition(x,y));
		assertEquals(-1,q15.subtraction(x,y));
		assertEquals(2,q15.multiplication(x,y));
		assertEquals(0,q15.division(x,y));
	}
}
