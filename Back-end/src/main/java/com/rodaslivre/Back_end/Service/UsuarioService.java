package com.rodaslivre.Back_end.Service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rodaslivre.Back_end.Model.Usuario;
import com.rodaslivre.Back_end.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario post(Usuario objeto) {
        objeto.setDataCriacao(new Date());
        Usuario objetoNovo = usuarioRepository.saveAndFlush(objeto);
        return objetoNovo;
    }

    public Usuario put(Usuario objeto) {
        objeto.setDataAtualizacao(new Date());
        return usuarioRepository.saveAndFlush(objeto);
    }

    public void delete(Long id) {
        Usuario objeto = usuarioRepository.findById(id).get();
        usuarioRepository.delete(objeto);
    }
}