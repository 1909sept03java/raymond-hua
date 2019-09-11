package Q4;
//Write a program to compute N factorial

public class Q4 {
	public int factorial(int x) {
		int result = 1;
		for (int i = 0; i <= x ; i++) {
			if (i == 0)
				continue;
			result *= i;
		}
		return result;
	}
}
