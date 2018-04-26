package ru.job4j.comparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        List<Character> leftArray = new ArrayList<>();
        for (Character indexLeft : left.toCharArray()) {
            leftArray.add(indexLeft);
        }

        List<Character> rightArray = new ArrayList<>();
        for (Character indexRight : right.toCharArray()) {
            rightArray.add(indexRight);
        }

        int result = 0;
        List<Character> tempArray = leftArray.size() < rightArray.size() ? leftArray : rightArray;
        for (int i = 0; i < tempArray.size(); i++) {
            if (leftArray.get(i) != rightArray.get(i)) {
                result = leftArray.get(i) - rightArray.get(i);
            } else {
                result = leftArray.size() - rightArray.size();
            }
        }
        return  result;
    }
}
            
