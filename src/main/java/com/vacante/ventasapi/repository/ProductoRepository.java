package com.vacante.ventasapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacante.ventasapi.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
