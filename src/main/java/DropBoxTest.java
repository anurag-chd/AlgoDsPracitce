import java.util.*;

public class DropBoxTest {

	public static void main(String args[]){
		/*String[][] input = {

				{"0", "APPEND", "Hey"},

				{"1", "APPEND", " there"},

				{"2", "APPEND", "!"},

				{"3", "UNDO"},

				{"4", "UNDO"},
				{"5", "UNDO"},
				{"6", "UNDO"}

		};*/

		/*String[][] input =

			{{"0","APPEND","Hey there!"},

				{"1","UNDO"},

				{"2","UNDO"},

				{"3","APPEND","Hey there!"},

				{"4","BACKSPACE"},

				{"5","BACKSPACE"},

				{"6","UNDO"},

				{"7","UNDO"}};*/

		/*String[][] input = {{"0","APPEND","Hey"},

				{"1","APPEND"," there!"},

				{"2","UNDO"},

				{"3","REDO"}};*/

		/*String[][] input = {{"1","APPEND","ey"},

				{"0","APPEND"," H"}

				};*/
		/*String[][] input = {{"0","APPEND","Hello"},

				{"1","SELECT","1","3"},

				{"2","BACKSPACE"}

				};
*/
		/*String[][] input = {{"0","APPEND","Hello"},

				{"1","SELECT","2","5"},

				{"2","APPEND", "y there"}

		};*/
		String[][] input = {{"0","APPEND","Hello"},

				{"1","SELECT","1","3"},

				{"2","BOLD"}

		};
		System.out.println(textEditor(input));
	}
	/*
	input[
	["0","APPEND","HEY"],
	["1", "APPEND"," there"]
	]
	*/

	public static String textEditor(String[][] input){
		//PriorityQueue<String[]> inputQueue = new PriorityQueue<>((a,b) -> Long.compare(Long.parseLong(a[0]),Long.parseLong(b[0]) ));
		Arrays.sort(input,(a, b) -> Long.compare(Long.parseLong(a[0]),Long.parseLong(b[0]) ));

		Stack<String> inputStack = new Stack<>();
		int index = 0;
		while(index < input.length){
			String []cmds =input[index];
			switch (cmds[1].toLowerCase()) {
				case "append":
					if(inputStack.isEmpty())
						inputStack.push(cmds[2]);
					else
						inputStack.push(inputStack.peek() + cmds[2]);
					index++;
					break;
				case "backspace":
					String temp = inputStack.peek();
					if(temp.length() > 0)
						inputStack.push(temp.substring(0,temp.length()-1));
					index++;
					break;
				case "undo":
					int start = index;
					Stack<String>  undoS= (Stack<String>) inputStack.clone();
					while(start < input.length && input[start][1].equalsIgnoreCase("undo")){
						if(!undoS.isEmpty()){
							undoS.pop();
							if(!undoS.isEmpty())
								inputStack.push(undoS.peek());
							else{
								inputStack.push("");
							}
						}

						start++;


					}
					if(index != start){
						index = start;
					}else{
						index++;
					}

					break;
				case "redo":
					int undoIndex=index-1;
					int redoIndex = index;
					Stack<String>  redoStack= (Stack<String>) inputStack.clone();

					while (undoIndex > 0 && input[undoIndex][1].equalsIgnoreCase("undo") && input[redoIndex][1].equalsIgnoreCase("redo") ){
						if(!redoStack.isEmpty()){
							redoStack.pop();
							if(!redoStack.isEmpty())
								inputStack.push(redoStack.peek());
							else{
								inputStack.push("");
							}
						}

						undoIndex--;
						redoIndex++;
					}
					if(index != redoIndex){
						index = redoIndex;
					}else{
						index++;
					}
					break;
				case "select":
					int selectIndex = index;
					while(selectIndex < input.length-1 && input[selectIndex +1][1].equalsIgnoreCase("select"))
						selectIndex++;
					cmds = input[selectIndex];
					if(inputStack.peek().length() > 0 && selectIndex < input.length-1){
						int s = Integer.parseInt(cmds[2]);
						int e = Integer.parseInt(cmds[3]);
						if(e > inputStack.peek().length()){
							e = inputStack.peek().length();
						}
						if(s < 0 || s > inputStack.peek().length())
							break;
						cmds = input[++selectIndex];
						switch (cmds[1].toLowerCase()){
							case "backspace": {


								StringBuilder sbr = new StringBuilder(inputStack.peek());
								inputStack.push(sbr.substring(0, s ) + sbr.substring(e));
								break;
							}
							case "append": {
								StringBuilder sbr = new StringBuilder(inputStack.peek());
								inputStack.push(sbr.substring(0,s)+ cmds[2] + sbr.substring(e));
								break;
							}
							case "bold": {
								StringBuilder sbr = new StringBuilder(inputStack.peek());
								inputStack.push(sbr.substring(0,s) + "*" +sbr.substring(s,e) + "*" + sbr.substring(e));
								break;
							}



						}
					}
					index = selectIndex +1;
					break;




					//if(i >0 && prev)
			}
		}

		return inputStack.pop();

	}
}
