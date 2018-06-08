package com.diego.app.service.inte;

import java.util.List;

import com.diego.app.models.entity.CuentaBancaria;

public interface ICuentaBancariaService {
	
	public List<CuentaBancaria> findAll();

	public void save(CuentaBancaria cuentaBancaria);

	public CuentaBancaria findOne(Long id);

	public void delete(Long id);
	
	public Boolean ExcedioNumMovimientos(CuentaBancaria cuentabancaria);
}
