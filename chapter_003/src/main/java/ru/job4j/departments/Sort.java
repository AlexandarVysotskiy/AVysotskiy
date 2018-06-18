package ru.job4j.departments;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Class Sort
 *
 * @author Aleksandr Vysotskiy.
 * @version 2.0
 * @since 18.06.18
 */
public class Sort {

    /**
     * This is an auxiliary method, he is adds the missing parent.
     *
     * @param input array of departments.
     * @return array with add missing parents.
     */
    private TreeSet<String> addParents(TreeSet<String> input) {
        TreeSet result = new TreeSet();
        for (String index : input) {
            if (index.contains("\\")) {
                String path = index.substring(0, index.lastIndexOf("\\"));
                if (!input.contains(path)) {
                    result.add(path);
                }
            }
        }
        result.addAll(input);
        return result;
    }

    /**
     * This method sort array of departments by increase.
     *
     * @param input array of departments.
     * @return sorted array in ascending order.
     */
    public TreeSet<String> sortAscending(TreeSet<String> input) {
        return addParents(input);
    }

    /**
     * This method sort array of departments by diminution.
     *
     * @param input array of departments.
     * @return sorted array in diminution order.
     */
    public TreeSet<String> diminution(TreeSet<String> input) {
        TreeSet<String> set = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result;
                if (o1.length() < o2.length() && o2.startsWith(o1)) {
                    result = -1;
                } else {
                    if (o1.length() > o2.length() && o1.startsWith(o2)) {
                        return 1;
                    } else {
                        result = o2.compareTo(o1);
                    }
                }
                return result;
            }
        });
        set.addAll(addParents(input));
        return set;
    }
}