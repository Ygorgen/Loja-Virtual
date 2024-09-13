package com.rodaslivre.Back_end.Model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_carros")
@Data

public class Carro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotBlank(message = "A marca não pode estar em branco.")
	@Size(min = 2, max = 100, message = "O atributo marca deve conter no mínimo 2 e no máximo 100 caracteres.")
	private String marca;

	@NotBlank(message = "O modelo não pode estar em branco.")
	@Size(min = 2, max = 100, message = "O atributo modelo deve conter no mínimo 2 e no máximo 100 caracteres.")
	private String modelo;

	@Min(value = 2005, message = "O ano deve ser no mínimo 2005.")
	@Max(value = 2025, message = "O ano deve ser no máximo 2025.")
	private int ano;

	@NotBlank(message = "O tipo não pode estar em branco.")
	private String tipo;

	@Min(value = 0, message = "O preço por dia deve ser positivo.")
	private double precoPorDia;
    
	private boolean disponibilidade;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "carro", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("carro")
	private List<Reserva> reserva;
}
