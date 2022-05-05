public class Bucket_Tester{

	public static void main(String[] args){
		testInsertWL();
	}


	public static void testToString(){
		Bucket b = new Bucket(5,false);
		WordNode def1 = new WordNode("Aback", "adv.", "Behind, in the rear.");
		WordNode def2 = new WordNode("Before", "adv.", "Behind, in the rear.");
		WordNode def3 = new WordNode("Aardvark", "adv.", "Behind, in the rear.");
		WordNode def4 = new WordNode("Zebra", "adv.", "Behind, in the rear.");

		b.insert(def1,null);
		b.insert(def2,null);
		b.insert(def3,null);
		b.insert(def4,null);

		System.out.println(b);


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

	// Tests overloaded insert with the wordlist
	public static void testInsertWL(){
		Bucket b = new Bucket(5,false);
		Bucket c = new Bucket(5,false);
		Bucket d = new Bucket(5,false);

		WordNode def1 = new WordNode("Aback", "adv.", "Behind, in the rear.");
		WordNode def2 = new WordNode("Before", "adv.", "Behind, in the rear.");
		WordNode def3 = new WordNode("Aardvark", "adv.", "Behind, in the rear.");
		WordNode def4 = new WordNode("Zebra", "adv.", "Behind, in the rear.");
		WordNode def5 = new WordNode("Fun", "adv.", "Behind, in the rear.");
		WordNode def6 = new WordNode("Before", "adv.", "2.");

		WordList wor1 = new WordList("Aback");
		wor1.add(def1);
		WordList wor2 = new WordList("Before");
		wor2.add(def2);
		WordList wor3 = new WordList("Aardvark");
		wor3.add(def3);
		WordList wor4 = new WordList("Zebra");
		wor4.add(def4);
		WordList wor5 = new WordList("Fun");
		wor5.add(def5);

		System.out.println(b.insert(wor1,c));
		System.out.println(b.insert(wor2,null));
		System.out.println(b.insert(wor3,null));
		System.out.println(b.insert(wor4,d));
		System.out.println(b.insert(wor5,null));

		b.debug_print();
	}
}