package uncatagorisedAlgorithms;

import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.ArrayList;

/**
 * @author TheKrystof701
 * @version 23-02-22
 */
public class AllArrayCombinations_Algorithm {
	public static ArrayList<String> getCombinations(ArrayList<String> words) {

		try {
			List<List<String>> powerSet = new LinkedList<List<String>>();

			for (int i = 1; i <= words.size(); i++)
				powerSet.addAll(combination(words, i));

			ArrayList<String> lists = new ArrayList<>();

			String vysl = "";
			for (List<String> l1 : powerSet) {
				for (String s : l1) {
					vysl += s + " ";

				}
				lists.add(vysl);
				vysl = "";

			}

			// lists.forEach((it) -> System.out.println(it));
			return lists;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static <T> List<List<T>> combination(List<T> values, int size) {

		if (0 == size) {
			return Collections.singletonList(Collections.<T>emptyList());
		}

		if (values.isEmpty()) {
			return Collections.emptyList();
		}

		List<List<T>> combination = new LinkedList<List<T>>();

		T actual = values.iterator().next();

		List<T> subSet = new LinkedList<T>(values);
		subSet.remove(actual);

		List<List<T>> subSetCombination = combination(subSet, size - 1);

		for (List<T> set : subSetCombination) {
			List<T> newSet = new LinkedList<T>(set);
			newSet.add(0, actual);
			combination.add(newSet);
		}
		combination.addAll(combination(subSet, size));

		return combination;
	}
}
