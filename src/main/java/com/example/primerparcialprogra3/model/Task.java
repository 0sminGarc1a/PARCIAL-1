package com.example.primerparcialprogra3.model;

public class Task {
    private Long id;
    private String description;
    private String priority;
    private java.time.LocalDateTime createdAt;

    public Task() {
        this.createdAt = java.time.LocalDateTime.now();
    }

    public Task(Long id, String description, String priority) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.createdAt = java.time.LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public java.time.LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.time.LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
