package com.diego.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diego.app.models.dao.ICuentaBancariaDAO;
import com.diego.app.models.entity.CuentaBancaria;
import com.diego.app.service.inte.ICuentaBancariaService;

@Service
public class CuentaBancariaService implements ICuentaBancariaService {

	@Autowired
	private ICuentaBancariaDAO cuentabancariaDAO;
	
	@Override
	@Transactional(readOnly=true)
	public List<CuentaBancaria> findAll() {
		return cuentabancariaDAO.findAll();
	}

	@Override
	@Transactional
	public void save(CuentaBancaria cuentaBancaria) {
		cuentabancariaDAO.save(cuentaBancaria);
	}

	@Override
	@Transactional(readOnly=true)
	public CuentaBancaria findOne(Long id) {
		return cuentabancariaDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		cuentabancariaDAO.deleteById(id);
	}

}
