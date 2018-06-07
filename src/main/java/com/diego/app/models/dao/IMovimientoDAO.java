package com.diego.app.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diego.app.models.entity.Movimiento;

@Repository
public interface IMovimientoDAO extends JpaRepository<Movimiento, Long> {

}
