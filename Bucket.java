// This is a bucket for the B+ tree.

public class Bucket{
	public WordList word[];
	public Bucket b[];
	public Bucket parent, right, left;
	public int BSize;
	public int max;

	public Bucket(int max){
		this.BSize = 0;
		word = new WordList[max];
		for(int j = 0; j < max; j++){
			word[j] = null;
		}
		b = new Bucket[max+1];
		for(int i = 0; i < max+1; i++){
			b[i] = null;
		}
		this.max = max;
		parent = null;
		right = null;
		left = null;
	}

	// Determines if the bucket is a leaf
	public Boolean isLeaf(){
		return b[0]==null;
	}

	// Shifts everything to the right including index x
	public void shiftRight(int x){
        for(int i = BSize-1; i >= x; i--){
            word[i+1] = word[i];
            b[i+2] = b[i+1];
        }
        this.BSize++;
    }

	// X is equal to the index
    // Variable at index x is getting writtenover
    public void shiftLeft(int x){
    	for(int i = x; i < BSize; i++){
    		word[i] = word[i+1];
    		b[i+1] = b[i+2];
    	}
    	this.BSize--;
    }

    // Compares two strings alphabetically
    public static int compare(String a, String b){
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

    // Only to be called from split function
    // Inserts a wordlist into the bucket
    public Boolean insert(WordList l, Bucket p){
    	for(int i = 0; i <= BSize; i++){
    		// Reaches null, it is end of bucket, insert
    		if(word[i] == null){
    			word[i] = l;
    			b[i+1] = p;
    			BSize++;
    			break;
    		}
    		// If current position is bigger than w, shift and insert
    		else if(compare(l.getName(), word[i].getName()) == -1){
    			shiftRight(i);    // shiftRight increments BSize
    			word[i] = l;
    			b[i+1] = p;
    			break;
    		}
    	}

    	// If BSize is equal to max, the bucket is full
    	// If bucket is full and we need to split
    	return (this.BSize == this.max);
    }


    // Inserts a WordNode (single definition) into the dictionary
    public Boolean insert(WordNode w, Bucket p){
    	for(int i = 0; i <= BSize; i++){
    		// Reaches null, it is end of bucket, insert
    		if(word[i] == null){
    			word[i] = new WordList(w.getName());
    			word[i].add(w);
    			b[i+1] = p;
    			BSize++;
    			break;
    		}
    		// If current position is bigger than w, shift and insert
    		else if(compare(w.getName(), word[i].getName()) == -1){
    			shiftRight(i);    // increments BSize
    			word[i] = new WordList(w.getName());
    			word[i].add(w);
    			b[i+1] = p;;
    			break;
    		}
    		// if current position is same word, add new definition
    		else if(compare(w.getName(), word[i].getName()) == 0){
    			word[i].add(w);
    			break;
    		}
    	}

    	// If BSize is equal to max, the bucket is full
    	// If bucket is full and we need to split
    	return (this.BSize == this.max);
    }

    // Returns the bucket to traverse to next
	public Bucket findNext(String name){
        for(int i=0; i < this.BSize; i++){
            if(compare(name, this.word[i].getName()) == -1){
                return this.b[i];
            }
        }
        return this.b[this.BSize];             // Nate thinks this might point to a null
    }

    // Prints everything for debugging purposes
	public void debug_print(){
		System.out.format("Bucket hash:  %s\n",System.identityHashCode(this));
		System.out.format("max_degree:  %d\n", this.max);
		System.out.format("size:  %d\n", this.BSize);
		System.out.format("parent:  %d\n", System.identityHashCode(this.parent));
		System.out.print("nArray:  ");
		for(int i=0; i<this.max; i++){
			if(this.word[i]==null)
				System.out.print(this.word[i]);
			else
				System.out.print(this.word[i].getName());
			System.out.print(" ");
		}
		System.out.println("");
		System.out.print("bArray:  ");
		for(int i=0; i<this.max+1; i++){
			System.out.print(System.identityHashCode(this.b[i]));
			System.out.print(" ");
		}
		System.out.println("\n*****\n");
	}

	@Override
  	public String toString(){
    	String out;
  		if(isLeaf())
  			out = "^";
  		else
  			out = "*";

    	for(int i=0; i<BSize; i++){
    		out += "["+word[i].getName()+"]";
    	}
    	
    	if(isLeaf())
  			out = out + "^";
  		else
  			out = out + "*";
    	return out;
  	}
}