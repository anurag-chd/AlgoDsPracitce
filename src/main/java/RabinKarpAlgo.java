public class RabinKarpAlgo {

	public static void main(String args []){
		String txt = "GEEKS FOR GEEKS";
		String pat = "GEEK";

		int p = 101;
		System.out.println(search(txt, pat, p));
		assert(search(txt, pat, p) == 4);


	}

	public static int search(String txt, String pat, int p){
		int patH = 0;
		for(int i = 0;i < pat.length(); i++){
			patH += (int)(pat.charAt(i) - '0') * (int)Math.pow(p,i);
		}

		int start = -1;
		int end = 0;
		int substrHash = 0;
		while(end < txt.length()){
			while(end < txt.length() && end - start <= pat.length()){
				substrHash += (int)(txt.charAt(end) - '0') * (int)Math.pow(p, end -start -1);
				end++;
			}
			start++;
			if(substrHash == patH){
				int i = 0;
				while(i < pat.length()){
					if(txt.charAt(start + i) != pat.charAt(i)){
						break;
					}
					i++;
				}
				if(i == pat.length())
					return start;

			}
			substrHash -= (int)(txt.charAt(start) - '0');
			substrHash /= p;

		}

		return -1;
	}
}
