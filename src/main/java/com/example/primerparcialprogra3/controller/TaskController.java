package com.example.primerparcialprogra3.controller;

import com.example.primerparcialprogra3.model.Task;
import com.example.primerparcialprogra3.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody Map<String, String> request) {
        String description = request.get("description");
        String priority = request.get("priority");
        
        if (description == null || description.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        
        Task task = taskService.addTask(description, priority != null ? priority : "normal");
        return ResponseEntity.ok(task);
    }

    @GetMapping("/next")
    public ResponseEntity<Task> getNextTask() {
        Task task = taskService.getNextTask();
        if (task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @PostMapping("/process")
    public ResponseEntity<?> processTask() {
        try {
            Task task = taskService.processTask();
            return ResponseEntity.ok(task);
        } catch (RuntimeException e) {
            Map<String, String> error = new java.util.HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> getQueueStatus() {
        Map<String, Object> status = taskService.getQueueStatus();
        return ResponseEntity.ok(status);
    }

    @GetMapping("/all")
    public ResponseEntity<Task[]> getAllTasks() {
        Task[] tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @DeleteMapping("/clear")
    public ResponseEntity<Void> clearQueue() {
        taskService.clearQueue();
        return ResponseEntity.ok().build();
    }
}
