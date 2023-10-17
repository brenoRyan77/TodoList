package br.com.brenoryan.todolist.model.dto;

import br.com.brenoryan.todolist.model.entity.Task;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTOView {

    private String description;
    private String title;
    private String priority;
    private String status;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private UsuarioDTOView usuario;

    public static TaskDTOView fromEntity(Task task){

        var response = new TaskDTOView();
        response.setDescription(task.getDescription());
        response.setPriority(task.getPriority());
        response.setTitle(task.getTitle());
        response.setStartAt(task.getStartAt());
        response.setEndAt(task.getEndAt());
        response.setStatus(task.getStatus());
        return response;
    }
}
