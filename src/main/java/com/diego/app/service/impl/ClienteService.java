package com.diego.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.diego.app.models.dao.IClienteDAO;
import com.diego.app.models.entity.Cliente;
import com.diego.app.service.inte.IClienteService;

@Service
public class ClienteService implements IClienteService {
	
	@Autowired
	private IClienteDAO clienteDAO;

	@Override
	@Transactional(readOnly=true)
	public List<Cliente> findAll() {
		return clienteDAO.findAll();
	}

	@Override
	@Transactional
	public void save(Cliente cliente) {
		clienteDAO.save(cliente);
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente findOne(Long id) {
		return clienteDAO.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clienteDAO.deleteById(id);
	}

}
