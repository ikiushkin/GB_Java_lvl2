package ru.geekbrains;

import java.util.*;

public class TelephoneDirectory {

    static Map <String, ArrayList<String>> telephoneDirectory = new HashMap<String, ArrayList<String>>();

    public static void add(String name, String phoneNumber) {
        if (name != null && name.trim().length() != 0) {
            if (checkName(name)) {
                for (HashMap.Entry<String, ArrayList<String>> o : telephoneDirectory.entrySet()) {
                    if (name.equals(o.getKey())) {
                        o.getValue().add(phoneNumber);
                    }
                }
            } else {
                telephoneDirectory.put(name, new ArrayList<String>(Arrays.asList(phoneNumber)));
            }
        } else {
            System.out.println("Вы ввели Фамилию в неверном формате");
        }
    }

    static void get(String name) {
        for (Map.Entry<String, ArrayList<String>> str : telephoneDirectory.entrySet()) {
            if (name.equals(str.getKey())) {
                System.out.println(str.getValue());
            }
        }
    }

    static boolean checkName (String str) {
        for (Map.Entry<String, ArrayList<String>> str1 : telephoneDirectory.entrySet()) {
            if (str.equals(str1.getKey())) {
                return true;
            }
        }
        return false;
    }

    public static final void print() {
        System.out.println(telephoneDirectory);
    }
}
