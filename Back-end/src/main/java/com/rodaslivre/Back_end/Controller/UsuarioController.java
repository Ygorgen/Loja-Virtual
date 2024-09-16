package com.rodaslivre.Back_end.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.rodaslivre.Back_end.Model.Usuario;
import com.rodaslivre.Back_end.Repository.UsuarioRepository;
import com.rodaslivre.Back_end.Service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/all")
    public List<Usuario> findAll(){
       return usuarioService.getAll();
    }

    @GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id) {
		return usuarioRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}

    @PostMapping("/cadastrar")
    public Usuario post(@RequestBody Usuario objeto){
        return usuarioService.post(objeto);
    }

    @PutMapping("/atualizar")
    public Usuario put(@RequestBody Usuario objeto){
        return usuarioService.put(objeto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        usuarioService.delete(id);
        return ResponseEntity.ok().build();
    }

}