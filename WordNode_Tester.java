public class WordNode_Tester{

	public static void main(String[] args){
		WordNode n = new WordNode("A	prep.	In; on; at; by.");
		System.out.println(n);

		WordNode n2 = new WordNode("A","prep.","In; on; at; by.");
		System.out.println(n2);
	}
}