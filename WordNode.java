public class WordNode{
	private String name;
	private String pos;
	private String definition;
	public WordNode next;

	public WordNode(String inName, String inPos, String inDef){
		this.name = inName;
		this.pos = inPos;
		this.definition = inDef;
		this.next = null;
	}

	// public WordNode(String input){
		
	// }

	public String getName(){
		return this.name;
	}

	public String getPos(){
		return this.pos;
	}

	public String getDef(){
		return this.definition;
	}

	@Override
	public String toString(){
		return String.format("(%s) %s",pos,definition);
	}
}