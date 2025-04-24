package com.vacante.ventasapi.service;

import com.vacante.ventasapi.model.Producto;
import com.vacante.ventasapi.model.Venta;
import com.vacante.ventasapi.repository.ProductoRepository;
import com.vacante.ventasapi.repository.VentaRepository;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {
	private final VentaRepository ventaRepository;
	private final ProductoRepository productoRepository;

	public VentaService(VentaRepository ventaRepository, ProductoRepository productoRepository) {
		this.ventaRepository = ventaRepository;
		this.productoRepository = productoRepository;
	}

	public Venta registrarVenta(Long idProducto, BigDecimal cantidad) {
		Producto producto = productoRepository.findById(idProducto)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

		if (producto.getExistencia().compareTo(cantidad) < 0)
			throw new RuntimeException("No hay suficiente existencia");

		Venta venta = new Venta();
		venta.setProducto(producto);
		venta.setFecha(LocalDateTime.now());
		venta.setCantidad(cantidad);
		venta.setPrecioUnitario(producto.getPrecioUnitario());
		venta.setTotal(producto.getPrecioUnitario().multiply(cantidad));

		producto.setExistencia(producto.getExistencia().subtract(cantidad));
		productoRepository.save(producto);

		return ventaRepository.save(venta);
	}

	public List<Venta> obtenerVentasPorRango(LocalDateTime inicio, LocalDateTime fin) {
		return ventaRepository.findByFechaBetween(inicio, fin);
	}
}
