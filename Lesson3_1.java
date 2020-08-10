package ru.geekbrains;

public class Lesson3_1 {

    public static void main(String[] args) {

        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("Ivan", "89093456792");
        phoneBook.add("Ivan", "89093456792");
        phoneBook.add("Ivan", "89093456792");
        phoneBook.add("Sergey", "87773488791");
        phoneBook.add("Dmitriy", "89023457697");
        phoneBook.get("Ivan");
        phoneBook.printDictionary();
    }
}
