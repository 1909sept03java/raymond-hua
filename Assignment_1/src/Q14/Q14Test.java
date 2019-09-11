package Q14;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Q14Test {
	/*
	private static Q14 q14;
	
	@BeforeClass
	public static void initializeQ() {
		q14 = new Q14();
	}
	@Test
	public void resultIsCorrect1(){
		assertEquals(output, q14.min(input1, input2));
	}*/
	
	public static void main(String[] args) {
		int input1 = 1;
		int input2 = 2;
		int input3 = 3;
		double sqrt = 64;
		
		Q14 q14 = new Q14();
		
		q14.function(input1, sqrt);
		q14.function(input2, sqrt);
		q14.function(input3, sqrt);
	}
}
