public class KmpStringMatching {
	public static void main(String args[]){
		String txt = "AAAAABAAABA";
		String pat = "AAABA";
		System.out.println(search(txt,pat));
	}

	public static int search(String txt, String pat){
		int lps[] = getLPS(pat);

		int patIndex = 0;
		int txtIndex = 0;
		while(txtIndex < txt.length()){
			if(txt.charAt(txtIndex) == pat.charAt(patIndex)){
				patIndex++;
				if(patIndex == pat.length()){
					return txtIndex - pat.length() + 1;
				}
				txtIndex++;
			}
			else if(patIndex == 0){
				txtIndex++;
			}
			else{
				patIndex = lps[patIndex-1];

			}


		}

		return -1;

	}

	public static int [] getLPS(String pat){
		int lps[] = new int[pat.length()];
		lps[0] =  0;
		int i = 0;
		int j = 1;
		while(j < pat.length()){
			if(pat.charAt(j) == pat.charAt(i)){
				lps[j] = i +1;
				i++;
			}
			else{
				while(i > 0 && pat.charAt(j) != pat.charAt(i)){
					i = lps[i-1];
				}
				if(pat.charAt(j) == pat.charAt(i)){
					lps[j] = i + 1;
					i++;
				}

			}
			j++;

		}
		return lps;
	}
}
