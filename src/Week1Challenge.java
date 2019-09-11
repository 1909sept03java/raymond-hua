import java.util.ArrayList;
/*
 * From the gene bank, how many mutation does it take to get from start to end
 * Case 1: If equal, return 0
 * Case 2: If not possible return -1
 * Case 3: If possible, and it works return counter
 * Case 4: If possible, and doesn't work return -1
 */
public class Week1Challenge {
	
	public static boolean inTheBank(String str, String[] bank) {
		for (String i : bank) {
			if(str.equals(i))
				return true;
		}
		return false;
	}
	
	public static int minimumMutations(String start, String end, String[] bank) {
		boolean maybeDoIt = false;
		int counter = 0;
		
		if (start.equals(end))				//if equal, output 0
			return 0;	
		else {								//check if end is in the bank
			for (String i : bank) {
				if(end.equals(i))
					maybeDoIt = true;
			}
		}
		
		if (maybeDoIt) {					//if end is in the bank, do algorithm
			/*
			 * algorithm
			 * from right to left, index through string
			 * find where char is different
			 * find a string in bank that would fulfill it
			 */
			//for (int i = 0; i < end.length(); i++) {
			for (int i = end.length() - 1; i >= 0; i--) {
				if (start.charAt(i) == end.charAt(i))
					continue;																			//if equal continue
				else {
					String temp = start.substring(0,i) + end.charAt(i) + start.substring(i+1 ,start.length());
					if (inTheBank(temp, bank)) {
						start = temp;
						counter++;
					}		
				}
			}
		}
		
		if(start.equals(end))
			return counter;
		else
			return -1;
	}
}