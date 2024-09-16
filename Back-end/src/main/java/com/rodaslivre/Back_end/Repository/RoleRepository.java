package com.rodaslivre.Back_end.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rodaslivre.Back_end.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
    
    List<Role> findByNome(String nome);
}