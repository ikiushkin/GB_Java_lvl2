package ru.geekbrains;

public class Lesson2 {
    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {

        foldAllValuesInArray(stringArray);

    }

    static String[][] stringArray = {
        {"1", "5", "45", "124"},
        {"3", "34", "TT", "8"},
        {"45", "94", "992", "3452"},
        {"24", "9", "DD", "43"}
    };

    private static void foldAllValuesInArray(String[][] stringArr) throws MyArraySizeException {

        int arrValueSum = 0;

        for (int i = 0; i < stringArr.length; i++) {

            for (int j = 0; j < stringArr[i].length; j++) {
                if (i > 3 || j > 3) {
                    throw new MyArraySizeException("Размер массив превышает допустимы значения 4х4!");
                }

                try {
                    Integer.parseInt(stringArr[i][j].trim());
                } catch (NumberFormatException npe) {
                    System.out.println("Ошибка приведения типов String to Integer в ячейке: " + i + "-" + j);
                    npe.printStackTrace();
                    continue;
                }

                arrValueSum += Integer.parseInt(stringArr[i][j].trim());
            }
        }
        System.out.println(arrValueSum);
    }
}
