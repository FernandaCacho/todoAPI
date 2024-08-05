package com.br.todolist.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.todolist.models.Task;
import com.br.todolist.services.TaskService;

@RestController
@RequestMapping("/tasks")
@Validated
public class TaskController {

   @Autowired
   private TaskService taskService;

   @GetMapping
   public ResponseEntity<List<Task>> findAllTasks(){
      List<Task> tasks = this.taskService.findAllTasks();
      return ResponseEntity.ok(tasks);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Task> findById(@PathVariable Long id){
      Task objeto = this.taskService.findById(id);
      return ResponseEntity.ok(objeto);
   }
   
   @PostMapping
   @Validated
   public ResponseEntity<Void> create(@Validated @RequestBody Task objeto){
      this.taskService.create(objeto);
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objeto.getId()).toUri();

      return ResponseEntity.created(uri).build();
   }

   @PutMapping("/{id}")
   @Validated
   public ResponseEntity<Void> update(@Validated @RequestBody Task objeto, @PathVariable Long id){
      objeto.setId(id);
      this.taskService.update(objeto);

      return ResponseEntity.noContent().build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id){
      this.taskService.delete(id);

      return ResponseEntity.noContent().build();
   }
}
