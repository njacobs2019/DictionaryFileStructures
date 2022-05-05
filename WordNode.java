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

	public WordNode(String input){
		String[] output;
		output = input.split("\t");

		if(output.length!=3){
			System.out.println("Error with overloaded WordNode constructor");
			System.out.println("This was the line:");
			System.out.println(input);
			System.exit(1);
		}

		this.name = output[0];
		this.pos = output[1];
		this.definition = output[2];
		this.next = null;
	}

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