package com.rodaslivre.Back_end.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rodaslivre.Back_end.Model.Role;
import com.rodaslivre.Back_end.Repository.RoleRepository;
import com.rodaslivre.Back_end.Service.RoleService;

@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
    
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping()
    public List<Role> findAll(){
       return roleService.getAll();
    }

    @GetMapping("/{id}")
	public ResponseEntity<Role> getByid(@PathVariable Long id) {
		return roleRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

    @PostMapping()
    public Role post(@RequestBody Role objeto){
        return roleService.post(objeto);
    }

    @PutMapping()
    public Role put(@RequestBody Role objeto){
        return roleService.put(objeto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        roleService.delete(id);
        return ResponseEntity.ok().build();
    }

}
