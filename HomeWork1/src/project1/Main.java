package project1;

import java.util.Scanner;
/** Used for testing*/
public class Main {
    //*  This is the project1.Main method where we will run all the other methods and classes
    // @author Thomas Thomassen, Mostafa Shalan
    // @param
    // @return*/

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Date d1 = new Date(2,29,2024);
        System.out.println(d1.isValid()); // true

        Date d2 = new Date(2,29,2023);
        System.out.println(d2.isValid()); // false


    }
}