package Main;

import java.util.ArrayList;

public class CompareTwoArrays {

	public static void main(String[] args) {
	/*	ArrayList<String> arrayList1 = new ArrayList<>();
		ArrayList<String> arrayList2 = new ArrayList<>();

		arrayList1.add("apple");
		arrayList2.add("pear");
		arrayList1.add("orange");
		arrayList2.add("strawberry");
		arrayList1.add("plum");
		arrayList2.add("blackberry");
		arrayList1.add("pineapple");
		arrayList2.add("plum");

		System.out.print(compareTwoArrays(arrayList1, arrayList2));*/
	}

	public static boolean compareTwoArrays(ArrayList<String> listA, ArrayList<String> listB) {
		boolean found = false;

		for (int i = 0; i < listA.size(); i++) {
			for (int j = 0; j < listB.size(); j++) {
				if (listA.get(i).equalsIgnoreCase(listB.get(j))) {
					found = true;
				}

			}

		}

		return found;
	}
}
