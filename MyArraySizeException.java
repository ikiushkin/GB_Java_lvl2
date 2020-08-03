package ru.geekbrains;

public class MyArraySizeException extends IllegalArgumentException {

    public MyArraySizeException() {
    }

    public MyArraySizeException(String s) {
        super(s);
    }

    public MyArraySizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyArraySizeException(Throwable cause) {
        super(cause);
    }
}
