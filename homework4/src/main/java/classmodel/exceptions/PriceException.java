package classmodel.exceptions;

public class PriceException extends BaseException {
    private int number1;
    private int number2;

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }

    public PriceException(String message, int num1, int num2) {
        super(message);
        number1 = num1;
        number2 = num2;
    }
}
