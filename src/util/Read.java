package util;

import java.util.Scanner;

public class Read {

    private static final Scanner sc = new Scanner(System.in);

    public static int inputInteger(String prompt) {
        System.out.print(prompt);
        int option;
        while (true) {
            try {
                option = Integer.parseInt(sc.nextLine());
                return option;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Tente novamente!");
                return -1;
            }
        }
    }    

    public static String inputString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
}
