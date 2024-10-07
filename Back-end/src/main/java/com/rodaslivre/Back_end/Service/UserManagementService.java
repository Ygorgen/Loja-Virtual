package com.rodaslivre.Back_end.Service;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rodaslivre.Back_end.Model.Usuario;
import com.rodaslivre.Back_end.Repository.UsuarioRepository;

@Service
public class UserManagementService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailService emailService;

    public String requestCode(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        usuario.setCodigoRecuperacaoSenha(getCodigoRecuperacaoSenha(usuario.getId()));
        usuario.setDataEnvioCodigo(new Date());
        usuarioRepository.saveAndFlush(usuario);
        emailService.sendEmailText(usuario.getEmail(), "Código de Recuperação de Senha",
                "Olá o seu código para a recuperação de senha é : " + usuario.getCodigoRecuperacaoSenha());

        return "Código Enviado!";
    }

    private String getCodigoRecuperacaoSenha(Long id) {
        DateFormat format = new SimpleDateFormat("ddMMyyyyHHmmssmm");
        return format.format(new Date()) + id;
    }

    public String changePassword(Usuario usuario) {
        Usuario usuarioBanco = usuarioRepository.findByEmailAndCodigoRecuperacaoSenha(usuario.getEmail(),
                usuario.getCodigoRecuperacaoSenha());
        if (usuarioBanco != null) {
            Date diferenca = new Date(new Date().getTime() - usuarioBanco.getDataEnvioCodigo().getTime());
            if (diferenca.getTime() / 1000 < 900) {
                usuarioBanco.setSenha(usuario.getSenha());
                usuarioBanco.setCodigoRecuperacaoSenha(null);
                usuarioRepository.saveAndFlush(usuarioBanco);
                return "Senha alterada com sucesso!";
            } else {
                return "Tempo expirado, solicite um novo código";
            }
        } else {
            return "Email ou código não encontrado!";
        }
    }

}