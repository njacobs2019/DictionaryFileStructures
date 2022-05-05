import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class BPlusTree_Tester{

	public static void main(String[] args){
		addTester();
	}

	public static void addTester(){
		//Initializes file reader
		File f = new File("Dataset.tsv");

		BPlusTree tree = new BPlusTree(3);
		WordNode n;

		try{
			Scanner scan = new Scanner(f);
			String line;
			line = scan.nextLine();  // Reads in the first line of column labels
			
			// while(scan.hasNext()){
			for(int i=0; i<20; i++){
				line = scan.nextLine();
				System.out.print("BFS:  ");
				tree.printBFS();
				System.out.println();				
				n = new WordNode(line);
				System.out.format("i %d\n",i);
				tree.add(n);
			}


			tree.printBFS();
			tree.printAll();
			System.out.println();

			
			scan.close();
		}
		catch(FileNotFoundException e){
			System.out.format("The file was not found.\n");
		}
	}

	public static void addTester_noSplit(){
		WordNode def1 = new WordNode("Aback", "adv.", "Behind, in the rear.");
		WordNode def2 = new WordNode("Before", "adv.", "Behind, in the rear.");
		WordNode def3 = new WordNode("Aardvark", "adv.", "Behind, in the rear.");
		WordNode def4 = new WordNode("Zebra", "adv.", "Behind, in the rear.");
		WordNode def5 = new WordNode("Fun", "adv.", "Behind, in the rear.");
		WordNode def6 = new WordNode("Before", "adv.", "2.");

		BPlusTree tree = new BPlusTree(5);

		tree.add(def1);
		tree.add(def2);
		tree.add(def3);
		tree.add(def4);
		tree.add(def5);

		tree.printBFS();
	}
}