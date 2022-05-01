public class Bucket{
	WordList word[];
	Bucket b[];
	Bucket parent, right, left;
	int BSize;
	int max;
	Boolean isLeaf = null;

	Bucket(int max, Boolean isLeaf){
		BSize = 0;
		word = null;
		b = new Bucket[max+1];
		this.max = max;
		parent = null;
		right = null;
		left = null;
		this.isLeaf = isLeaf;
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

    static int compare(String a, String b){
    	int min = a.length();

    	if(b.length() < min){
    		min = b.length();
    	}

    	for(int i = 0; i < min; i++){
    		if(a.charAt(i) < b.charAt(i)){
    			return -1;
    		}
    		if(a.charAt(i) > b.charAt(i)){
    			return 1;
    		}
    	}
    	return 0;
    }

	//insert with internal or leaf override

    Boolean Insert(WordNode w, Bucket p){
	    if(isLeaf == false){
	    	for(int i = 0; i < BSize; i++){
	    		if(word[i] == null){
	    			word[i] = w;
	    			b[i+1] = p;
	    			BSize++;
	    			break;
	    		}
	    		if(compare(w.getName(), word[i].getName()) == 0){
	    			shiftRight(i);
	    			word[i] = w;
	    			b[i+1] = p;
	    			BSize++;
	    			break;
	    		}
	    	}
	    }
	    else{
	    	for(int i = 0; i < BSize; i++){
	    		if(word[i] == null){
	    			word[i] = w;
	    			BSize++;
	    			break;
	    		}
	    		if(compare(w.getName(), word[i].getName()) == 0){
	    			shiftRight(i);
	    			word[i] = w;
	    			BSize++;
	    			break;
	    		}
	    	}
	    }
    	if(word[max] != null){
    		return true;
    	}
    	return false;
    }


	BBucket findNext(String n){
        for(int i=0; i < this.BSize; i++){
            if(compare(n, this.word[i].getName()) == 0){
                return this.b[i];
            }
        }
        return this.b[this.BSize];
    }


	//tostring testing visualization return string of words in bucket
	@Override
	public String toString(){
		String buc = "";
		for(int i = 0; i < BSize; i++){
			buc += word[i].getName();
		}
		return buc;
	}
}