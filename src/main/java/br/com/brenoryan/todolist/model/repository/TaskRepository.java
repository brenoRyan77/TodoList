package br.com.brenoryan.todolist.model.repository;

import br.com.brenoryan.todolist.model.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(name = "SELECT T* FROM TASK T WHERE T.IDUSER = :idUser")
    List<Task> findByIdUser(Long idUser);

    @Query(name = "SELECT T* FROM TASK T WHERE T.STATUS = :status")
    List<Task> findTaskByStatus(String status);
}
