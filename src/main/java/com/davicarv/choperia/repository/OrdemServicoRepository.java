package com.davicarv.choperia.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.davicarv.choperia.domain.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long>{
	public List<OrdemServico> findByDataCriacao(Calendar dataCriacao);
	//@Query("SELECT DISTINCT(r) FROM Reserva r WHERE (r.dataEntrega BETWEEN :dataEntrega AND :dataDevolucao)"
	//		+ "OR (r.dataDevolucao BETWEEN :dataEntrega AND :dataDevolucao)")
	//public List<OrdemServico> findOrdemServicoEntreDatas(Calendar dataEntrega, Calendar dataDevolucao);
}
