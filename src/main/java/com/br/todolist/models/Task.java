package com.br.todolist.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table (name = "task")
public class Task {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id", unique = true)
   private long id;

   @Column(name = "title", length = 100, nullable = false)
   private String title;

   @Column(name = "description", length = 255)
   private String description;

   @Column(name = "completed")
   private Boolean completed=false;


   public Task() {
   }
    
   public Task(long id, String title, String description, Boolean completed) {
      this.id = id;
      this.title = title;
      this.description = description;
      this.completed = completed;
   }

   public long getId() {
      return this.id;
   }

   public void setId(long id) {
      this.id = id;
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Boolean isCompleted() {
      return this.completed;
   }

   public Boolean getCompleted() {
      return this.completed;
   }

   public void setCompleted(Boolean completed) {
      this.completed = completed;
   }

   public Task id(long id) {
      setId(id);
      return this;
   }

   public Task title(String title) {
      setTitle(title);
      return this;
   }

   public Task description(String description) {
      setDescription(description);
      return this;
   }

   public Task completed(Boolean completed) {
      setCompleted(completed);
      return this;
   }

   @Override
   public boolean equals(Object o) {
      if(o == this)
      {
         return true;
      }
      if(!(o instanceof Task))
      {
         return false;
      }
         
      Task task = (Task) o;
         
      return Objects.equals(id, task.id) && Objects.equals(title, task.title) && Objects.equals(description, task.description) && Objects.equals(completed, task.completed);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, title, description, completed);
   }

}
