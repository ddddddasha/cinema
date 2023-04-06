package util;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static util.constants.Constants.*;

public class IncorrectInput {
    private final Scanner scanner = new Scanner(System.in);

    public boolean numberValid(String number) {
        String regex = "^[0-9]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        if (matcher.find()) {
            return true;
        } else {
            System.out.println(INCORRECT_INPUT);
            return false;
        }

    }

    public Long integerInput(){
        while (!scanner.hasNextInt()) {
            System.out.println(NOT_AN_INTEGER);
            scanner.next();
        }
        return scanner.nextLong();
    }

    public Double doubleInput(){
        while (!scanner.hasNextDouble()) {
            System.out.println(NOT_A_NUMBER);
            scanner.next();
        }
        return scanner.nextDouble();
    }
}
