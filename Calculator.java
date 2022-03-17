package CalculatorTest;

import java.util.Arrays;
import java.util.Scanner;

public class Calculator {


    public static void main(String[] args) throws Exception {
        String[] roman_letters = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        Scanner input_a_value = new Scanner(System.in);
        System.out.print("Введите выражение используя диапазон чисел 1 - 10: ");
        System.out.println("Для выхода напишите end");
        String str = input_a_value.nextLine();
        String delimetr = " ";

        while (!str.equals("end")) {
            String[] pars = str.split(delimetr);
            String operation = pars[1];

            if (pars.length != 3) {
                System.out.println("Неверный формат ввода данных. Введите выражение, разделяя каждый символ _пробелом_");
                System.out.print("Введите выражение используя диапазон чисел 1 - 10: ");
                str = input_a_value.nextLine();
                pars = str.split(delimetr);
            }

            boolean its_an_arabic_numbers = true;

            int value1 = 0;
            int value2 = 0;
            Operation values;
            // Переводим в int. Если введены римские, выкинет исключение
            try {
                value1 = Integer.parseInt(pars[0]);
                value2 = Integer.parseInt(pars[2]);
                //values = new Arabic(value1, value2, 0);
            } catch (NumberFormatException e) {
                its_an_arabic_numbers = false;
                System.out.println("Введены римские цифры");
                //values = new CalulatorRome(parsed_input[0], parsed_input[2], 0);
            }

            if (its_an_arabic_numbers) {
                if (value1 < 1 || value1 > 10 || value2 < 1 || value2 > 10) {
                    throw new Exception("Выход за рамки диапазона");
                }
            } else {
                if (!Arrays.asList(roman_letters).contains(pars[0]) || !Arrays.asList(roman_letters).contains(pars[2])) {
                    throw new Exception("Выход за рамки диапазона");
                }
            }


            if (its_an_arabic_numbers) {
                values = new CalculatorArabic(value1, value2);

            } else {
                values = new CalculatorRome(pars[0], pars[2]);
            }

            if (operation.equals("+")) {
                values.sum();
            } else if (operation.equals("-")) {
                values.diff();
            } else if (operation.equals("/")) {
                values.div();
            } else if (operation.equals("*")) {
                values.mul();
            }

            if (its_an_arabic_numbers) {
                System.out.println("Ответ: " + values.getResult());
            } else {
                // конвертировать L and C from XXXXX and XXXXXXXXXX
                System.out.println("Ответ: " + values.getStringResult());
            }
            System.out.println();
            System.out.print("Введите следующее выражение: ");
            str = input_a_value.nextLine();
        }

        System.out.println("Вы вышли из калькулятора");

    }
}


