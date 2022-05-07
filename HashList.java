// This is a hashlist. It is a linked list that is pointed to by the HashTable.
// It holds HashNodes which contain a single word and all of its definitions.

class HashList{
	public HashNode head;
	private int length;      // Number of words in this linked list

	public HashList(){
		this.head = null;
		this.length = 0;
	}

	// Returns the number of words in the linked list
	public int length(){
		return this.length;
	}

	// Adds a WordNode (single definition) to the linked list by prepending it
	public void add(WordNode n){
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

	// Returns the specified HashNode in the HashList if it exists, else null.
	public HashNode search(String name){
		name = name.toLowerCase();
		for(HashNode temp=this.head; temp!=null; temp = temp.next){
			if(temp.getName().equals(name)){
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