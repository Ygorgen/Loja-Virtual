package com.rodaslivre.Back_end.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rodaslivre.Back_end.Model.UserPermission;

public interface RoleUserRepository extends JpaRepository<UserPermission, Long>{
    
}