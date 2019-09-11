package Q12;
//. Write a program to store numbers from 1 to 100 in an array. Print out all the even
//numbers from the array. Use the enhanced FOR loop for printing out the numbers.

public class Q12 {
	public static void main(String[] args) {
		int[] array = new int[100];
		for (int i = 1; i <= 100; i++)
			array[i-1] = i;
		for (int i : array)	
			if(i%2 == 0)
				System.out.println(i);
	}
}
