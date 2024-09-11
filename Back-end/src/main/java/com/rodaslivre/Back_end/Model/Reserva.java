package com.rodaslivre.Back_end.Model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name="tb_reservas")
@Data
public class Reserva {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

    @NotNull(message = "A data de início não pode ser nula.")
    private int diasalugados;
    
    private float valortotal;
    
    private Date datadealuguel;

    @OneToOne
	@JsonIgnoreProperties({"reserva", "reservas"})
	private Carro carro;
}