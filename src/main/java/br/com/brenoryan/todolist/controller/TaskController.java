package br.com.brenoryan.todolist.controller;

import br.com.brenoryan.todolist.model.dto.TaskDTO;
import br.com.brenoryan.todolist.model.dto.TaskDTOView;
import br.com.brenoryan.todolist.model.dto.UsuarioDTOView;
import br.com.brenoryan.todolist.model.service.TaskService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/task")
@RestController
@Tag(name = "Tasks", description = "Operations relating to Tasks")
public class TaskController {

    @Autowired
    TaskService taskService;


    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody @Valid TaskDTO taskDTO) throws Exception {

        try{
            taskService.createTask(taskDTO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem bem-sucedida", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTOView.class))
            }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/{idUser}")
    public ResponseEntity<?> listTaskByUser(@PathVariable("idUser") Long idUser) throws Exception {
        try{
            List<TaskDTOView> taskDTOViews = taskService.listUserTasks(idUser);
            return ResponseEntity.ok(taskDTOViews);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
