package com.diego.app.service.inte;

import java.util.List;

import com.diego.app.models.entity.Movimiento;

public interface IMovimientoService {
	
	public List<Movimiento> findAll();

	public void save(Movimiento movimiento);

	public Movimiento findOne(Long id);

	public void delete(Long id);
	
	public String Movimiento(Movimiento movimiento);
}
