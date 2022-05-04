// This class holds a single word and all of its definitions.

public class WordList{
	private String name;
	private WordNode head;
	private int length;

	public WordList(String inName){
		this.name = inName;
		this.head = null;
		this.length = 0;
	}

	public String getName(){
		return this.name;
	}

	public int length(){
		return this.length;
	}

	// Note this prepends the LL
	public void add(String pos, String defn){
		WordNode n = new WordNode(this.name, pos, defn);

		//Check if the LL is empty
		if(this.head==null){
			this.head = n;
		}
		else{
			n.next = this.head;
			this.head = n;
		}
		this.length++;
	}

	// Note this prepends the LL
	public void add(WordNode n){
		//Check if the LL is empty
		if(this.head==null){
			this.head = n;
		}
		else{
			n.next = this.head;
			this.head = n;
		}
		this.length++;
	}

	@Override
	public String toString(){
		String out = String.format("%s:\n",this.name);

		for(WordNode temp=this.head; temp!=null; temp = temp.next){
			out += temp.toString();
			if(temp.next!=null)
				out += "\n";
		}

		return out;
	}
}