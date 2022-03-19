package CalculatorTest;

import java.util.Arrays;
import java.util.Scanner;

public class Calculator {


    public static void main(String[] args) throws Exception {
        String[] romanLetters = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};

        Scanner inputValue = new Scanner(System.in);
        System.out.print("Enter the expression 'separated by a space' using the range of numbers 1 - 10: ");
        System.out.println("For finish enter 'end'");
        String str = inputValue.nextLine();
        String delimetr = " ";

        while (!str.equals("end")) {
            String[] pars = str.split(delimetr);

            if (pars.length != 3) {
                System.out.println("Invalid data entry format. Enter an expression, separating each character with _space_");
                System.out.print("Enter an expression using the range of numbers 1 - 10:");
                str = inputValue.nextLine();
                pars = str.split(delimetr);
            }
            String operation = pars[1];

            boolean itsAnArabicNumbers = true;

            int value1 = 0;
            int value2 = 0;
            Operation values;

            // in int

            try {
                value1 = Integer.parseInt(pars[0]);
                value2 = Integer.parseInt(pars[2]);
            } catch (NumberFormatException e) {
                itsAnArabicNumbers = false;
                //System.out.println("rome number");

            }

            if (itsAnArabicNumbers) {
                if (value1 < 1 || value1 > 10 || value2 < 1 || value2 > 10) {
                    throw new Exception("out of range");
                }
            } else {
                if (!Arrays.asList(romanLetters).contains(pars[0]) || !Arrays.asList(romanLetters).contains(pars[2])) {
                    throw new Exception("out of range");
                }
            }


            if (itsAnArabicNumbers) {
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
            } else {
                throw new Exception("Operation not supported");
            }

            // Here it was possible to make it more beautiful in methods. But I think it will do for an example.
            if (itsAnArabicNumbers) {
                System.out.println("Answer: " + values.getResult());
            } else if (!values.getStringResult().contains("-")) {
                if (values.getResult() >= 40 && values.getResult() < 50) {
                    StringBuffer strOnline = new StringBuffer(values.getStringResult());
                    strOnline.delete(0, 4);
                    System.out.println("XL" + strOnline);
                } else if (values.getStringResult().contains("L")) {
                    String valueFirst = values.getStringResult().replaceFirst("L", "");
                    System.out.println("L" + valueFirst);
                } else {
                    System.out.println("Answer: " + values.getStringResult());
                }
            }
            System.out.println();
            System.out.print("next expression: ");
            str = inputValue.nextLine();
        }

        System.out.println("You are finished");

    }
}


