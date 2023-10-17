package br.com.brenoryan.todolist.controller;

import br.com.brenoryan.todolist.model.dto.UsuarioDTO;
import br.com.brenoryan.todolist.model.dto.UsuarioDTOView;
import br.com.brenoryan.todolist.model.service.UsuarioService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNullApi;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Operations relating to Users")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> incluirUser(@RequestBody @Valid UsuarioDTO payload) throws Exception {

        try{
            usuarioService.createUser(payload);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listagem bem-sucedida", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioDTOView.class))
            }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public ResponseEntity<?> listAll() throws Exception {
        try{
            List<UsuarioDTOView> listReturn = usuarioService.listAll();
            return ResponseEntity.ok(listReturn);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
