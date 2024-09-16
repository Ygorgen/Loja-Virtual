package com.rodaslivre.Back_end.Service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rodaslivre.Back_end.Model.Role;
import com.rodaslivre.Back_end.Repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    public Role post(Role objeto) {
        objeto.setDataCriacao(new Date());
        Role objetoNovo = roleRepository.saveAndFlush(objeto);
        return objetoNovo;
    }

    public Role put(Role objeto) {
        objeto.setDataAtualizacao(new Date());
        return roleRepository.saveAndFlush(objeto);
    }

    public void delete(Long id) {
        Role objeto = roleRepository.findById(id).get();
        roleRepository.delete(objeto);
    }
}
