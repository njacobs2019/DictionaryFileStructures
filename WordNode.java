public class WordNode{
	private String name;
	private String pos;
	private String definition;
	WordNode next;

	public WordNode(String inName, String inPos, String inDef){
		this.name = inName;
		this.pos = inPos;
		this.definition = inDef;
		this.next = null;
	}

	public String getName(){
		return this.name;
	}

	// other get methods

	// toString method

}