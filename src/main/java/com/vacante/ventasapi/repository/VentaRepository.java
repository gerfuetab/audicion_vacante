package com.vacante.ventasapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacante.ventasapi.model.Venta;

import java.time.LocalDateTime;
import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {
	List<Venta> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);
}
