public class HashList_Tester{

	public static void main(String[] args){
		WordNode defn1 = new WordNode("Aback","adv.","Behind; in the rear.");
		WordNode defn2 = new WordNode("Aback","adv.","Toward the back or rear; backward.");
		WordNode defn3 = new WordNode("Abacus","n.","The uppermost member or division of the capital of a column, immediately under the architrave. See Column.");

		HashList hl = new HashList();
		hl.add(defn1);
		hl.add(defn2);
		hl.add(defn3);

		System.out.format("This hashlist contains %s words\n",hl.length());
		System.out.println(hl);

		
		System.out.println("--------------");
		HashNode hn = hl.search("Aback");
		System.out.println(hn);

		
		System.out.println("--------------");
		hn = hl.search("yellow");
		System.out.println(hn);
	}
}