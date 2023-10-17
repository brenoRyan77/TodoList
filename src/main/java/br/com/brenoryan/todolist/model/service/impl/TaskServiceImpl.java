package br.com.brenoryan.todolist.model.service.impl;

import br.com.brenoryan.todolist.model.dto.TaskDTO;
import br.com.brenoryan.todolist.model.dto.TaskDTOView;
import br.com.brenoryan.todolist.model.dto.UsuarioDTOView;
import br.com.brenoryan.todolist.model.entity.Task;
import br.com.brenoryan.todolist.model.entity.Usuario;
import br.com.brenoryan.todolist.model.repository.TaskRepository;
import br.com.brenoryan.todolist.model.service.TaskService;
import br.com.brenoryan.todolist.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UsuarioService usuarioService;
    @Override
    public void createTask(TaskDTO payload) throws Exception {

        Task newTask = new Task();

        Usuario user = usuarioService.findUserById(payload.getIdUser());

        if(Objects.isNull(user)){
            throw new Exception("Não existe nenhum usuário associado ao id " + payload.getIdUser());
        }

        newTask.setDescription(payload.getDescription());
        newTask.setTitle(payload.getTitle());
        newTask.setPriority(payload.getPriority());

        if(payload.getEndAt() != null && payload.getStartAt().isAfter(payload.getEndAt())){
            throw new Exception("A data e início da Task não pode ser maior do que a data fim.");
        }

        if(payload.getEndAt() != null){
            newTask.setEndAt(payload.getEndAt());
        }
        newTask.setStatus("CRIADO");
        newTask.setStartAt(payload.getStartAt());
        newTask.setIdUser(payload.getIdUser());

        taskRepository.save(newTask);
    }

    @Override
    public List<TaskDTOView> listUserTasks(Long idUser) throws Exception {

       try{
           List<TaskDTOView> listReturn = new ArrayList<>();

           Usuario user = usuarioService.findUserById(idUser);
           if(Objects.isNull(user)){
               throw new Exception("Não existe nenhum usuário associado ao id " + idUser);
           }
           List<Task> taskListByUser = taskRepository.findByIdUser(idUser);
           taskListByUser.forEach(tasks -> {
               TaskDTOView taskDTOView = new TaskDTOView();
               taskDTOView = TaskDTOView.fromEntity(tasks);
               UsuarioDTOView usuarioDTOView = new UsuarioDTOView();
               usuarioDTOView.setUserName(user.getUserName());
               usuarioDTOView.setName(user.getName());
               taskDTOView.setUsuario(usuarioDTOView);

               listReturn.add(taskDTOView);
           });

           return listReturn;

       }catch (Exception e){
            throw new Exception("Ocorreu um erro ao tentar recuperar Task de Usuário.");
       }
    }

}
