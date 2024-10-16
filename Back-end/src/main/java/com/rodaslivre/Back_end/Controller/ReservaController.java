package com.rodaslivre.Back_end.Controller;

import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rodaslivre.Back_end.Model.Carro;
import com.rodaslivre.Back_end.Model.Reserva;
import com.rodaslivre.Back_end.Model.Usuario;
import com.rodaslivre.Back_end.Repository.CarroRepository;
import com.rodaslivre.Back_end.Repository.ReservaRepository;
import com.rodaslivre.Back_end.Repository.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservaController {

	@Autowired
	private ReservaRepository reservaRepository;

	@Autowired
	private CarroRepository carroRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping
	public ResponseEntity<List<Reserva>> getAll() {
		return ResponseEntity.ok(reservaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Reserva> getByid(@PathVariable Long id) {
		return reservaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@PostMapping
public ResponseEntity<Reserva> post(@Valid @RequestBody Reserva reserva) {
    // Verificar se o ID do usuário foi fornecido
    Long usuarioId = reserva.getUsuario() != null ? reserva.getUsuario().getId() : null;
    if (usuarioId == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID do usuário ausente ou inválido!");
    }

    // Buscando o usuário pelo ID
    Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(usuarioId);
    if (usuarioEncontrado.isEmpty()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário com o ID " + usuarioId + " não encontrado!");
    }

    // Buscando o carro associado à reserva
    return carroRepository.findById(reserva.getCarro().getId()).map(carro -> {
        if (!carro.isDisponibilidade()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carro Indisponível!");
        }


        float valorTotal = (float) (reserva.getDiasalugados() * carro.getPrecoPorDia());
        reserva.setValortotal(valorTotal);


        carro.setDisponibilidade(false);
        carroRepository.save(carro);


        reserva.setCarro(carro);
        reserva.setUsuario(usuarioEncontrado.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaRepository.save(reserva));

    }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Carro não Encontrado!"));
}


	@PutMapping
	public ResponseEntity<Reserva> put(@Valid @RequestBody Reserva reserva) {
		return reservaRepository.findById(reserva.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(reservaRepository.save(reserva)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Reserva> reserva = reservaRepository.findById(id);

		if (reserva.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

		Carro carro = reserva.get().getCarro();

		carro.setDisponibilidade(true);
		carroRepository.save(carro);

		reservaRepository.deleteById(id);
	}
}
