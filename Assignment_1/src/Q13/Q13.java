package Q13;
/*Display the triangle on the console as follows using any type of loop. Do NOT use
a simple group of print statements to accomplish this.
0
1 0
1 0 1
0 1 0 1
*/
public class Q13 {
	public static void main(String[] args) {
		int iteration = 4;
		boolean zeroOrOne = true;
		
		for (int space = 1; space <= iteration ; space++) {
			for (int i = 0; i < space; i++) {
				if (zeroOrOne == true)
					System.out.printf("0 ");
				else
					System.out.printf("1 ");
				zeroOrOne = !zeroOrOne;
			}
		System.out.println();
		}
	}
}
