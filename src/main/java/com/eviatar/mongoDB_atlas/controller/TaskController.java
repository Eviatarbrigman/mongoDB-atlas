package com.eviatar.mongoDB_atlas.controller;

import com.eviatar.mongoDB_atlas.model.Task;
import com.eviatar.mongoDB_atlas.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/task")
    public ResponseEntity<Task> createTask (@RequestBody Task task) {
        Task taskToSave = new Task();
        taskToSave.setDescription(task.getDescription());
        taskToSave.setAssignee(task.getAssignee());
        taskToSave.setStoryPoint(task.getStoryPoint());
        taskToSave.setSeverity(task.getSeverity());
        return new ResponseEntity<>(taskService.addTask(taskToSave), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTasksBySeverity(@PathVariable String id) {
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/severity/{severity}")
    public ResponseEntity<List<Task>> getTasksBySeverity(@PathVariable Integer severity) {
        return new ResponseEntity<>(taskService.findBySeverity(severity), HttpStatus.OK);
    }

    @GetMapping("/assignee/{assignee}")
    public ResponseEntity<List<Task>> getTasksByAssignee(@PathVariable String assignee) {
        return new ResponseEntity<>(taskService.getTasksByAssignee(assignee), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.updateTask(task), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@RequestBody String taskId) {
        return new ResponseEntity<>(taskService.deleteTask(taskId), HttpStatus.OK);
    }

}
