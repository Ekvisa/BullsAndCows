package com.company;

import java.io.IOException;
import java.lang.reflect.Array;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        int n;

        while (true) {
            System.out.println("Длина последовательности для отгадывания: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(reader.readLine());

            if (n > 10) {System.out.println("Слишком большая длина");}
            else break;
        }

        int[] goalArray = generateGoalArray(n);

        while (true) {

            int[] supposedArray = getSupposedArray(goalArray.length);

            System.out.println("Быков: " + countBulls(goalArray, supposedArray));
            System.out.println("Коров: " + countCows(goalArray, supposedArray));

            if (countBulls(goalArray, supposedArray) == n) {
                System.out.println("Да! Загаданная последовательность:");
                for (int i = 0; i < supposedArray.length; i++) {
                    System.out.print(supposedArray[i]);
                }
                break;
            }

        }

    }


    public static boolean isNumberInArray(int[] array, int number) { //входит ли число в массив?

        boolean answer = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == number) {
                answer = true;
                break;
            }
        }
        return answer;

    }


    public static int[] subarray (int[] array, int n) { //подмассив из первых n чисел

        if (array.length <= n) { //мало ли  ╮(˘_˘)╭
            return array;
        }
        else {
            int[] mySubarray = new int[n];
            for (int i = 0; i < n; i++) {
                mySubarray[i] = array[i];
            }
            return mySubarray;
        }

    }


    public static int[] getSupposedArray (int arrayLength) throws IOException {

        String supposedString;
        String [] supposedStringSplitted;
        int[] SupposedArray = new int[arrayLength];

        while (true) { //принимаем строку-предположение нужной длины
            System.out.println("Угадайте последовательность: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            supposedString = reader.readLine();
            if (supposedString.length() != arrayLength) {
                System.out.println("Неверная длина");

            }
            else {break;}
        }

        supposedStringSplitted = supposedString.split(""); //принятую строку-предположение -- в массив!
        for (int i = 0; i < arrayLength; i++) {
            SupposedArray[i] = Integer.parseInt(supposedStringSplitted[i]);
        }

        return SupposedArray;

    }


    public static int countBulls(int[] goalArray, int[] supposedArray) { //считаем и выводим число быков

        int bulls = 0;

        for (int i = 0; i < goalArray.length; i++) {
            if (goalArray[i] == supposedArray[i]) {bulls++;}
        }

        return bulls;

    }


    public static int countCows(int[] goalArray, int[] supposedArray) { //считаем и выводим число коров

        int cows = 0;

        for (int i = 0; i < goalArray.length; i++) {
            for (int j = 0; j < goalArray.length; j++) {
                if ((goalArray[i] == supposedArray[j]) && (i != j)) {cows++;}
            }
        }

        return cows;

    }


    public static int[] generateGoalArray (int n) {

        int[] goalArray = new int[n];

        for (int i = 0; i < goalArray.length; i++) { //для (i + 1)-го числа
            while (true) {
                int tmp = (int)(Math.random() * 10); //сгенерим число-кандидат
                int[] subarrayTmp = subarray(goalArray, i); //возьмём первые i чисел
                if (!(isNumberInArray(subarrayTmp, tmp))) { //сравним их с кандидатом
                    goalArray[i] = tmp;
                    break;
                }
            }
        }

        return goalArray;

    }

}
