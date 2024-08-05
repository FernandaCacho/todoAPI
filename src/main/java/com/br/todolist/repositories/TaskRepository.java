package com.br.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.todolist.models.Task;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

   Optional<Task> findById(Long id);
   
}
