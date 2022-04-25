// Nicholas Jacobs
// Branching Out HW - B Tree - COS 226
// Due 4/15/2022
// Last worked on:  

class Bucket{
	private int max_degree;
	public BNode[] nArray;    // Array of node pointers
	public Bucket[] bArray;   // Array of bucket pointers
	public Bucket parent;     // Pointer to the parent buckettt
	public int size;      // Keeps track of how full the bucket is

	public Bucket(int mx_degree){
		this.max_degree = mx_degree;
		this.nArray = new BNode[max_degree];
		this.bArray = new Bucket[max_degree+1];
		this.parent = null;
		this.size = 0;
	}

	public boolean insert(BNode n, Bucket b_pointer){
		// b_pointer is larger than n

		// needs to be able to shift nodes across
		// returns whether or not we need to split, i.e. the bucket is full

		// Error checking
		if(this.size==this.max_degree){
			System.out.println("\n\nERROR:  Tried inserting to a full bucket.");
			System.exit(1);
		}

		//find index where the data belongs
		int i=0;
		for(;i<this.size; i++){
			if (n.getData() < this.nArray[i].getData())
				break;
		}

		//shift if necessary
		if(i!=size){
			//shift contents
			for(int j=this.size; j>i; j--){
				this.nArray[j] = this.nArray[j-1];
				this.bArray[j+1] = this.bArray[j];
			}
		}

		// insert data into nArr[i] and nArr[i+1]
		this.nArray[i] = n;
		this.bArray[i+1] = b_pointer;
		this.size++;
		return this.CheckIfSplit();
	}


	public Bucket findNext(int data){
		for(int i=0; i<this.size; i++){
			if(data < this.nArray[i].getData()){
				return this.bArray[i];
			}
		}
		return this.bArray[this.size];
	}

	// If the bucket is full, then it needs to split and returns true
	public boolean CheckIfSplit(){
		return this.size >= this.max_degree;
	}


	// Prints everything for debugging purposes
	public void debug_print(){
		System.out.format("max_degree:  %d\n", this.max_degree);
		System.out.format("size:  %d\n", this.size);
		System.out.format("parent:  %d\n", System.identityHashCode(this.parent));
		System.out.print("nArray:  ");
		for(int i=0; i<this.max_degree; i++){
			System.out.print(this.nArray[i]);
			System.out.print(" ");
		}
		System.out.println("");
		System.out.print("bArray:  ");
		for(int i=0; i<this.max_degree+1; i++){
			System.out.print(System.identityHashCode(this.bArray[i]));
			System.out.print(" ");
		}
		System.out.println("\n*****\n");
	}


	@Override
  	public String toString(){
    	String out = "*";
    	for(int i=0; i<size; i++){
    		out = out + nArray[i].toString();
    	}
    	out = out + "*";
    	return out;
  	}
}