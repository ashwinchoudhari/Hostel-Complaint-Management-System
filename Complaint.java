public class Complaint {
    private int complaintId;
    private String studentName;
    private String complaintType;
    private String description;
    private String status;

    private static int idCounter = 1001;

    public Complaint(String studentName, String complaintType, String description) {
        this.complaintId = idCounter++;
        this.studentName = studentName;
        this.complaintType = complaintType;
        this.description = description;
        this.status = "Pending";
    }

    public int getComplaintId() { return complaintId; }
    public String getStudentName() { return studentName; }
    public String getComplaintType() { return complaintType; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayComplaint() {
        System.out.println("ID: " + complaintId
                + " | Student: " + studentName
                + " | Type: " + complaintType
                + " | Description: " + description
                + " | Status: " + status);
    }
}
