public class ComplaintRepository {
    private Complaint[] complaints = new Complaint[50];
    private int count = 0;

    public boolean addComplaint(Complaint complaint) {
        if (count >= complaints.length) {
            return false;
        }
        complaints[count++] = complaint;
        return true;
    }

    public void displayAllComplaints() {
        if (count == 0) {
            System.out.println("No complaints registered.");
            return;
        }

        System.out.println("\n---- All Complaints ----");
        for (int i = 0; i < count; i++) {
            complaints[i].displayComplaint();
        }
    }

    public Complaint findById(int id) {
        for (int i = 0; i < count; i++) {
            if (complaints[i].getComplaintId() == id) {
                return complaints[i];
            }
        }
        return null;
    }
}
