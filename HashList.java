// This is a hashlist. It is a linked list that is pointed to by the hashtable.
// It holds HashNodes which contain a single word and all of its definitions.

class HashList{
	private HashNode head;
	private int length;      // how many words are in this linked list

	public HashList(){
		this.head = null;
		this.length = 0;
	}

	public int length(){
		// Returns the number of words in the linked list
		return this.length;
	}

	public void add(WordNode n){
		// adds a wordnode

		// Find the hashnode to add to
		String name = n.getName();
		HashNode hn = search(name);

		if(hn==null){
			// create new hashnode and add to LL
			hn = new HashNode(name);
			hn.add(n);
			hn.next = this.head;
			this.head = hn;
			this.length++;
		}
		else{
			hn.add(n);
		}
	}

	public HashNode search(String name){
		for(HashNode temp=this.head; temp!=null; temp = temp.next){
			if(temp.getName()==name){
				// hit
				return temp;
			}
		}
		return null;
	}

	@Override
	public String toString(){
		String out = "";

		for(HashNode temp=this.head; temp!=null; temp = temp.next){
			// out += "HashNode:\n";    // uncomment for testing purposes
			out += temp.toString();
			if(temp.next!=null){
				out+="\n";
			}
		}
		return out;
	}
}