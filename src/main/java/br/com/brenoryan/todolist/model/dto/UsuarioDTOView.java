package br.com.brenoryan.todolist.model.dto;


import br.com.brenoryan.todolist.model.entity.Usuario;
import lombok.Data;

@Data
public class UsuarioDTOView {


    private String userName;
    private String name;

    public static UsuarioDTOView fromEntity(Usuario usuario){

        var response = new UsuarioDTOView();
        response.setName(usuario.getName());
        response.setUserName(usuario.getUserName());

        return response;
    }
}
