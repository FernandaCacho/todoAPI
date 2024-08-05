package com.br.todolist.services;

import java.util.List;
import java.util.Optional;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.todolist.models.Task;
import com.br.todolist.repositories.TaskRepository;

@Service
public class TaskService {
   
   @Autowired
   private TaskRepository taskRepository;

   public List<Task> findAllTasks() {
      return taskRepository.findAll(); 
   }

   public Task findById(Long id) {
      Optional<Task> task = this.taskRepository.findById(id);
      return task.orElseThrow(()-> new RuntimeException("Id nao encontrado"));
   }

   @Transactional
   public Task create(Task job) {
      job.setId(0); //verificar
      return this.taskRepository.save(job);
   }

   @Transactional
   public Task update(Task job){
      Task newJob = findById(job.getId());

      newJob.setTitle(job.getTitle());
      newJob.setDescription(job.getDescription());
      newJob.setCompleted(job.getCompleted());
      
      return this.taskRepository.save(newJob);
   }

   public void delete(Long id) {
      findById(id);
      this.taskRepository.deleteById(id);
   }
}
