import java.util.LinkedList;
import java.util.Queue;

public class BPlusTree{
	private int max;
	private Bucket root, start;

	public BPlusTree(int max){
		this.max = max;
		this.root = null;
		System.out.println("B+ tree created with max size " + max);
	}

	// Works!
	public void printBFS(){
		Queue<Bucket> q = new LinkedList<>();
		q.add(root);
		Bucket current;
		while(q.size()>0){
			current = q.poll();
			if(current!=null){
				System.out.print(current);
				//current.debug_print();
				System.out.println();
				for(int i=0; i<current.max; i++){
					q.add(current.b[i]);
				}
			}
		}
	}


	public void printAll(){
		// This function prints the entire dictionary
		Bucket start = this.root;

		while(start.b[0]!=null){
			start = start.b[0];
		}

		for(Bucket temp=start; temp!=null; temp = temp.right){
			System.out.println(temp);
		}
	}

	// Works!
	public void add(WordNode n){
		if(root == null){
			root = new Bucket(max);
			root.insert(n,null);
		}
		else{
			Bucket p = root;
			while(true){
				if(p.isLeaf()){
					Boolean flag = p.insert(n, null);
					if(flag){
						split(p);
					}
					return;
				}
				else{
					p = p.findNext(n.getName());
				}
			}
		}
	}

	// Works!
	public void split(Bucket p){
		int mid;              // Index of middle element
		Bucket other_p;       // New bucket
		boolean flag = true;  // Tracks if we need to keep splitting up the tree.  Assumes true at first.
		int left;             // Elements to right of mid
		int right;            // Elements to left of mid

		while(flag){
			// Error check that b is not null;
			if(p==null){
				System.out.println("\n\nERROR:  Tried splitting a null bucket.");
				System.exit(1);
			}

			// Calculates sizes and mid index
			mid = this.max/2;
			left = mid;
			right = this.max-left;

			other_p = new Bucket(this.max);         // Creates the new bucket

			//adjust left and right pointers
			if(p.isLeaf()){
				other_p.right = p.right;
				p.right = other_p;
				other_p.left = p;

				if(other_p.right!=null){
					other_p.right.left = other_p;
				}
			}

			//Handles case where p is the root
			if(p.parent == null){
				this.root = new Bucket(this.max);
				this.root.b[0] = p;
				p.parent = this.root;
			}
			
			//adds word at mid index of p to new parent
			flag = p.parent.insert(p.word[mid], other_p);
			//if p is an internal bucket, p.word[mid] is deleted
			if(!p.isLeaf()){
				p.word[mid] = null;
			}

			int i = 0;

			//splits internal bucket
			if(!p.isLeaf()){
				//new bucket's 0 pointer is set equal to 
				other_p.b[0] = p.b[mid+1];
				p.b[mid+1] = null;
				if(other_p.b[0] != null){
					other_p.b[0].parent = other_p;
				}
				for(i=0; i<right-1; i++){
					other_p.word[i] = p.word[i+mid+1];
					p.word[i+mid+1] = null;

					other_p.b[i+1] = p.b[i+mid+2];
					p.b[i+mid+2] = null;
					if(other_p.b[i+1] != null){
						other_p.b[i+1].parent = other_p;
					}
				}
			}
			else{
				for(i=0; i<right; i++){
					other_p.word[i] = p.word[i+mid];
					p.word[i+mid] = null;
				}
			}

			//Update sizes of the buckets
			other_p.BSize = right;
			p.BSize = left;
			if(!other_p.isLeaf()){
				other_p.BSize = right - 1;
			}

			//Update other_p's parent
			other_p.parent = p.parent;

			// Moves up a layer so we can repeat on parent if necessary
			p = p.parent;
		}
	}


}