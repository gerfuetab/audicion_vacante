package com.vacante.ventasapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false)
	@Size(max = 100)
	@NotBlank
	private String descripcion;

	@Column(nullable = false)
	@DecimalMin(value = "0.0", inclusive = true)
	private BigDecimal precioUnitario;

	@Column(nullable = false)
	@DecimalMin(value = "0.0", inclusive = true)
	private BigDecimal existencia;

	// Getters y Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public BigDecimal getExistencia() {
		return existencia;
	}

	public void setExistencia(BigDecimal existencia) {
		this.existencia = existencia;
	}
}
