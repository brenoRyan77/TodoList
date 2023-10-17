package br.com.brenoryan.todolist.model.service;

import br.com.brenoryan.todolist.model.dto.TaskDTO;
import br.com.brenoryan.todolist.model.dto.TaskDTOView;

import java.util.List;

public interface TaskService {

    void createTask(TaskDTO payload) throws Exception;

    List<TaskDTOView> listUserTasks(Long idUser) throws Exception;
}
