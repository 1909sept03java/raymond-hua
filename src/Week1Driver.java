import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Week1Driver {
	private static Week1Challenge w1c;
	public static Scanner scanner = new Scanner(System.in);	
	
	public static void main(String[] args) {
		String start = null;
		String end = null;
		String[] bank = null;
		
		/*Read from file
		String path = "src/text.txt";
		try(BufferedReader br = new BufferedReader(new FileReader(path))){		
			start = br.readLine();
			String temp = br.readLine();
			end = br.readLine();
			temp = br.readLine();
			bank = (br.readLine()).split(",");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
		
		/*Input through console
		System.out.println("Input a starting string:");
		start = scanner.nextLine();
		System.out.println("Input an ending string:");
		end = scanner.nextLine();
		System.out.println("Input a string bank split by ',':");
		bank = (scanner.nextLine()).split(",");
		*/
		
		/*Hard coded
		start = "AACCGGTT";				//start string
		end = "AAACGGTA";				//end string
		bank = new String[]{"AACCGGTA", "AACCGCTA", "AAACGGTA"};		//create bank with array list
		*/
		
		System.out.println(w1c.minimumMutations(start, end, bank));
	}
}
