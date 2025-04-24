package com.vacante.ventasapi.dto;

import java.math.BigDecimal;
import java.util.List;

public class RespuestaVentasDTO {
	private List<VentaResumenDTO> ventasPorProducto;
	private BigDecimal totalGlobal;

	public RespuestaVentasDTO(List<VentaResumenDTO> ventasPorProducto, BigDecimal totalGlobal) {
		this.ventasPorProducto = ventasPorProducto;
		this.totalGlobal = totalGlobal;
	}

	// Getters y setters
	public List<VentaResumenDTO> getVentasPorProducto() {
		return ventasPorProducto;
	}

	public void setVentasPorProducto(List<VentaResumenDTO> ventasPorProducto) {
		this.ventasPorProducto = ventasPorProducto;
	}

	public BigDecimal getTotalGlobal() {
		return totalGlobal;
	}

	public void setTotalGlobal(BigDecimal totalGlobal) {
		this.totalGlobal = totalGlobal;
	}
}
