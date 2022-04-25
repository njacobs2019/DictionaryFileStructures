// Nicholas Jacobs
// Branching Out HW - B Tree - COS 226
// Due 4/15/2022
// Last worked on:  

// Used to implement a queue for a Breadth-First Traversal
import java.util.LinkedList;
import java.util.Queue;

class BTree{
	private int max_degree;
	private Bucket root;

	public BTree(int mx_degree){
		this.max_degree = mx_degree;
		this.root = null;
	}

	public void printBFS(){
		Queue<Bucket> q = new LinkedList<>();
		q.add(root);
		Bucket current;
		while(q.size()>0){
			current = q.poll();
			if(current!=null){
				System.out.print(current);
				for(int i=0; i<this.max_degree; i++){
					q.add(current.bArray[i]);
				}
			}
		}
	}

	public void add(int data){
		BNode n = new BNode(data);
		if(root==null){
			// Create a new root bucket
			this.root = new Bucket(this.max_degree);
			root.insert(n, null);
			return;
		}

		// Need to find correct location to add the node
		Bucket p = this.root;
		boolean flag = false;   // whether or not we need to split
		while(true){
			// We have reached the bottom of the tree
			if(p.bArray[0]==null){
				flag = p.insert(n, null);
				if(flag){
					split(p);
				}
				return;
			}
			else{
				p = p.findNext(data);  // search through and if hit null, went too far, if node is too big, went too far and use previous bucket pointer
			}
		}
	}

	public void split(Bucket b){
		int mid;              // Index of middle element
		Bucket other_b;       // New bucket
		boolean flag = true;  // Tracks if we need to keep splitting up the tree.  Assumes true at first.
		int left;             // Elements to right of mid
		int right;            // Elements to left of mid


		// System.out.print("b:  ");
		// System.out.print(b);
		// System.out.println();

		// System.out.print("b.parent:  ");
		// System.out.print(b.parent);
		// System.out.println();


		while(flag){
			// Error check that b is not null;
			if(b==null){
				System.out.println("\n\nERROR:  Tried splitting a null bucket.");
				System.exit(1);
			}

			// Calculates sizes and mid index
			mid = this.max_degree/2;
			left = mid;
			right = this.max_degree-mid-1;

			other_b = new Bucket(this.max_degree);         // Creates the new bucket

			//Handles case where b is the root
			if(b.parent == null){
				this.root = new Bucket(this.max_degree);
				this.root.bArray[0] = b;
				b.parent = this.root;
			}
			
			flag = b.parent.insert(b.nArray[mid], other_b);
			b.nArray[mid] = null;

			int i;
			for(i=0; i<right; i++){
				other_b.nArray[i] = b.nArray[i+mid+1];
				b.nArray[i+mid+1] = null;

				other_b.bArray[i] = b.bArray[i+mid+1];
				b.bArray[i+mid+1] = null;
			}

			other_b.bArray[i] = b.bArray[max_degree];
			b.bArray[max_degree] = null;

			//Update sizes of the buckets
			other_b.size = right;
			b.size = left;

			//Update other_b's parent
			other_b.parent = b.parent;

			// Need to update other_b's children's parent pointer
			Bucket temp;
			for(int j=0; j<this.max_degree+1; j++){
				temp = other_b.bArray[i];
				if(temp!=null){
					temp.parent = other_b;
				}
			}


			// Moves up a layer so we can repeat on parent if necessary
			b = b.parent;
		}
	}
}