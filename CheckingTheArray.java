package ru.geekbrains;

import java.util.*;

public class CheckingTheArray {

    private static String[] wordsArray = new String[]{
            null, " ", "One", "Two", "Tree", "Word", "Four", "Ten", "Word", "People", "Cat", "Dog", "City", "Earth", "Word", "Sun", "Apple", "Banana", "Dark", "Green", "Apple", "Word", "Yellow", "Family", "Two", "Street", "Four", "Computer", "Four", "Word", "Dark", "Word", "Dog", "Green", "Dog", "City", "Pen"
    };

    private static Set<String> uniqueArray = new HashSet<String>(Arrays.asList(wordsArray));

    public static List<String> sourceList = new ArrayList<String>(uniqueArray);
    public static List<String> expandedList = new ArrayList<String>(Arrays.asList(wordsArray));

    public static void wordCounter(List<String> list, List<String> listToCheck) {
        int counter;
        for (String str1: list) {
            counter = 0;
            if (str1 == null || str1.trim().length() == 0) {
                continue;
            }
            for (String str2: listToCheck) {
                if (str2 == null || str2.trim().length() == 0) {
                    continue;
                }
                if (str1.equals(str2)) {
                    counter ++;
                }
            }
            System.out.println("Строка " + "\"" + str1 + "\"" + " встречается " + counter + " раз");
        }
    }
}

