package ru.geekbrains;

public class Lesson3 {

    public static void main(String[] args) {

        // Задание 1
        System.out.println();
        CheckingTheArray.wordCounter(CheckingTheArray.sourceList, CheckingTheArray.expandedList);

        // Задание 2
        System.out.println();
        TelephoneDirectory.add("Пупкин", "89091012233");
        TelephoneDirectory.add("Семенов", "89601492735");
        TelephoneDirectory.add("Иванов", "89601492735");
        TelephoneDirectory.add("Иванов", "89152312701");
        TelephoneDirectory.add("Сидоров", "89131232443");
        TelephoneDirectory.add("", "89134230003");
        TelephoneDirectory.add(null, "89134230003");
        TelephoneDirectory.get("Иванов");
        TelephoneDirectory.print();
    }
}
