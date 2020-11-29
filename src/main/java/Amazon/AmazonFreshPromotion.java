package Amazon;

public class AmazonFreshPromotion {
	public static void main(String[] args){
		String[][] codeList1 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
		String[] shoppingCart1 = {"orange", "apple", "apple", "banana", "orange", "banana"};
		String[][] codeList2 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
		String[] shoppingCart2 = {"banana", "orange", "banana", "apple", "apple"};
		String[][] codeList3 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
		String[] shoppingCart3 = {"apple", "banana", "apple", "banana", "orange", "banana"};
		String[][] codeList4 = { { "apple", "apple" }, { "apple", "apple", "banana" } };
		String[] shoppingCart4 = {"apple", "apple", "apple", "banana"};
		String[][] codeList5 = { { "apple", "apple" }, { "banana", "anything", "banana" } };
		String[] shoppingCart5 = {"orange", "apple", "apple", "banana", "orange", "banana"};
		String[][] codeList6 = { { "apple", "apple" }, { "banana", "anything", "banana" }  };
		String[] shoppingCart6 = {"apple", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};
		String[][] codeList7= { { "anything", "apple" }, { "banana", "anything", "banana" }  };
		String[] shoppingCart7 = {"orange", "grapes", "apple", "orange", "orange", "banana", "apple", "banana", "banana"};
		String[][] codeList8 = {{"apple", "orange"}, {"orange", "banana", "orange"}};
		String[] shoppingCart8 = {"apple", "orange", "banana", "orange", "orange", "banana", "orange", "grape"};
		String[][] codeList9= { { "anything", "anything", "anything", "apple" }, { "banana", "anything", "banana" }  };
		String[] shoppingCart9 = {"orange", "apple", "banana", "orange", "apple", "orange", "orange", "banana", "apple", "banana"};

		test(codeList1, shoppingCart1);
		test(codeList2, shoppingCart2);
		test(codeList3, shoppingCart3);
		test(codeList4, shoppingCart4);
		test(codeList5, shoppingCart5);
		test(codeList6, shoppingCart6);
		test(codeList7, shoppingCart7);
		test(codeList8, shoppingCart8);
		test(codeList9, shoppingCart9);
	}

	public static void test(String[][] codeList, String[] shoppingCart){
		int cartIndex = 0;
		int codeIndex = 0;
		int listIndex = 0;


		while(cartIndex < shoppingCart.length && listIndex < codeList.length){
				int index = cartIndex;
				int start = 0;
				while(start < codeList[listIndex].length && index < shoppingCart.length &&
						(codeList[listIndex][start].equalsIgnoreCase("anything") ||
								codeList[listIndex][start].equalsIgnoreCase(shoppingCart[index]))){
					index++;
					start++;
				}
				if(start == codeList[listIndex].length){
					listIndex++;
					if(listIndex == codeList.length){
						System.out.println(1);
						return;
					}
					cartIndex = index;
				}
				else
					cartIndex++;




		}
		System.out.println(0);

	}
}
