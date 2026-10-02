package com.example.complaint;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@CrossOrigin(origins = "*")
public class ComplaintController {

    private final ComplaintRepository repository;

    public ComplaintController(ComplaintRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Complaint> getAllComplaints() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Complaint getComplaintById(@PathVariable int id) {
        return repository.findById(id);
    }

    @PostMapping
    public Complaint registerComplaint(@RequestBody Complaint complaint) {
        repository.addComplaint(complaint);
        return complaint;
    }

    @PutMapping("/{id}/status")
    public Complaint updateStatus(@PathVariable int id, @RequestBody StatusUpdateRequest request) {
        Complaint complaint = repository.findById(id);
        if (complaint != null) {
            complaint.setStatus(request.getStatus());
        }
        return complaint;
    }
}

class StatusUpdateRequest {
    private String status;
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
