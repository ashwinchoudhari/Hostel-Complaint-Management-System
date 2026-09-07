import java.util.Scanner;

public class ComplaintService {
    private final ComplaintRepository repository;
    private final Scanner sc;

    public ComplaintService(ComplaintRepository repository, Scanner sc) {
        this.repository = repository;
        this.sc = sc;
    }

    public void registerComplaint() {
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Complaint Type: ");
        String type = sc.nextLine();

        System.out.print("Enter Description: ");
        String description = sc.nextLine();

        Complaint complaint = new Complaint(name, type, description);

        if (repository.addComplaint(complaint)) {
            System.out.println("Complaint Registered Successfully.");
            System.out.println("Your Complaint ID is: " + complaint.getComplaintId());
        } else {
            System.out.println("Complaint storage is full.");
        }
    }

    public void searchComplaint() {
        System.out.print("Enter Complaint ID to Search: ");
        int id = readInt();

        Complaint complaint = repository.findById(id);

        if (complaint != null) {
            System.out.println("\n--- Complaint Found ---");
            complaint.displayComplaint();
        } else {
            System.out.println("Complaint Not Found.");
        }
    }

    public void updateComplaintStatus() {
        System.out.print("Enter Complaint ID: ");
        int id = readInt();

        Complaint complaint = repository.findById(id);

        if (complaint == null) {
            System.out.println("Complaint Not Found.");
            return;
        }

        System.out.println("1. Pending");
        System.out.println("2. In Progress");
        System.out.println("3. Resolved");
        System.out.print("Select New Status: ");

        int choice = readInt();

        switch (choice) {
            case 1:
                complaint.setStatus("Pending");
                break;
            case 2:
                complaint.setStatus("In Progress");
                break;
            case 3:
                complaint.setStatus("Resolved");
                break;
            default:
                System.out.println("Invalid Status Choice.");
                return;
        }

        System.out.println("Status Updated Successfully.");
    }

    private int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }
}
