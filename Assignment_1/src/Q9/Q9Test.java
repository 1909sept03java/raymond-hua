package Q9;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Q9Test {
	private static Q9 q9;
	int input1 = 100;
	int input2 = 13;
	int input3 = 1;
	boolean output1 = false;
	boolean output2 = true;
	boolean output3 = false;

	
	//ArrayList list = new ArrayList();
	//list.add(1);
	
	@BeforeClass
	public static void initializeQ() {
		q9 = new Q9();
	}
	
	@Test
	public void resultIsCorrect1(){
		assertEquals(output1, q9.primeNumberPrinter(input1));
	}
	
	@Test
	public void resultIsCorrect2(){
		assertEquals(output2, q9.primeNumberPrinter(input2));
	}

	@Test
	public void resultIsCorrect3(){
		assertEquals(output3, q9.primeNumberPrinter(input3));
	}
}
