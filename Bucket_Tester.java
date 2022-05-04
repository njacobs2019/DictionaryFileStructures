public class Bucket_Tester{

	public static void main(String[] args){
		testFindNext();
	}

	public static void testFindNext(){
		Bucket b = new Bucket(5,false);
		Bucket c = new Bucket(5,false);
		Bucket d = new Bucket(5,false);
		Bucket e = new Bucket(5,false);
		Bucket f = new Bucket(5,false);

		WordNode def1 = new WordNode("Aback", "adv.", "Behind, in the rear.");
		WordNode def2 = new WordNode("Before", "adv.", "Behind, in the rear.");
		WordNode def3 = new WordNode("Aardvark", "adv.", "Behind, in the rear.");
		WordNode def4 = new WordNode("Zebra", "adv.", "Behind, in the rear.");

		System.out.println(b.insert(def1,c));
		System.out.println(b.insert(def2,d));
		System.out.println(b.insert(def3,e));
		System.out.println(b.insert(def4,f));

		b.debug_print();
		System.out.println(System.identityHashCode(b.findNext("Aaaaa")));
		System.out.println(System.identityHashCode(b.findNext("Abaaa")));
		System.out.println(System.identityHashCode(b.findNext("Abbaaaa")));
		System.out.println(System.identityHashCode(b.findNext("Zzz")));

	}

	public static void testShiftLeft(){
		Bucket b = new Bucket(5,false);
		Bucket c = new Bucket(5,false);
		Bucket d = new Bucket(5,false);

		WordNode def1 = new WordNode("Aback", "adv.", "Behind, in the rear.");
		WordNode def2 = new WordNode("Before", "adv.", "Behind, in the rear.");
		WordNode def3 = new WordNode("Aardvark", "adv.", "Behind, in the rear.");

		System.out.println(b.insert(def1,c));
		System.out.println(b.insert(def2,null));
		System.out.println(b.insert(def3,null));

		b.debug_print();
		b.shiftLeft(1);
		b.debug_print();
	}

	public static void testShiftRight(){
		Bucket b = new Bucket(5,false);
		Bucket c = new Bucket(5,false);
		Bucket d = new Bucket(5,false);

		WordNode def1 = new WordNode("Aback", "adv.", "Behind, in the rear.");
		WordNode def2 = new WordNode("Before", "adv.", "Behind, in the rear.");
		WordNode def3 = new WordNode("Aardvark", "adv.", "Behind, in the rear.");

		System.out.println(b.insert(def1,c));
		System.out.println(b.insert(def2,null));
		System.out.println(b.insert(def3,null));

		b.debug_print();
		b.shiftRight(1);
		b.debug_print();
	}

	public static void testInsert(){
		Bucket b = new Bucket(5,false);
		Bucket c = new Bucket(5,false);
		Bucket d = new Bucket(5,false);

		WordNode def1 = new WordNode("Aback", "adv.", "Behind, in the rear.");
		WordNode def2 = new WordNode("Before", "adv.", "Behind, in the rear.");
		WordNode def3 = new WordNode("Aardvark", "adv.", "Behind, in the rear.");
		WordNode def4 = new WordNode("Zebra", "adv.", "Behind, in the rear.");
		WordNode def5 = new WordNode("Fun", "adv.", "Behind, in the rear.");
		WordNode def6 = new WordNode("Before", "adv.", "2.");

		System.out.println(b.insert(def1,c));
		System.out.println(b.insert(def2,null));
		System.out.println(b.insert(def3,null));
		System.out.println(b.insert(def4,d));
		System.out.println(b.insert(def6,null));
		System.out.println(b.insert(def5,null));

		b.debug_print();
	}
}