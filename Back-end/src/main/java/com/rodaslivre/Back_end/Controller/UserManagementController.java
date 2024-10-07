package com.rodaslivre.Back_end.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rodaslivre.Back_end.Model.Usuario;
import com.rodaslivre.Back_end.Service.UserManagementService;

@RestController
@RequestMapping("/usuarios/user-management")
@CrossOrigin
public class UserManagementController {

    @Autowired
    private UserManagementService userManagementService;

    @PostMapping("/recover")
    public String recoverCode(@RequestBody Usuario usuario) {

        return userManagementService.requestCode(usuario.getEmail());
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestBody Usuario usuario) {

        return userManagementService.changePassword(usuario);
    }

}