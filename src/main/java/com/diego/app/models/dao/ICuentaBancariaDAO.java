package com.diego.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diego.app.models.entity.CuentaBancaria;

@Repository
public interface ICuentaBancariaDAO extends JpaRepository<CuentaBancaria, Long> {

}
