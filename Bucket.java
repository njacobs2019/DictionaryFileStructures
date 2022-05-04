public class Bucket{
	WordList word[];
	Bucket b[];
	Bucket parent, right, left;
	int BSize;
	int max;
	Boolean isLeaf = null;

	public Bucket(int max, Boolean isLeaf){
		this.BSize = 0;
		word = new WordList[max];
		b = new Bucket[max+1];
		this.max = max;
		parent = null;
		right = null;
		left = null;
		this.isLeaf = isLeaf;
	}

	// Tested!
	public void shiftRight(int x){
		// shifts everything to the right including index x

        for(int i = BSize-1; i >= x; i--){
            word[i+1] = word[i];
            b[i+2] = b[i+1];
        }
    }

	// Tested!
    public void shiftLeft(int x){
    	// when shiftleft is called, x is equal to the index
    	// variable at index x is getting writtenover

    	for(int i = x; i < BSize; i++){
    		word[i] = word[i+1];
    		b[i+1] = b[i+2];
    	}
    }

    // Tested!
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
    	return 0;
    }

    // Tested!
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
    			shiftRight(i);
    			word[i] = new WordList(w.getName());
    			word[i].add(w);
    			b[i+1] = p;
    			BSize++;
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

    // Tested
	public Bucket findNext(String name){
		// Returns the bucket to go to next

        for(int i=0; i < this.BSize; i++){
            if(compare(name, this.word[i].getName()) == -1){
                return this.b[i];
            }
        }
        return this.b[this.BSize];
    }

    // Prints everything for debugging purposes
	public void debug_print(){
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
}