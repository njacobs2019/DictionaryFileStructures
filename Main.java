import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main{

	public static void main(String[] args){
		// Create the scanner
		Scanner scan = new Scanner(System.in);
		String line;

		// Initialize tree
		BPlusTree tree = new BPlusTree(10);


		// Fill the tree
		long startTime = System.currentTimeMillis();
		fillTree(tree);
		long endTime = System.currentTimeMillis();
		long tree_fill = endTime-startTime;
		System.out.println("Time to fill B+ Tree (ms): " + (tree_fill));


		System.out.print("Ready to print the entire B+ tree? (press enter)  ");
		line = scan.nextLine();

		// Print the tree
		startTime = System.currentTimeMillis();
		tree.printAll();
		endTime = System.currentTimeMillis();
		long tree_printAll = endTime-startTime;
		System.out.println("\n\nTime to print entire B+ Tree (ms): " + (tree_printAll));
		System.out.println("Note:  The dataset contains some words that do not start with a or b. They are synonyms of words in the dictionary.");


		System.out.print("\nReady to partial print the B+ tree for \"butty\" with 10 words? (press enter)  ");
		line = scan.nextLine();

		//Partial print the tree
		startTime = System.currentTimeMillis();
		tree.partialPrint("butty",10);
		endTime = System.currentTimeMillis();
		long tree_printPartial = endTime-startTime;
		System.out.println("\n\nTime to partial print B+ Tree (ms): " + (tree_printPartial));
		
		System.out.print("\nReady to search the B+ tree for \"butty\"? (press enter)  ");
		line = scan.nextLine();

		// Search the tree
		startTime = System.currentTimeMillis();
		System.out.println("Here is the bucket containing butty:");
		System.out.println(tree.search("butty"));
		endTime = System.currentTimeMillis();
		long tree_search = endTime-startTime;
		System.out.println("\n\nTime to search B+ Tree (ms): " + (tree_search));
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
}