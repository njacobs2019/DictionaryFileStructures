import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Tune{

	public static void main(String[] args){
		tuneTable(); 
	}

	public static void tuneTable(){
		double[] out;

		HashTable ht = new HashTable(5000);
		fillTable(ht);
		out = ht.stats();

		System.out.format("Avg_full: %.1f\tSD:  %.4f\n",out[0],out[1]);
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