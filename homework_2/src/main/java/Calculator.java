import java.util.Scanner;

public class Calculator {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int num1 = getNumber();
        int num2 = getNumber();
        char operation = getOperator();
        int result = Calculate(num1,num2,operation);
        System.out.println("The result is: "+result);
    }

    public static int getNumber(){
        System.out.println("Input a number:");
        int num;
        if(scanner.hasNextInt()){
            num = scanner.nextInt();
        } else {
            System.out.println("Wrong input. Please try one more time");
            scanner.next();
            num = getNumber();
        }
        return num;
    }

    public static char getOperator(){
        System.out.println("Input operator:");
        char operator;
        if(scanner.hasNext()){
            operator = scanner.next().charAt(0);
        } else {
            System.out.println("Wrong input. Please try one more time");
            scanner.next();
            operator = getOperator();
        }
        return operator;
    }

    public static int Calculate(int num1, int num2, char operator){
        int result;
        switch (operator){
            case '+':
                result = num1+num2;
                break;
            case '-':
                result = num1-num2;
                break;
            case '*':
                result = num1*num2;
                break;
            case '/':
                if(num2 == 0){
                    System.out.println("Error! Dividing by zero!");
                    return result = 0;
                }
                result = num1/num2;
                break;
            default:
                System.out.println("Something went wrong. Please repeat the input");
                result = Calculate(num1, num2, getOperator());
        }
        return result;
    }
}