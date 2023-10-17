package br.com.brenoryan.todolist.model.service.impl;

import br.com.brenoryan.todolist.model.dto.UsuarioDTO;
import br.com.brenoryan.todolist.model.dto.UsuarioDTOView;
import br.com.brenoryan.todolist.model.entity.Usuario;
import br.com.brenoryan.todolist.model.repository.UsuarioRepository;
import br.com.brenoryan.todolist.model.service.UsuarioService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Override
    public void createUser(UsuarioDTO payload) throws Exception {

        Usuario usuario = new Usuario();

        Usuario existUser = usuarioRepository.findByUserName(payload.userName());

        if(!Objects.isNull(existUser)){
            throw new Exception("Já existe um userName para " + payload.userName());
        }

        usuario.setUserName(payload.userName());
        usuario.setName(payload.name());
        String encryptedPassword = encryptedPassword(payload.password());

        usuario.setPassword(encryptedPassword);

        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findUserById(Long id) throws Exception {

        Optional<Usuario> user = usuarioRepository.findById(id);
        if(user.isEmpty()){
            throw new Exception("Não existe nenhum usuário associado ao id " + id);
        }
        return user.get();
    }

    @Override
    public List<UsuarioDTOView> listAll() throws Exception {

        try{
            List<Usuario> listUser = usuarioRepository.findAll();
            List<UsuarioDTOView> returnList = listUser.stream()
                    .map(UsuarioDTOView::fromEntity).toList();

            return returnList;
        }catch (Exception e){
            throw new Exception("Ocorreu um erro ao tentar listar todos os Usuários.");
        }
    }

    private String encryptedPassword(String password){
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(password, salt);
    }

}
