import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Reader_Tester{

	public static void main(String[] args){
		String filename = "Dataset.tsv";

		//Initializes file reader
		File f = new File(filename);

		WordNode n;

		//Try catch in case the file the program reads from is invalid in any way.
		try{
			Scanner scan = new Scanner(f);
			String line;
			//Reads through the file that stores all the usernames and adds the usernames to the linked list
			while(scan.hasNext()){
				line = scan.nextLine();
				n = new WordNode(line);
				System.out.print(n.getName());
				System.out.print(":  ");
				System.out.println(n);
			}
			scan.close();
		}
		catch(FileNotFoundException e){
			System.out.format("The file \"%s\" was not found.\n", filename);
		}
	}
}