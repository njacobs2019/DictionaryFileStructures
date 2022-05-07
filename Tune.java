import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Tune{

	public static void main(String[] args){
		tuneTree();
	}

	// This function tunes the bucket size of the tree
	public static void tuneTree(){
		BPlusTree tree;
		long startTime=0;
		long endTime=0;
		int i;

		int[] sizes = {3,5,8,10,15,20,25,30,40,50,60,70,80,90};

		System.out.println("Bucket Size, time (ms)");
		for(int j=0; j<sizes.length; j++){
			i=sizes[j];

			startTime = System.currentTimeMillis();
			tree = new BPlusTree(i);
			fillTree(tree);
			endTime = System.currentTimeMillis();
			System.out.format("%d, %d\n",i,endTime-startTime);
		}
		for(i=100; i<=2000; i+=50){
			startTime = System.currentTimeMillis();
			tree = new BPlusTree(i);
			fillTree(tree);
			endTime = System.currentTimeMillis();
			System.out.format("%d, %d\n",i,endTime-startTime);
		}
	}

	// Tunes the table size
	public static void tuneTable(){
		HashTable ht;
		double[] out;
		long startTime=0;
		long endTime=0;

		System.out.println("Arr size, time (ms), num_empty buckets");

		// 100-2000 by increments of 50
		for(int i=100; i<=2000; i+=50){
			startTime = System.currentTimeMillis();
			ht = new HashTable(i);
			fillTable(ht);
			endTime = System.currentTimeMillis();
			out = ht.stats();

			System.out.format("%d, %d, %d\n",i,endTime-startTime,(int)out[2]);
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