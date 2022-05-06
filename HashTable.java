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
	}


	// 
	public void printAll(){

	}


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

	public void add(WordNode n){
		int index = hash(n.getName());

		if(table[index]==null){
			table[index] = new HashList();
		}
		table[index].add(n);
	}

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

	private int calcHash(String name) throws NoSuchAlgorithmException{
		// Object to do the hashing
		MessageDigest md = MessageDigest.getInstance("SHA-256");   // also can use MD5 or SHA-1

		// Computes the hash
		byte[] hash1 = md.digest(name.getBytes(StandardCharsets.UTF_8));

		// Converts the hash to an integer
		BigInteger num1 = new BigInteger(1, hash1);

		int out = num1.intValue();

		return Math.abs(out)%this.size;
	}

}