package com.example.primerparcialprogra3.service;

import com.example.primerparcialprogra3.model.Task;
import com.example.primerparcialprogra3.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(String description, String priority) {
        Task task = new Task();
        task.setDescription(description);
        task.setPriority(priority);
        return taskRepository.save(task);
    }

    public Task getNextTask() {
        return taskRepository.findNext();
    }

    public Task processTask() {
        Task task = taskRepository.processNext();
        if (task == null) {
            throw new RuntimeException("No hay tareas en la cola para procesar");
        }
        return task;
    }

    public Task[] getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public Map<String, Object> getQueueStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("size", taskRepository.getQueueSize());
        status.put("isEmpty", taskRepository.isEmpty());
        status.put("nextTask", taskRepository.findNext());
        return status;
    }

    public void clearQueue() {
        taskRepository.clearQueue();
    }
}
