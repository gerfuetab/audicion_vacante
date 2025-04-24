package com.vacante.ventasapi.controller;

import com.vacante.ventasapi.dto.RespuestaVentasDTO;
import com.vacante.ventasapi.dto.VentaResumenDTO;
import com.vacante.ventasapi.model.Venta;
import com.vacante.ventasapi.service.VentaService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
	private final VentaService ventaService;

	public VentaController(VentaService ventaService) {
		this.ventaService = ventaService;
	}

	@PostMapping
	public ResponseEntity<Venta> registrarVenta(@RequestBody Map<String, Object> request) {
		Long idProducto = ((Number) request.get("idProducto")).longValue();
		BigDecimal cantidad = new BigDecimal(request.get("cantidad").toString());
		Venta venta = ventaService.registrarVenta(idProducto, cantidad);
		return ResponseEntity.ok(venta);
	}

	@GetMapping
	public ResponseEntity<RespuestaVentasDTO> consultarVentas(
			@RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
			@RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {

		LocalDateTime desde = inicio.atStartOfDay();
		LocalDateTime hasta = fin.atTime(LocalTime.MAX);

		List<Venta> ventas = ventaService.obtenerVentasPorRango(desde, hasta);

		// Agrupar ventas por producto y crear el resumen
		List<VentaResumenDTO> resumen = ventas.stream()
				.collect(Collectors.groupingBy(v -> v.getProducto().getId(), LinkedHashMap::new, 
						Collectors.collectingAndThen(Collectors.toList(), lista -> {
							Long id = lista.get(0).getProducto().getId();
							String descripcion = lista.get(0).getProducto().getDescripcion();
							BigDecimal totalVentas = lista.stream().map(Venta::getTotal).reduce(BigDecimal.ZERO,
									BigDecimal::add);
							return new VentaResumenDTO(id, descripcion, totalVentas);
						})))
				.values().stream().collect(Collectors.toList());

		// Calcular el total global de todas las ventas
		BigDecimal totalGlobal = resumen.stream().map(VentaResumenDTO::getTotalVentas).reduce(BigDecimal.ZERO,
				BigDecimal::add);

		// Crear la respuesta final
		RespuestaVentasDTO respuesta = new RespuestaVentasDTO(resumen, totalGlobal);

		return ResponseEntity.ok(respuesta);
	}
}
