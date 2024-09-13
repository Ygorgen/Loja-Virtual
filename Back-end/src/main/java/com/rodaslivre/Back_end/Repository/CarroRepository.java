package com.rodaslivre.Back_end.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.rodaslivre.Back_end.Model.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long> {
	
    List<Carro> findAllByMarcaContainingIgnoreCase(@Param("marca") String marca);

    List<Carro> findAllByDisponibilidade(boolean disponibilidade);
}

