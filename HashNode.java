// This is a node in the hashtable's linked lists
// This node holds a single wordlist i.e. a single word and all of its definitions

class HashNode extends WordList{
	public HashNode next;

	public HashNode(String inName){
		super(inName.toLowerCase());
		next = null;
	}
}