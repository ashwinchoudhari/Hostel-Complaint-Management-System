const API_URL = "http://localhost:8080/api/complaints";

document.addEventListener("DOMContentLoaded", () => {
  loadComplaints();

  document.getElementById("complaintForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    
    const complaintData = {
      studentName: document.getElementById("studentName").value,
      complaintType: document.getElementById("complaintType").value,
      description: document.getElementById("description").value
    };

    try {
      const response = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(complaintData)
      });

      if (response.ok) {
        alert("Complaint registered successfully!");
        document.getElementById("complaintForm").reset();
        loadComplaints();
      } else {
        alert("Failed to register complaint.");
      }
    } catch (err) {
      console.error(err);
      alert("Error connecting to server.");
    }
  });
});

async function loadComplaints() {
  try {
    const response = await fetch(API_URL);
    if (!response.ok) return;
    const complaints = await response.json();
    const tableBody = document.getElementById("complaintTableBody");
    tableBody.innerHTML = "";

    complaints.forEach(c => {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td>${c.complaintId}</td>
        <td>${c.studentName}</td>
        <td>${c.complaintType}</td>
        <td>${c.description}</td>
        <td><strong>${c.status}</strong></td>
        <td>
          <select onchange="updateStatus(${c.complaintId}, this.value)">
            <option value="">Change Status</option>
            <option value="Pending">Pending</option>
            <option value="In Progress">In Progress</option>
            <option value="Resolved">Resolved</option>
          </select>
        </td>
      `;
      tableBody.appendChild(row);
    });
  } catch (err) {
    console.error("Failed to load complaints:", err);
  }
}

async function searchComplaint() {
  const id = document.getElementById("searchId").value;
  if (!id) return;

  try {
    const response = await fetch(`${API_URL}/${id}`);
    const resultDiv = document.getElementById("searchResult");

    if (response.ok) {
      const c = await response.json();
      resultDiv.innerHTML = `
        <p><strong>ID:</strong> ${c.complaintId} | <strong>Student:</strong> ${c.studentName} | 
        <strong>Type:</strong> ${c.complaintType} | <strong>Description:</strong> ${c.description} | <strong>Status:</strong> ${c.status}</p>
      `;
    } else {
      resultDiv.innerHTML = "<p style='color:red;'>Complaint not found.</p>";
    }
  } catch (err) {
    console.error(err);
  }
}

async function updateStatus(id, newStatus) {
  if (!newStatus) return;

  try {
    await fetch(`${API_URL}/${id}/status`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ status: newStatus })
    });

    loadComplaints();
  } catch (err) {
    console.error(err);
  }
}
