public class WordList_Tester{

	public static void main(String[] args){
		WordNode defn = new WordNode("Aback","adv.","Behind; in the rear.");

		WordList wordl = new WordList("Aback");
		wordl.add(defn);
		wordl.add("adv.","Toward the back or rear; backward.");

		System.out.format("Num definitions:  %d\n",wordl.length());
		System.out.println(wordl);
	}
}