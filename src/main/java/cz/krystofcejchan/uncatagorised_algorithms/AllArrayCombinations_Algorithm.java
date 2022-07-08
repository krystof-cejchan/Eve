package cz.krystofcejchan.uncatagorised_algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author krystof-cejchan
 * @version 23-02-22
 */
public class AllArrayCombinations_Algorithm {
    public static ArrayList<String> getCombinations(ArrayList<String> words) {

        try {
            List<List<String>> powerSet = new LinkedList<>();

            for (int i = 1; i <= words.size(); i++)
                powerSet.addAll(combination(words, i));

            ArrayList<String> lists = new ArrayList<>();

            StringBuilder vysl = new StringBuilder();
            for (List<String> l1 : powerSet) {
                for (String s : l1) {
                    vysl.append(s).append(" ");

                }
                lists.add(vysl.toString());
                vysl = new StringBuilder();

            }

            // lists.forEach((it) -> System.out.println(it));
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<List<T>> combination(List<T> values, int size) {

        if (0 == size) {
            return Collections.singletonList(Collections.emptyList());
        }

        if (values.isEmpty()) {
            return Collections.emptyList();
        }

        List<List<T>> combination = new LinkedList<>();

        T actual = values.iterator().next();

        List<T> subSet = new LinkedList<>(values);
        subSet.remove(actual);

        List<List<T>> subSetCombination = combination(subSet, size - 1);

        for (List<T> set : subSetCombination) {
            List<T> newSet = new LinkedList<>(set);
            newSet.add(0, actual);
            combination.add(newSet);
        }
        combination.addAll(combination(subSet, size));

        return combination;
    }
}
