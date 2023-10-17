package br.com.brenoryan.todolist.model.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskDTO {

    private String description;
    private String title;
    private String priority;
    private Long idUser;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
}
