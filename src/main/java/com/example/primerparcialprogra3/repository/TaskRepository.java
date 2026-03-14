package com.example.primerparcialprogra3.repository;

import com.example.primerparcialprogra3.model.Task;
import com.example.primerparcialprogra3.model.TaskQueue;
import org.springframework.stereotype.Repository;

@Repository
public class TaskRepository {
    private final TaskQueue taskQueue;

    public TaskRepository() {
        this.taskQueue = new TaskQueue();
    }

    public Task save(Task task) {
        taskQueue.enqueue(task);
        return task;
    }

    public Task findNext() {
        return taskQueue.peek();
    }

    public Task processNext() {
        return taskQueue.dequeue();
    }

    public Task[] getAllTasks() {
        return taskQueue.toArray();
    }

    public int getQueueSize() {
        return taskQueue.size();
    }

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }

    public void clearQueue() {
        taskQueue.clear();
    }
}
