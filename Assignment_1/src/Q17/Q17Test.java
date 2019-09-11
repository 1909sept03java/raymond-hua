package Q17;
import java.util.Scanner;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class Q17Test {
	private static Q17 q17;
	static double principal;
	static double roi;
	static double years;
	static double interest;
	
	@BeforeClass
	public static void initializeQ() {
		q17 = new Q17();
		Scanner scanner = new Scanner(System.in);
		System.out.println("What is your principal in $X.XX:");
		principal = scanner.nextDouble();
		System.out.println("What is your rate of interest in %:");
		roi = scanner.nextDouble()/100.00;
		System.out.println("What is your number of years:");
		years = scanner.nextDouble();
		interest = (principal*roi*years);
		System.out.println(interest);
	}
	
	@Test
	public void resultIsCorrect1(){
		assertEquals(interest, q17.function(principal, roi, years));
	}
	//works when casting to int
}
