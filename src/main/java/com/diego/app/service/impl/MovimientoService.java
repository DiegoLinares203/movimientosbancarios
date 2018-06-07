package com.diego.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diego.app.models.dao.IMovimientoDAO;
import com.diego.app.models.entity.Movimiento;
import com.diego.app.service.inte.IMovimientoService;

@Service
public class MovimientoService implements IMovimientoService {

	@Autowired
	private IMovimientoDAO movimientoDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<Movimiento> findAll() {
		return movimientoDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Movimiento movimiento) {
		movimientoDAO.save(movimiento);
	}

	@Override
	@Transactional(readOnly=true)
	public Movimiento findOne(Long id) {
		return movimientoDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		movimientoDAO.deleteById(id);
	}

	@Override
	public String Movimiento(Movimiento movimiento) {
		String mensaje="";
		Double monto = movimiento.getMonto();
		Double saldo = movimiento.getCuentabancaria().getSaldo();
		String clave = movimiento.getClave();
		
		if(movimiento.getTipo() == "Deposito"){
			movimiento.getCuentabancaria().setSaldo(saldo + monto);
			save(movimiento);
			mensaje = "Deposito Registrado";
		}
		
		if(movimiento.getTipo() == "Retiro"){
			if(monto > saldo)
				mensaje = "La cantidad a retirar es mayor al saldo que actualmente tiene";
			
			else {
				
				if(clave == movimiento.getCuentabancaria().getClave()) {
					movimiento.getCuentabancaria().setSaldo(saldo - monto);
					save(movimiento);
					mensaje = "Retiro Registrado";
				}
					
				else 
					//Implementar los 3 intentos
					mensaje = "Clave Incorrecta";
			}
		}
		
		return mensaje;
	}
}
