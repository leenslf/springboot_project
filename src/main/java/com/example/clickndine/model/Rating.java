package com.example.clickndine.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The customer who gave the rating
    @ManyToOne
    @JoinColumn(name = "rater_id", nullable = false)
    private Customer rater;

    // Target can be a Restaurant or Courier (polymorphism using User)
    @ManyToOne
    @JoinColumn(name = "target_id", nullable = false)
    private User target;

    private int score;

    @Column(length = 1000)
    private String comment;

    private LocalDateTime createdAt;

    public Rating() {
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getRater() {
        return rater;
    }

    public void setRater(Customer rater) {
        this.rater = rater;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
