package car;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CarServiceDAO dao = new CarServiceDAO();
        Scanner sc        = new Scanner(System.in);
        int choice;

        System.out.println("=== Car Service Record Management System ===");

        do {
            System.out.println("\n1. Add Record");
            System.out.println("2. View All Records");
            System.out.println("3. Search by ID");
            System.out.println("4. Update Record");
            System.out.println("5. Delete Record");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("Owner Name        : "); String name  = sc.nextLine();
                    System.out.print("Car Model         : "); String model = sc.nextLine();
                    System.out.print("License No        : "); String lic   = sc.nextLine();
                    System.out.print("Date (YYYY-MM-DD) : "); String date  = sc.nextLine();
                    System.out.print("Service Type      : "); String type  = sc.nextLine();
                    System.out.print("Cost              : "); double cost  = sc.nextDouble(); sc.nextLine();
                    System.out.print("Mechanic          : "); String mech  = sc.nextLine();
                    dao.create(new CarServiceRecord(name, model, lic, date, type, cost, mech));
                    break;

                case 2:
                    dao.readAll();
                    break;

                case 3:
                    System.out.print("Enter ID: ");
                    dao.readById(sc.nextInt());
                    break;

                case 4:
                    System.out.print("ID to update      : "); int uid   = sc.nextInt(); sc.nextLine();
                    System.out.print("New Owner Name    : "); String un = sc.nextLine();
                    System.out.print("New Car Model     : "); String um = sc.nextLine();
                    System.out.print("New License No    : "); String ul = sc.nextLine();
                    System.out.print("New Date          : "); String ud = sc.nextLine();
                    System.out.print("New Service Type  : "); String ut = sc.nextLine();
                    System.out.print("New Cost          : "); double uc = sc.nextDouble(); sc.nextLine();
                    System.out.print("New Mechanic      : "); String ume= sc.nextLine();
                    dao.update(uid, new CarServiceRecord(un, um, ul, ud, ut, uc, ume));
                    break;

                case 5:
                    System.out.print("ID to delete: ");
                    dao.delete(sc.nextInt());
                    break;

                case 0:
                    System.out.println("Bye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        sc.close();
    }
}