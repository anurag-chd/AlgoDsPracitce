import java.util.Arrays;
import java.util.HashMap;

public class MultiplyStrings {

	public static void main(String args[]){

		String num1 = "19";
		String num2 = "19";
		HashMap<Integer,Integer> map = new HashMap<>();
		map.put(2,map.getOrDefault(2,0)+1);
		HashMap<Integer,Integer> map2 = new HashMap<>();
		map2.put(3,map2.getOrDefault(3,0) +1);
		int a = 2;
		int b = 3;
		System.out.println(map.get(a) == map2.get(b));

		System.out.println(multiply(num1, num2));

	}


	public static String multiply(String num1, String num2){
		String res = "";

		int resArr[] = new int[num1.length() + num2.length()];

		for(int i = num1.length() -1; i >= 0; i--){
			for(int j = num2.length()-1; j >= 0; j--){
				resArr[i + j+1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
			}
		}
		int carry = 0;
		for(int i = resArr.length-1; i>=0;i--){
			resArr[i] += carry;
			carry = resArr[i]/10;
			resArr[i] = resArr[i]%10;
		}


		//System.out.println(Arrays.toString(resArr));
		boolean numStarted = false;
		for(int i = 0; i < resArr.length; i++){
			if(!numStarted && resArr[i] == 0){
				continue;
			}
			numStarted = true;
			res += resArr[i];

		}
		return res;

	}
}
