package com.rodaslivre.Back_end.Service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rodaslivre.Back_end.Model.Role;
import com.rodaslivre.Back_end.Model.UserPermission;
import com.rodaslivre.Back_end.Model.Usuario;
import com.rodaslivre.Back_end.Repository.RoleRepository;
import com.rodaslivre.Back_end.Repository.RoleUserRepository;

@Service
public class RoleUserService {

    @Autowired
    private RoleUserRepository roleUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void vincularUserPermissionClient(Usuario usuario) {

        List<Role> ListRole = roleRepository.findByNome("comumuser");
        if (ListRole.size() > 0) {
            UserPermission userPermission = new UserPermission();
            userPermission.setUsuario(usuario);
            userPermission.setRole(ListRole.get(0));
            userPermission.setDataCriacao(new Date());
            roleUserRepository.saveAndFlush(userPermission);
        }

    }

}
