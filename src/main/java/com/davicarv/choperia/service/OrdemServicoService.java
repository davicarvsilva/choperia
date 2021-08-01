package com.davicarv.choperia.service;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davicarv.choperia.domain.Equipamento;
import com.davicarv.choperia.domain.OrdemServico;
import com.davicarv.choperia.domain.StatusEquipamentoEnum;
import com.davicarv.choperia.exception.NotFoundException;
import com.davicarv.choperia.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
	@Autowired
	private OrdemServicoRepository repo;

	public List<OrdemServico> findAll() {
		return repo.findAll();
	}

	public OrdemServico findById(Long id) {
		Optional<OrdemServico> result = repo.findById(id);
		if (result.isEmpty()) {
			throw new NotFoundException("Ordem de Serviço não encontrado");
		} else {
			return result.get();
		}
	}

	public OrdemServico save(OrdemServico os) {
		List<Equipamento> listaEquipamentos = os.getEquipamentos();
		verificaStatusEquipamento(listaEquipamentos);
		
		try {
			return repo.save(os);
		} catch (Exception e) {
			throw new RuntimeException("falha ao salvar Ordem de Serviço");
		}
	}

	public OrdemServico update(OrdemServico os) {
		OrdemServico obj = findById(os.getId());
		
		List<Equipamento> listaEquipamentos = obj.getEquipamentos();
		verificaStatusEquipamento(listaEquipamentos);
		
		try {
			return repo.save(os);
		} catch (Exception e) {
			Throwable t = e;
			while(t.getCause() != null) {
				t = t.getCause();
				if(t instanceof ConstraintViolationException) {
					throw((ConstraintViolationException) t);
				}
			}
			throw new RuntimeException("Falha ao salvar Ordem de Serviço");
		}
	}

	public void delete(Long id) {
		OrdemServico obj = findById(id);
		try {
			repo.delete(obj);
		} catch (Exception e) {
			throw new RuntimeException("Falha ao apagar ordem de serviço");
		}
	}
	
	private void verificaStatusEquipamento(List<Equipamento> listaEquipamentos) {
		for (Equipamento equipamento : listaEquipamentos) {
			if(equipamento.getStatus() == StatusEquipamentoEnum.ALOCADO) {
				throw new RuntimeException("Um dos equipamentos escolhidos já está alocado");
			}
		}
	}
}
