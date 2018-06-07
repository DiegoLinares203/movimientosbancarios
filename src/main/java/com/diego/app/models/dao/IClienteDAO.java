package com.diego.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diego.app.models.entity.Cliente;

@Repository
public interface IClienteDAO extends JpaRepository<Cliente, Long>{

}
