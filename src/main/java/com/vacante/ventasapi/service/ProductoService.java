package com.vacante.ventasapi.service;

import com.vacante.ventasapi.model.Producto;
import com.vacante.ventasapi.repository.ProductoRepository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductoService {
	private final ProductoRepository productoRepository;

	public ProductoService(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}

	@Transactional
	public Producto guardarProducto(Producto producto) {
		return productoRepository.save(producto);
	}

	@Transactional
	public Producto actualizarProducto(Long id, Producto datosActualizados) {
		Producto producto = productoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Producto no encontrado"));
		if (datosActualizados.getPrecioUnitario() != null)
			producto.setPrecioUnitario(datosActualizados.getPrecioUnitario());
		if (datosActualizados.getExistencia() != null)
			producto.setExistencia(datosActualizados.getExistencia());
		return productoRepository.save(producto);
	}

	public Optional<Producto> obtenerProductoPorId(Long id) {
		return productoRepository.findById(id);
	}
}
