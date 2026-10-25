package org.example;

import java.util.Scanner;
public class Calculator2 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Insert deposit: ");
        double deposit = input.nextDouble();

        System.out.print("Intrest Rate: ");
        double intrestRate = input.nextDouble();

        System.out.print("Term Time: ");
        int duration = input.nextInt();

        intrestRate = intrestRate / 100;

        double futureValue = deposit * Math.pow(
                (1 + intrestRate / 365), (365 * duration)
        );
        double interest = futureValue - deposit;

        System.out.printf("Future Value: $%.2f%n", futureValue);
        System.out.printf("Interest Earned: $%.2f%n", interest);

        input.close();
    }
}
