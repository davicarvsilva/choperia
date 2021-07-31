package com.davicarv.choperia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davicarv.choperia.domain.Funcionario;
import com.davicarv.choperia.domain.OrdemServico;
import com.davicarv.choperia.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
	@Autowired
	private OrdemServicoRepository repo;
	
	public List<OrdemServico> findAll(){
		return repo.findAll();
	}
	
	public OrdemServico findById(Long id){
		Optional<OrdemServico> result = repo.findById(id);
		if(result.isEmpty()) {
			throw new RuntimeException("Ordem de Serviço não encontrado");
		}
		else {
			return result.get(); 
		}
	}
	
	public OrdemServico save(OrdemServico b) {
		try {
			return repo.save(b);
		}
		catch (Exception e) {
			throw new RuntimeException("falha ao salvar Ordem de Serviço");
		}
	}
	
	public OrdemServico update(OrdemServico b) {
		OrdemServico obj = findById(b.getId());
		try {
			return repo.save(b);
		}
		catch (Exception e) {
			throw new RuntimeException("Falha ao salvar Ordem de Serviço");
		}
	}
	
	public void delete(Long id) {
		OrdemServico obj = findById(id);
		try {
			repo.delete(obj);
		}
		catch (Exception e) {
			throw new RuntimeException("Falha ao apagar ordem de serviço");
		}
		
	}
}
