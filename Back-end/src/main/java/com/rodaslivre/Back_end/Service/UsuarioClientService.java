package com.rodaslivre.Back_end.Service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rodaslivre.Back_end.Dto.UserClientRequestDTO;
import com.rodaslivre.Back_end.Model.Usuario;
import com.rodaslivre.Back_end.Repository.UserClientRepository;

@Service
public class UsuarioClientService {

    @Autowired
    private UserClientRepository usuarioRepository;

    @Autowired
    private RoleUserService roleUserService;


    public Usuario post(UserClientRequestDTO userClientRequestDTO) {
        Usuario usuario = new UserClientRequestDTO().converter(userClientRequestDTO);
        usuario.setDataCriacao(new Date());
        Usuario objetoNovo = usuarioRepository.saveAndFlush(usuario);
        roleUserService.vincularUserPermissionClient(objetoNovo);
        return objetoNovo;
    }

    






}