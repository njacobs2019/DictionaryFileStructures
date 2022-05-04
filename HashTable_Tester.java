public class HashTable_Tester{

	public static void main(String[] args){
		HashTable ht = new HashTable(100);
		WordNode defn1 = new WordNode("Aback","adv.","Behind; in the rear.");
		WordNode defn2 = new WordNode("Aback","adv.","Toward the back or rear; backward.");
		WordNode defn3 = new WordNode("Abacus","n.","The uppermost member or division of the capital of a column, immediately under the architrave. See Column.");
		ht.add(defn1);
		ht.add(defn2);
		ht.add(defn3);

		HashNode hn = ht.search("Aback");
		System.out.println(hn);

		hn = ht.search("Abacus");
		System.out.println(hn);

		hn = ht.search("yellow");
		System.out.println(hn);
	}
}