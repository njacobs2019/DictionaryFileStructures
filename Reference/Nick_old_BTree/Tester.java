class Tester{

	public static void main(String[] args){
		// Conducts three unit tests on the Splay tree
	    System.out.print("\nUnit testing on Splay Tree:\n\n");

	    int[] data1 = {10, 12, 30};
	    splay_unitTest(data1, 1);

	    int[] data2 = {80, 20, 10, 15, 100, 75, 90};
	    splay_unitTest(data2, 2);

	    int[] data3 = {};
	    splay_unitTest(data3, 3);


	    // Tests out the entire BTree class
		System.out.println("\nThis tests the BTree and shows intermediate BFS traversal:");
		System.out.println("Note:  [Node] and *Bucket*\n");

		int[] data = {22,34,4,2,1,12,44,5,23,8,20,28,29,30,35,36,37,38,39,40,41,42,43};
		BTree tree = new BTree(5);

		for(int i=0; i<data.length; i++){
			tree.add(data[i]);
			System.out.format("Added:  %d\n", data[i]);
			tree.printBFS();
			System.out.println("");
		}
	}

	 static void splay_unitTest(int[] data, int test){
	    

	    System.out.format("Unit Test %d:\n",test);
	    System.out.print("Data to be added:\t");
	    for(int i=0; i<data.length; i++){
	      System.out.format("[%d]", data[i]);
	    }
	    System.out.println();

	    Splay x = new Splay();

	    for(int i=0; i<data.length; i++){
	      x.add(data[i]);
	    }
	    
	    System.out.print("BFS traversal:\t\t");
	    x.printBFS();

	    System.out.print("\nPre-Order traversal:\t");
	    x.preOrderPrint();

	    System.out.print("In-Order traversal:\t");
	    x.inOrderPrint();

	    for(int i=0; i<50; i++)
	      System.out.print("*");
	    System.out.println();
  }
}