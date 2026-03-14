package com.example.primerparcialprogra3.model;

public class TaskQueue {
    private QueueNode<Task> front;
    private QueueNode<Task> rear;
    private int size;
    private Long nextId;

    public TaskQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
        this.nextId = 1L;
    }

    public void enqueue(Task task) {
        if (task.getId() == null) {
            task.setId(nextId++);
        }
        
        QueueNode<Task> newNode = new QueueNode<>(task);
        
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
        size++;
    }

    public Task dequeue() {
        if (isEmpty()) {
            return null;
        }
        
        Task data = front.getData();
        front = front.getNext();
        
        if (front == null) {
            rear = null;
        }
        
        size--;
        return data;
    }

    public Task peek() {
        if (isEmpty()) {
            return null;
        }
        return front.getData();
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        front = null;
        rear = null;
        size = 0;
        nextId = 1L;
    }

    public Task[] toArray() {
        Task[] tasks = new Task[size];
        QueueNode<Task> current = front;
        int index = 0;
        
        while (current != null) {
            tasks[index++] = current.getData();
            current = current.getNext();
        }
        
        return tasks;
    }
}
