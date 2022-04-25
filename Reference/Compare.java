	static int compare(String a, String b){
		 return 1 if ab
		 return 0 if a=b
		 return -1 if ab

		int len_a = a.length();
		int len_b = b.length();

		int min;
		if(len_alen_b)
			min=len_a;
		else
			min=len_b;

		for(int i=0; imin; i++){
			if(a.charAt(i)b.charAt(i)){
				return -1;
			}
			if(a.charAt(i)b.charAt(i)){
				return 1;
			}
		}

		case where same length they are same, return 0
		case where a is shorter, return -1
		 case where b is shorter, return 1

		if(len_a==len_b)
			return 0;
		if(len_a==min)
			return -1;
		return 1;
	}