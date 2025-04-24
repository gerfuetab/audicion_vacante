package com.vacante.ventasapi.controller;

import com.vacante.ventasapi.model.Producto;
import com.vacante.ventasapi.service.ProductoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
	private final ProductoService productoService;

	public ProductoController(ProductoService productoService) {
		this.productoService = productoService;
	}

	@PostMapping("/crearProducto")
	public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
		return ResponseEntity.ok(productoService.guardarProducto(producto));
	}

	@PutMapping("/actualizar/{id}")
	public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
		return ResponseEntity.ok(productoService.actualizarProducto(id, producto));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Producto> obtenerProducto(@PathVariable Long id) {
		Optional<Producto> producto = productoService.obtenerProductoPorId(id);
		return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
