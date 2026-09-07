import java.util.Scanner;

public class ComplaintSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ComplaintRepository repository = new ComplaintRepository();
        ComplaintService service = new ComplaintService(repository, sc);

        int choice;

        do {
            System.out.println("\n===== Complaint Management System =====");
            System.out.println("1. Register Complaint");
            System.out.println("2. View All Complaints");
            System.out.println("3. Search Complaint");
            System.out.println("4. Update Complaint Status");
            System.out.println("5. Exit");
            System.out.print("Enter Your Choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Please enter a valid choice: ");
                sc.next();
            }

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    service.registerComplaint();
                    break;
                case 2:
                    repository.displayAllComplaints();
                    break;
                case 3:
                    service.searchComplaint();
                    break;
                case 4:
                    service.updateComplaintStatus();
                    break;
                case 5:
                    System.out.println("Thank you, Have a great day!");
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}
