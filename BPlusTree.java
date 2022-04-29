public class BPlusTree.java{
	int max, i;
	Bucket root = null;

	public BPlusTree(int max){
		System.out.println("B+ tree created with max size " + max);
		this.max = max;
	}

	public void add(String inName, String inPos, String inDef){
		WordNode n = new WordNode(inName, inPos, inDef);

		if(root = null){
			root = new Bucket(max, true);
			root.BSize = 1;
			root.word[0] = n;
		}
		else{
			Bucket p = root;
		}
	}
}