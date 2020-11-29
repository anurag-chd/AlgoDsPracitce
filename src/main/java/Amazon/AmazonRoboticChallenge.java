package Amazon;

import java.util.Arrays;
import java.util.Stack;

public class AmazonRoboticChallenge {
	public static void main(String[] args){
		/*int num = 8;
		String[] blocks = {"5", "-2", "4", "Z", "X", "9", "+", "+"};*/

		int num = 4;
		String[] blocks = {"1", "2", "+", "Z"};


		System.out.println(calculateScore(num, blocks));

	}

	public static int calculateScore(int num, String[] blocks){
		int[] res = new int[num];
		Arrays.fill(res, 0);
		Stack<Integer> stack = new Stack<>();
		int sum = 0;
		for(int index= 0; index < blocks.length;index++){
			String block = blocks[index];
			try{
				int val = Integer.parseInt(block);
				sum += val;
				stack.push(val);
			}
			catch(NumberFormatException n){
				switch (block){
					case "X":
						if(!stack.isEmpty()){
							sum += (stack.peek() *2);
							stack.push(stack.peek() * 2);

						}
						break;
					case "+":
						int val = 0;

						if(stack.size() >= 2){
							int prevVal = stack.pop();
							int prev2Val = stack.pop();
							val = prevVal + prev2Val;
							stack.push(prev2Val);
							stack.push(prevVal);

						}
						if(stack.size() == 1){
							val = stack.peek();
						}
						stack.push(val);
						sum += val;


						break;
					case "Z":
						if(!stack.isEmpty()){
							int value = stack.pop();
							sum -= value;
						}
						break;
				}
			}
		}
		//System.out.println(Arrays.toString(res));

		return sum;
	}
}
