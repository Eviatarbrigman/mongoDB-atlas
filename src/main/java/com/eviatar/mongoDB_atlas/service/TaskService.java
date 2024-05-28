package com.eviatar.mongoDB_atlas.service;

import com.eviatar.mongoDB_atlas.model.Task;
import com.eviatar.mongoDB_atlas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(Task task) {
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return taskRepository.save(task);
    }

    public List<Task> findBySeverity(int severity) {
        return taskRepository.findBySeverity(severity);
    }

    public List<Task> getTasksByAssignee(String assignee) {
        return taskRepository.getTasksByAssignee(assignee);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(String id) {
        return taskRepository.findById(id).orElseThrow();
    }

    public Task updateTask( Task task) {
        Task taskFromDB = taskRepository.findById(task.getTaskId()).orElseThrow();
        taskFromDB.setDescription(task.getDescription());
        taskFromDB.setAssignee(task.getAssignee());
        taskFromDB.setSeverity(task.getSeverity());
        taskFromDB.setStoryPoint(task.getStoryPoint());
        return taskRepository.save(taskFromDB);
    }

    public String deleteTask(String taskId) {
        taskRepository.deleteById(taskId);
        return "task  " + taskId + " deleted";
    }
}

