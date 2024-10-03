package com.rodaslivre.Back_end.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rodaslivre.Back_end.Dto.UserClientRequestDTO;
import com.rodaslivre.Back_end.Model.Usuario;
import com.rodaslivre.Back_end.Service.UsuarioClientService;

@RestController
@RequestMapping("/usuarios/client")
@CrossOrigin
public class UserClientController {
    
    @Autowired
    private UsuarioClientService usuarioService;

    @PostMapping("/cadastrar")
    public Usuario post(@RequestBody UserClientRequestDTO userClientRequestDTO){
        return usuarioService.registrar(userClientRequestDTO);
    }

}