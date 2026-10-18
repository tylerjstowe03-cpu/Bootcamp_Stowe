package org.example;

import java.util.Scanner;

public class Calculator3 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Monthly Pay: ");
        double monthlyPay = input.nextDouble();

        System.out.print("Interest Rate (%): ");
        double rate = input.nextDouble();

        System.out.print("Enter number of years: ");
        int years = input.nextInt();

        double monthlyRate = rate / 100 / 12;

        int months = years * 12;

        double presentValue;

        if (monthlyRate == 0) {
            presentValue = monthlyPay * months;
        } else {
            presentValue = monthlyPay *
                    (1 - Math.pow(1 + monthlyRate, -months))
                    / monthlyRate;
        }

        System.out.printf("Present Value: $%,.2f%n", presentValue);

        input.close();
    }
}
