import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class HashTable_Tester{

	public static void main(String[] args){
		HashTable ht = new HashTable(100);
		fillTable(ht);
		testPrintAllAlpha(ht);
	}

	public static void testPrintAllAlpha(HashTable ht){
		ht.printAllAlpha();
	}

	public static void testPartialPrint(HashTable ht){
		ht.partialPrint("butty",10);
		System.out.println("----");
		ht.partialPrint("ical",10);
		System.out.println("----");
		ht.partialPrint("ical",2);
	}

	public static void testFindNext(HashTable ht){
		System.out.println(ht.findNext("butty"));
		System.out.println(ht.findNext("ical"));
		System.out.println(ht.findNext("was"));
		System.out.println(ht.findNext("zebra"));
	}

	public static void testPrintAll(HashTable ht){
		ht.printAll();
	}

	public static void testSearch(HashTable ht){
		System.out.println(ht.search("a"));

		System.out.println("---------");

		//Search prints out only null values
		HashNode hn = ht.search("A");
		System.out.println(hn);

		System.out.println("---------");

		hn = ht.search("a 1");
		System.out.println(hn);

		System.out.println("---------");

		hn = ht.search("yellow");
		System.out.println(hn);
	}

	public static void fillTable(HashTable t){
		//Initializes file reader
		File f = new File("Dataset.tsv");
		WordNode n;

		try{
			Scanner scan = new Scanner(f);
			String line;
			line = scan.nextLine();  // Reads in the first line of column labels
			
			while(scan.hasNext()){
			//for(int i = 0; i < 20; i++){
				line = scan.nextLine();
				n = new WordNode(line);
				t.add(n);
			}
			scan.close();
		}
		catch(FileNotFoundException e){
			System.out.format("The file was not found.\n");
		}
	}
}