package com.car;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        CarSystem system = new CarSystem();

        int choice;

        do {

            System.out.println("\n=================================");
            System.out.println("   CAR BUYING & SELLING SYSTEM");
            System.out.println("=================================");

            System.out.println("1. Register Buyer");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // =========================
                // REGISTER
                // =========================

                case 1:

                    system.register();
                    break;

                // =========================
                // LOGIN
                // =========================

                case 2:

                    boolean success = system.login();

                    if (success) {

                        // ROLE CHECK

                        String role =
                                system.getCurrentUserRole();

                        if (role.equalsIgnoreCase("ADMIN")) {

                            system.adminDashboard();

                        } else if (role.equalsIgnoreCase("BUYER")) {

                            system.buyerDashboard();
                        }
                    }

                    break;

                // =========================
                // EXIT
                // =========================

                case 3:

                    System.out.println("\n👋 Thank You For Using The System");
                    break;

                default:

                    System.out.println("❌ Invalid Choice");
            }

        } while (choice != 3);

        sc.close();
    }

}
