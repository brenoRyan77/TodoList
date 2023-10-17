package br.com.brenoryan.todolist.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_task")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 255)
    private String description;

    @Column(nullable = false, length = 50)
    private String title;
    private String priority;
    private Long idUser;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String status;

    @CreationTimestamp
    private LocalDateTime createdAt;


}
