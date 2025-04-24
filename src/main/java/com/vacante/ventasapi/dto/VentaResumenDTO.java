package com.vacante.ventasapi.dto;

import java.math.BigDecimal;

public class VentaResumenDTO {
	private Long idProducto;
	private String descripcionProducto;
	private BigDecimal totalVentas;

	// Constructor
	public VentaResumenDTO(Long idProducto, String descripcionProducto, BigDecimal totalVentas) {
		this.idProducto = idProducto;
		this.descripcionProducto = descripcionProducto;
		this.totalVentas = totalVentas;
	}

	// Getters y setters
	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	public BigDecimal getTotalVentas() {
		return totalVentas;
	}

	public void setTotalVentas(BigDecimal totalVentas) {
		this.totalVentas = totalVentas;
	}
}
