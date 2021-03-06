import java.math.BigInteger; 
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.lang.Math;

public class HashTable{
	private HashList[] table;
	private int size;

	public HashTable(int inSize){
		size = inSize;
		table = new HashList[size];
		System.out.format("Hash Table created with array size %d\n",size);
	}

	// Returns statistics about this.table
	public double[] stats(){
		int num_words=0;
		int num_empty_buckets=0;
		int temp_num;

		for(int i=0; i<this.size; i++){
			if(table[i]==null)
				temp_num=0;
			else
				temp_num = table[i].length();

			num_words += temp_num;
			if(temp_num==0)
				num_empty_buckets++;
		}

		int num_full_buckets = this.size-num_empty_buckets;
		int avg_all = num_words/this.size;                   // average words per bucket
		int avg_full = num_words/num_full_buckets;           // average words per non empty bucket

		// Calculating standard deviation of all buckets
		int sigma = 0;

		for(int i=0; i<this.size; i++){
			if(table[i]==null)
				temp_num=0;
			else
				temp_num = table[i].length();

			sigma += Math.pow((temp_num-avg_all),2);
		}

		double quotient = ((double) sigma)/this.size;
		double sd = Math.pow(quotient,0.5);

		double[] out = {avg_full, sd, num_empty_buckets};
		return out;
	}

	// Prints all HashNodes in the table (not alphabetical)
	public void printAll(){
		for(int i=0; i<size; i++){
			// Check if null
			if(table[i]!=null){
				for(HashNode temp=table[i].head; temp!=null; temp=temp.next){
					System.out.println(temp);
				}
			}
		}
	}

	// Returns the next word alphabetically
	public HashNode findNext(String name){
		HashNode out = null;
		for(int i=0; i<size; i++){
			if(table[i]!=null){
				for(HashNode temp=table[i].head; temp!=null; temp=temp.next){
					if(out==null && compare(temp.getName(),name)==1){
						out=temp;
					}
					else if(compare(temp.getName(),name)==1 && compare(temp.getName(),out.getName())==-1){
						out=temp;
					}
				}
			}
		}
		return out;
	}

	// Prints out the entire dictionary in alphabetical order
	// Warning:  extremely slow
	public void printAllAlpha(){
		partialPrint("a",10000000,true);
	}

	// Prints out the definition for the input word if exists
	// Prints out the next num-1 words after
	public void partialPrint(String name, int num, Boolean all){
		HashNode tempNode = search(name);
		if(tempNode==null){
			System.out.println("Start word not in dictionary");
			return;
		}

		System.out.println(search(name));

		for(int i=0; i<num-1; i++){
			tempNode = findNext(tempNode.getName());

			if(tempNode==null){
				if(!all)
					System.out.println("Hit end of dictionary before printing all.");
				return;
			}

			if(all){
				System.out.println(tempNode.getName());
			}
			else{
				System.out.println(tempNode);
			}
		}
	}

	//Over written partialPrint interface
	public void partialPrint(String name, int num){
		partialPrint(name,num,false);
	}

	// Returns the HashNode containing all the definitions of the word if it exists.
	public HashNode search(String name){
		// returns null if not found
		name = name.toLowerCase();

		int index = hash(name);

		if(table[index]==null){
			System.out.println("Short-circuit");
			return null;
		}
		
		return table[index].search(name);
	}

	// Adds a word to the HashTable
	public void add(WordNode n){
		int index = hash(n.getName());

		if(table[index]==null){
			table[index] = new HashList();
		}
		table[index].add(n);
	}

	// Wrapper method for calcHash to catch exceptions.
	private int hash(String name){
		int out=-1;
		try{
			out = calcHash(name);
		}
		catch(NoSuchAlgorithmException e){
			System.out.println("Your machine does not support the hash algorithm.");
		}
		return out;
	}	

	// Calculates the index for a given word
	private int calcHash(String name) throws NoSuchAlgorithmException{
		// Object to do the hashing
		MessageDigest md = MessageDigest.getInstance("SHA-1");   // also can use MD5 or SHA-1

		// Computes the hash
		byte[] hash1 = md.digest(name.getBytes(StandardCharsets.UTF_8));

		// Converts the hash to an integer
		BigInteger num1 = new BigInteger(1, hash1);

		int out = num1.intValue();

		return Math.abs(out)%this.size;
	}

	// Compares two strings alphabetically
	private static int compare(String a, String b){
		// return 1 if a>b
		// return 0 if a=b
		// return -1 if a<b

		int min = a.length();

		if(b.length() < min){
			min = b.length();
		}

		for(int i = 0; i < min; i++){
			if(a.charAt(i) < b.charAt(i)){
				return -1;
			}
			if(a.charAt(i) > b.charAt(i)){
				return 1;
			}
		}

		if(a.length()>b.length()){
			return 1;
		}
		else if(a.length()<b.length()){
			return -1;
		}
		return 0;
	}
}