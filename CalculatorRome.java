package CalculatorTest;

public class CalculatorRome extends Operation {
    private String romesValue1;
    private String romesValue2;
    private int romesValue1Int;
    private int romesValue2Int;
    private int resultInt;
    private String sign = "";
    private String resultString;
    private String[] romanLetters10 = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};


    CalculatorRome(String value1, String value2) {
        this.romesValue1 = value1;
        this.romesValue2 = value2;
        this.romesValue1Int = convertToInt(romesValue1);
        this.romesValue2Int = convertToInt(romesValue2);
    }

    private String convertResultToRomes(int n, int ostatok) {
        ostatok = n % 10;
        if (ostatok != 0) {
            try {
                return convertResultToRomes(n - ostatok, 0) + romanLetters10[ostatok - 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                System.out.println("Result can not be < I");
                sign = "-";
            }

        }
//        convert to rome
        while (n != 0) {
            if (n > 0 && n < 50) {
                n = n - 10;
                return convertResultToRomes(n, 0) + "X";
            } else if (n >= 40 && n < 90) {
                n = n - 40;
                return convertResultToRomes(n, 0) + "L";
            } else if (n == 90) {
                n = n - 90;
                return convertResultToRomes(n, 0) + "XC";
            } else if (n == 100) {
                n = n - 100;
                return convertResultToRomes(n, 0) + "C";
            } else {
                return sign;
            }
        }

        return sign;
    }


    @Override
    public void sum() {
        resultInt = romesValue1Int + romesValue2Int;
        resultString = convertResultToRomes(resultInt, resultInt);
    }

    @Override
    public void diff() {
        resultInt = romesValue1Int - romesValue2Int;
        resultString = convertResultToRomes(resultInt, resultInt);
    }

    @Override
    public void div() {
        resultInt = romesValue1Int / romesValue2Int;
        resultString = convertResultToRomes(resultInt, resultInt);
    }

    @Override
    public void mul() {
        resultInt = romesValue1Int * romesValue2Int;
        resultString = convertResultToRomes(resultInt, resultInt);
    }

    @Override
    public int getResult() {
        return resultInt;
    }

    public String getStringResult() {
        return resultString;
    }

    private int convertToInt(String romesValue) {
        char[] valueChar = romesValue.toCharArray();
        int[] valuesInt = new int[valueChar.length];
        for (int i = 0; i < valueChar.length; i++) {
            switch (valueChar[i]) {
                case 'I':
                    valuesInt[i] = 1;
                    break;
                case 'V':
                    valuesInt[i] = 5;
                    break;
                case 'X':
                    valuesInt[i] = 10;
                    break;
            }
        }
        int result = valuesInt[0];
        for (int i = 0; i < valuesInt.length && valuesInt.length > i + 1; i++) {
            if (valuesInt[i] >= valuesInt[i + 1]) {
                result += valuesInt[i + 1];
            } else if (valuesInt[i] < valuesInt[i + 1]) {
                result = result + valuesInt[i + 1] - 2;
            }
        }
        return result;
    }

    public String getCalculatorRomeValue1() {
        return romesValue1;
    }

    public String getCalculatorRomeValue2() {
        return romesValue2;
    }

    public void setCalculatorRomeValue1(String romesValue1) {
        this.romesValue1 = romesValue1;
    }

    public void setCalculatorRomeValue2(String romesValue2) {
        this.romesValue2 = romesValue2;
    }

    public int getCalculatorRomeValue1Int() {
        return romesValue1Int;
    }

    public int getCalculatorRomeValue2Int() {
        return romesValue2Int;
    }

    public void setCalculatorRomeValue1Int(int romesValue1Int) {
        this.romesValue1Int = romesValue1Int;
    }

    public void setCalculatorRomeValue2Int(int romesValue2Int) {
        this.romesValue2Int = romesValue2Int;
    }
}
