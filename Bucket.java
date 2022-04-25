public class Bucket{
	WordList word[];
	Bucket b[];
	Bucket parent;
	int BSize;
	int max;

	Bucket(int max){
		BSize = 0;
		word = null;
		b = new Bucket[max+1];
		this.max = max;
		parent = null;
	}

	void shiftRight(int x){
        for(int i = BSize-1; i >= x; i--){
            word[i+1] = word[i];
            b[i+2] = b[i+1];
        }
    }

    void shiftLeft(int x){
    	for(int i = x; i < BSize-1; i++){
    		word[i] = word[i+1];
    		b[i+1] = b[i+2];
    	}
    }

	//insert with internal or leaf override



	BBucket findNext(int n){
        for(int i=0; i < this.BSize; i++){
            if(n < this.word[i].getData()){
                return this.b[i];
            }
        }
        return this.b[this.BSize];
    }


	//tostring testing visualization return string of words in bucket
}