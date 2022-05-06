import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class HashTable_Tester{

	public static void main(String[] args){
		HashTable ht = new HashTable(100);
		// WordNode defn1 = new WordNode("Aback","adv.","Behind; in the rear.");
		// WordNode defn2 = new WordNode("Aback","adv.","Toward the back or rear; backward.");
		// WordNode defn3 = new WordNode("Abacus","n.","The uppermost member or division of the capital of a column, immediately under the architrave. See Column.");
		fillTable(ht);
		// ht.add(defn1);
		// ht.add(defn2);
		// ht.add(defn3);s



		// for(int i=0; i<20; i++){
		// 	System.out.println(ht.table[i]);
		// }

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
			
			//while(scan.hasNext()){
			for(int i = 0; i < 20; i++){
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