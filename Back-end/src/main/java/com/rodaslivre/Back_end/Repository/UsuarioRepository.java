package com.rodaslivre.Back_end.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rodaslivre.Back_end.Model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Usuario findByEmail(String email);

    Usuario findByEmailAndCodigoRecuperacaoSenha(String email, String codigoRecuperacaoSenha);
}
