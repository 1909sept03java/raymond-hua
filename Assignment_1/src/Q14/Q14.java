package Q14;
import java.math.*;
import java.util.*;
//Write  a  program  that  demonstrates  the  switch  case.  Implement  the  following functionalities in the cases:
//Case1: Find the square root of a number using the Math class method.
//Case2: Display today’s date.
//Case3: Split the following string and store it in a sting array. “I am learning Core Java
public class Q14 {
	public void function(int selection, double sqrt) {
		//double sqrt = 64;
		Date date = new Date();
		String str = "I am learning Core Java";
		String[] array = new String[5];
		int leftIndex = 0;
		int rightIndex = 0;
		
		switch(selection) {
		case 1: System.out.println(Math.sqrt(sqrt)); break;
		case 2: System.out.println(date.toString()); break;
		case 3:
			for(int i = 0; i < array.length; i++) {
				rightIndex = str.indexOf(" ", leftIndex);
				try {
					array[i] = str.substring(leftIndex, rightIndex);
				} catch (StringIndexOutOfBoundsException e) {
					array[i] = str.substring(leftIndex, str.length());
				}
				leftIndex = rightIndex + 1;
			}
			for (int i = 0 ; i < array.length ; i++)
				System.out.println(array[i]);
		}
	}
}
