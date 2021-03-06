import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main{

	public static void main(String[] args){
		// Create the scanner
		Scanner scan = new Scanner(System.in);
		String line;

		// Initialize tree
		BPlusTree tree = new BPlusTree(20);
		HashTable ht = new HashTable(300);

		// Counter times
		long startTree;
		long endTree;
		long startTable;
		long endTable;

		// Fill the tree
		startTree = System.currentTimeMillis();
		fillTree(tree);
		endTree = System.currentTimeMillis();
		startTable = System.currentTimeMillis();
		fillTable(ht);
		endTable = System.currentTimeMillis();

		long tree_fill = endTree-startTree;
		long table_fill = endTable-startTable;
		System.out.println("\nTime to fill B+ Tree (ms): " + (tree_fill));
		System.out.println("Time to fill Hash Table (ms): " + (table_fill));

		// Print Alpha
		System.out.println("\nThis will print the entire B+ tree in alphabetical order");
		System.out.println("Warning: this will also print the entire hash table in alphabetical order which takes <30 seconds");
		System.out.print("Ready to print? (press enter)  ");
		line = scan.nextLine();

		// Print the trees
		System.out.println("Printing B+ Tree");
		startTree = System.currentTimeMillis();
		tree.printAll();
		endTree = System.currentTimeMillis();
		long tree_printAll = endTree-startTree;

		System.out.println("Now Printing Hash Table:");
		startTable = System.currentTimeMillis();
		ht.printAllAlpha();
		endTable = System.currentTimeMillis();
		long table_printAll = endTable-startTable;

		double difference = table_printAll/tree_printAll;
		System.out.println("\n\nTime to print entire B+ Tree (ms): " + (tree_printAll));
		System.out.println("Time to print entire HashTable (ms): " + (table_printAll));
		System.out.format("Printing B+ tree was %.2f x faster!\n",difference);
		System.out.println("Note:  The dataset contains some words that do not start with a or b. They are synonyms of words in the dictionary.");



		// Partial Print

		System.out.print("\nReady to partial print the trees for \"butty\" with 10 words? (press enter)  ");
		line = scan.nextLine();

		//Partial print the tree
		startTree = System.currentTimeMillis();
		tree.partialPrint("butty",10);
		endTree = System.currentTimeMillis();
		
		System.out.println();

		startTable = System.currentTimeMillis();
		ht.partialPrint("butty",10);
		endTable = System.currentTimeMillis();

		long tree_printPartial = endTree-startTree;
		System.out.println("\n\nTime to partial print B+ Tree (ms): " + (tree_printPartial));
		long table_printPartial = endTable-startTable;
		System.out.println("Time to partial print Hash Table (ms): " + (table_printPartial));


		// Search both trees
		
		System.out.print("\nReady to search the data structures? (press enter)  ");
		line = scan.nextLine();

		String[] searches = {"abate","abelian","alien","butty","butyl","was","ical","Aria","Africa","Automatic"};


		System.out.print("Searching for:  ");
		for(int i=0; i<searches.length; i++){
			System.out.format("[%s]",searches[i]);
		}
		System.out.println();

		// Search the tree
		System.out.println("\nHere is the B+ Tree search:");
		startTree = System.currentTimeMillis();
		for(int i=0; i<searches.length; i++){
			System.out.println(tree.searchWord(searches[i]));
		}
		endTree = System.currentTimeMillis();

		// Search the table
		System.out.println("\nHere is the Hash Table search:");
		startTable = System.currentTimeMillis();
		for(int i=0; i<searches.length; i++){
			System.out.println(ht.search(searches[i]));
		}
		endTable = System.currentTimeMillis();

		long tree_search = endTree-startTree;
		System.out.println("\n\nTime to search B+ Tree (ms): " + (tree_search));
		long table_search = endTable-startTable;
		System.out.println("Time to search Hash Table (ms): " + (table_search));
	}

	public static void fillTree(BPlusTree t){
		//Initializes file reader
		File f = new File("Dataset.tsv");
		WordNode n;

		try{
			Scanner scan = new Scanner(f);
			String line;
			line = scan.nextLine();  // Reads in the first line of column labels
			
			while(scan.hasNext()){
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

	public static void fillTable(HashTable t){
		//Initializes file reader
		File f = new File("Dataset.tsv");
		WordNode n;

		try{
			Scanner scan = new Scanner(f);
			String line;
			line = scan.nextLine();  // Reads in the first line of column labels
			
			while(scan.hasNext()){
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