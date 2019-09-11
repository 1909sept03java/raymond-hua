package Q17;
import java.util.Scanner;
/*Write a program that calculates the simple interest on the principal, rate of interest
and number of years provided by the user. Enter principal, rate and time through the
console using the Scanner class.
Interest = Principal* Rate* Time
*/
public class Q17 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("What is your principal in $:");
		double principal = scanner.nextDouble();
		System.out.println("What is your rate of interest in %:");
		double roi = scanner.nextDouble()/100.00;
		System.out.println("What is your number of years:");
		double years = scanner.nextDouble();
		System.out.println("Your interest is: " + principal*roi*years);
	}
	public double function(double principal, double roi, double years) {
		System.out.println(principal *roi * years);
		return (principal*roi*years);
	}
}
