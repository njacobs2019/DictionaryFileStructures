// Nicholas Jacobs
// Branching Out HW - B Tree - COS 226
// Due 4/15/2022
// Last worked on:  

class BNode{
	private int data;

	public BNode(int inData){
		this.data = inData;
	}

	public int getData(){
		return this.data;
	}

	public void setData(int inData){
		this.data = inData;
	}

	@Override
  	public String toString(){
    	return "[" + data + "]";
  	}
}