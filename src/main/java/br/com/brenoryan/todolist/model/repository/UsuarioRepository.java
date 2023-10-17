package br.com.brenoryan.todolist.model.repository;

import br.com.brenoryan.todolist.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUserName(String userName);

    Optional<Usuario> findById(Long id);
}
