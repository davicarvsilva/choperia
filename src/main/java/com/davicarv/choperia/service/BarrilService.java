package com.davicarv.choperia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davicarv.choperia.domain.Barril;
import com.davicarv.choperia.repository.BarrilRepository;

@Service
public class BarrilService {
	@Autowired
	private BarrilRepository repo;

	public List<Barril> findAll() {
		return repo.findAll();
	}

	public Barril findById(Long id) {
		Optional<Barril> result = repo.findById(id);
		if (result.isEmpty()) {
			throw new RuntimeException("Barril não encontrado");
		} else {
			return result.get();
		}
	}

	public Barril save(Barril b) {
		try {
			return repo.save(b);
		} catch (Exception e) {
			throw new RuntimeException("falha ao salvar Barril");
		}
	}

	public Barril update(Barril b) {
		Barril obj = findById(b.getId());
		try {
			return repo.save(b);
		} catch (Exception e) {
			throw new RuntimeException("Falha ao salvar Barril");
		}
	}

	public void delete(Long id) {
		Barril obj = findById(id);
		if (obj.getOrdemServico() == null) {
			try {
				repo.delete(obj);
			} catch (Exception e) {
				throw new RuntimeException("Falha ao apagar Barril");
			}
		}
	}
}
