package com.example.clickndine.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The customer who made the complaint
    @ManyToOne
    @JoinColumn(name = "complainant_id", nullable = false)
    private Customer complainant;

    @Column(nullable = false, length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private ComplaintStatus status;

    private LocalDateTime createdAt;

    // Optionally, assign an administrator to handle this complaint:
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Administrator assignedAdmin;

    public Complaint() {
        this.createdAt = LocalDateTime.now();
        this.status = ComplaintStatus.OPEN;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getComplainant() {
        return complainant;
    }

    public void setComplainant(Customer complainant) {
        this.complainant = complainant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public void setStatus(ComplaintStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Administrator getAssignedAdmin() {
        return assignedAdmin;
    }

    public void setAssignedAdmin(Administrator assignedAdmin) {
        this.assignedAdmin = assignedAdmin;
    }
}
