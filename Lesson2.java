package ru.geekbrains;

public class Lesson2 {
    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {

        foldAllValuesInArray(stringArray);

    }

    static String[][] stringArray = {
        {"1", "5", "45", "124", "124"},
        {"3", "34", "TT", "8"},
        {"45", "94", "992", "3452", "343"},
        {"24", "9", "DD", "43"},
        {"24", "9", "DD", "43"}
    };

    private static void foldAllValuesInArray(String[][] stringArr) throws MyArraySizeException, MyArrayDataException {

        int arrValueSum = 0;

        for (int i = 0; i < stringArr.length; i++) {

            if (i > 3) {
                System.out.println("Сумма значений массива равна: " + arrValueSum);
                throw new MyArraySizeException("Размер массива выходит за пределы допустимых значений по вертикали (4)");
            }

            for (int j = 0; j < stringArr[i].length; j++) {

                if (j > 3) {
                    System.err.println("Выход за пределы допустимого значения длины массива (4) в строке " + (i+1));
                    continue;
                }

                try {
                    Integer.parseInt(stringArr[i][j].trim());
                } catch (NumberFormatException npe) {
                    System.err.println("Ошибка приведения типов String to Integer в ячейке: " + i + "-" + j);
                    npe.printStackTrace();
                    continue;
                }
                arrValueSum += Integer.parseInt(stringArr[i][j].trim());
                }
        }
        System.out.println("Сумма значений массива равна: " + arrValueSum);
    }
}