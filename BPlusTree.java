public class BPlusTree.java{
	int max, i;
	Bucket root = null;

	public BPlusTree(int max){
		System.out.println("B+ tree created with max size " + max);
		this.max = max;
	}

	public void add(String inName, String inPos, String inDef){
		WordNode n = new WordNode(inName, inPos, inDef);

		if(root == null){
			root = new Bucket(max, true);
			root.BSize = 1;
			root.word[0] = n;
		}
		else{
			Bucket p = root;
			if(p.isLeaf == true){
				Boolean flag = p.Insert(n, null);
				while(flag == true){
					flag = split(p);
					p = p.parent;
				}
				return;
			}
			else{
				p = p.findNext(inName);
			}
		}
	}

	Boolean Split(Bucket p){
		Boolean flag = false;
		while(true){
			int mid = max/2;
			if(p.parent == null){
				root = new Bucket(max, false);
				root.b[0] = p;
				p.parent = root;
			}
			if(p.isLeaf == false){
				Bucket other = new Bucket(max, false);
				flag = p.parent.Insert(p.word[mid], other);
				p.word[mid] = null;
				other.b[0] = p.b[mid+1];
				p.b[mid+1] = null;
				for(int i = 0; i < mid, i++){
					other.Insert(p.word[mid+i+1], p.b[mid+i+2]);
					p.word[mid+i+1] = null; p.b[mid+i+2] = null;
				}
				other.parent = p.parent;
				p.BSize = mid;
				p = p.parent;
			}
			else{
				Bucket other = new Bucket(max, true);
				flag = p.parent.Insert(p.word[mid], other);
				flag = other.Insert(p.word[mid], null);
				p.word[mid] = null;
				other.right = p.right;
				other.left = p;
				for(int i = 0; i < mid; i++){
					other.Insert(p.word[mid+i+1], p.b[mid+i+2]);
					p.word[mid+i+1] = null;
				}
				other.parent = p.parent;
				p.right = other;
				p.BSize = mid;
				p = p.parent;
			}
			if(flag != true) break;
		}
		return false;
	}

	public void Remove(String name){

	}

	Boolean Steal(Bucket p){
		Boolean flag = false;
		int min = b.max/2;
		//find way to steal to the right if leaf and up if internal
	}

	//function that checks value at parent to check if it needs updating and to update if needed

	public WordNode Search(String name){

	}
}