package com.rodaslivre.Back_end.Dto;

import org.springframework.beans.BeanUtils;
import com.rodaslivre.Back_end.Model.Usuario;
import lombok.Data;

@Data
public class UserClientRequestDTO {

    private String nome;
    private String cpf;
    private String email;
    

    public Usuario converter(UserClientRequestDTO userClientRequestDTO){
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(userClientRequestDTO, usuario);
        return usuario;
    }
}
