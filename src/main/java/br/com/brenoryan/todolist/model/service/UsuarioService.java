package br.com.brenoryan.todolist.model.service;

import br.com.brenoryan.todolist.model.dto.UsuarioDTO;
import br.com.brenoryan.todolist.model.dto.UsuarioDTOView;
import br.com.brenoryan.todolist.model.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    void createUser(UsuarioDTO payload) throws Exception;
    Usuario findUserById(Long id) throws Exception;

    List<UsuarioDTOView> listAll() throws Exception;
}
