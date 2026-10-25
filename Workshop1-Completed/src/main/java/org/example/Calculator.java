package org.example;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter loan amount: ");
        double principal = scanner.nextDouble();

        System.out.print("Enter interest rate: ");
        double rate = scanner.nextDouble();

        System.out.print("Enter years: ");
        int years = scanner.nextInt();

        double monthlyRate = rate / 100 / 12;
        int months = years * 12;

        double power = 1;
        int count = 0;

        while (count < months) {
            power = power * (1 + monthlyRate);
            count++;
        }
        double monthlyPayment;

        if (monthlyRate == 0) {
            monthlyPayment = principal / months;
        } else {
            monthlyPayment = principal *
                    (monthlyRate * power) / (power - 1);
        }

        double totalInterest = (monthlyPayment * months) - principal;

        System.out.printf("Monthly Payment: $%.2f%n", monthlyPayment);
        System.out.printf("Total Interest: $%.2f%n", totalInterest);

        scanner.close();
    }
}
