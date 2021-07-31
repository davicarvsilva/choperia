package com.davicarv.choperia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.davicarv.choperia.domain.Equipamento;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>{
	public List<Equipamento> findByCodigoInterno(String codigoInterno);
	//public List<Equipamento> findByStatusEquipamento(int statusEquipamento);
}
