package com.davicarv.choperia.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.davicarv.choperia.domain.OrdemServico;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {
	public List<OrdemServico> findByDataCriacao(Calendar dataCriacao);
	@Query("SELECT DISTINCT(os) FROM OrdemServico os WHERE (os.dataEntrega BETWEEN:dataEntrega AND :dataDevolucao)"
	+ "OR (os.dataDevolucao BETWEEN :dataEntrega AND :dataDevolucao)")
	public List<OrdemServico> findOrdemServicoEntreDatas(
			@Param("dataEntrega") Calendar dataEntrega, 
			@Param("dataDevolucao") Calendar dataDevolucao);
	
	public List<OrdemServico> findByClienteId(Long clienteId);
	//public List<OrdemServico> findByFuncionarios(Long funcionarioId);
}
