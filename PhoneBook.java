package ru.geekbrains;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {

    private List<Contact> contactList;

    public PhoneBook() {
        this.contactList = new ArrayList<Contact>(100);
    }

    public void add(String name, String phoneNumber) {
        if (isNameInDirectory(name)) {
            for (Contact contact: this.contactList) {
                if (contact.getName().equalsIgnoreCase(name)) {
                    String oldPhoneNumber = contact.getPhoneNumber();
                    contact.setPhoneNumber(oldPhoneNumber + ", " + phoneNumber);
                }
            }
        } else {
            this.contactList.add(new Contact(name, phoneNumber));
        }
    }

    public void get(String name) {
        if (this.contactList.size() == 0) {
            System.out.println("Телефонный справочник пуст");
        } else if (name != null && name.trim().length() != 0) {
            for (Contact contact: this.contactList) {
                if (contact.getName().equalsIgnoreCase(name)) {
                    System.out.println("Имя: " + contact.getName() + "\n" + "Телефон: " + contact.getPhoneNumber());
                    System.out.println("\n");
                }
            }
        }
    }

    private boolean isNameInDirectory (String name) {
        for (Contact contact: this.contactList) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void printDictionary() {
        for (Contact contact: this.contactList) {
            System.out.println("Имя: " + contact.getName() + "\n" + "Телефон: " + contact.getPhoneNumber());
        }
    }
}
