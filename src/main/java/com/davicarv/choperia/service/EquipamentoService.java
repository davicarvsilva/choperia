package com.davicarv.choperia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davicarv.choperia.domain.Equipamento;
import com.davicarv.choperia.repository.EquipamentoRepository;

@Service
public class EquipamentoService {
	@Autowired
	private EquipamentoRepository repo;
	
	public List<Equipamento> findAll(){
		return repo.findAll();
	}
	
	public Equipamento findById(Long id){
		Optional<Equipamento> result = repo.findById(id);
		if(result.isEmpty()) {
			throw new RuntimeException("Equipamento não encontrado");
		}
		else {
			return result.get(); 
		}
	}
	
	public Equipamento save(Equipamento b) {
		try {
			return repo.save(b);
		}
		catch (Exception e) {
			throw new RuntimeException("falha ao salvar Equipamento");
		}
	}
	
	public Equipamento update(Equipamento b) {
		Equipamento obj = findById(b.getId());
		try {
			return repo.save(b);
		}
		catch (Exception e) {
			throw new RuntimeException("Falha ao salvar Equipamento");
		}
	}
	
	public void delete(Long id) {
		Equipamento obj = findById(id);
		if(obj.getOrdensServico().isEmpty()){
			try {
				repo.delete(obj);
			}
			catch (Exception e) {
				throw new RuntimeException("Falha ao apagar Equipamento");
			}
		}
	}
}
