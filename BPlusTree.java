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
				for(int i=0; i<current.BSize; i++){
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

		// Loop through all buckets
			// loop through the bucket

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
				if(p.isLeaf()){                    // isLeaf could be incorrect.  If a leaf says it is not a leaf, the code goes below it.
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

		// System.out.print("b:  ");
		// System.out.print(b);
		// System.out.println();

		// System.out.print("b.parent:  ");
		// System.out.print(b.parent);
		// System.out.println();

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
				System.out.println(root.b[0]);
			}
			
			flag = p.parent.insert(p.word[mid], other_p);
			if(!p.isLeaf()){
				p.word[mid] = null;
			}

			int i = 0;

			if(!p.isLeaf()){
				System.out.print("Splitting internal node");
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
			else if(other_p.isLeaf()){
				for(i=0; i<right; i++){
					other_p.word[i] = p.word[i+mid];
					p.word[i+mid] = null;
				}
			}


			// if(!other_p.isLeaf()){
			// 	// delete first node by shifting left
			// 	other_p.shiftLeft(0);
			// }

			// other_p.b[i] = p.b[max];
			// p.b[max] = null;

			//Update sizes of the buckets
			other_p.BSize = right;
			p.BSize = left;
			if(!other_p.isLeaf()){
				other_p.BSize = right - 1;
			}

			//Update other_p's parent
			other_p.parent = p.parent;

			// Need to update other_p's children's parent pointer
			// Bucket temp;
			// for(int j=0; j<this.max+1; j++){
			// 	temp = other_p.b[i];
			// 	if(temp!=null){
			// 		temp.parent = other_p;
			// 	}
			// }

			// Moves up a layer so we can repeat on parent if necessary
			System.out.println(p);
			System.out.println(p.parent);
			System.out.println(other_p);
			System.out.println();
			p = p.parent;
		}
	}

	// public void Remove(String name){
	// 	while(true){
	// 		Bucket p = root;
	// 		while(true){
	// 			for(int i = 0; i < p.BSize - 1; i++){
	// 				if(p.compare(name, p.word[i].getName()) == 0){
	// 					p.shiftLeft(i);
	// 					if(p.BSize < min) Steal(p);
	// 					return;
	// 				}
	// 			}
	// 			else p = p.findNext(name);
	// 		}
	// 	}
	// }

	// Boolean Steal(Bucket p){
	// 	Boolean flag = false;
	// 	int min = p.max/2;
	// 	if(p.parent == null){
	// 		return false;
	// 	}
	// 	//find way to steal to the right if leaf and up if internal
	// 	if(p.isLeaf == false){
	// 		while(true){
	// 			int i = p.parent.BSize-1;
	// 			for(i >= 0; i--){
	// 				if(p.word[BSize].getName() < p.parent.word[i].getName()){
	// 					Bucket n = p.findNext(p.parent.word[i]);
	// 					p.Insert(p.parent.word[i], n);
	// 					p.parent.word[i] = null;
	// 					p.parent.BSize -= 1;
	// 					p.parent.shiftLeft(i);
	// 					if(p.parent.BSize = 0) p.parent = null;
	// 					break;
	// 				}
	// 			}
	// 			if(p.parent.BSize < min && p != root){
	// 				p = p.parent;
	// 				Steal(p);
	// 			}
	// 			if(p.parent.BSize < min && p.parent == root){
	// 				return;
	// 			}
	// 		}
	// 		if(p.parent.BSize > min || p.parent == null) break;
	// 	}
	// 	else{
	// 		while(true){
	// 			if(p.right != null){
	// 				p.Insert(p.right.word[0]);
	// 				p.right.word[0] = null;
	// 				p.right.shiftLeft(0);
	// 				p.right.BSize -= 1;
	// 				internalUpdate(p.right.word[0]);
	// 				if(p.right.BSize < min){
	// 					p = p.right;
	// 					Steal(p);
	// 				}
	// 			}
	// 			else if(p.left != null){
	// 				p.Insert(p.left.word[BSize-1]);
	// 				p.left.word[BSize-1] = null;
	// 				p.left.BSize -= 1;
	// 				internalUpdate(p.word[0]);
	// 				if(p.left.BSize < min){
	// 					p = p.left;
	// 					Steal(p);
	// 				}
	// 			}
	// 		}
	// 	}
	// }

	// //function that checks value at parent to check if it needs updating and to update if needed

	// public void internalUpdate(String name){

	// }

	// public WordNode Search(String name){

	// }
}