package com.davicarv.choperia.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davicarv.choperia.domain.Cliente;
import com.davicarv.choperia.domain.Funcionario;
import com.davicarv.choperia.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	@Autowired
	private FuncionarioRepository repo;

	public List<Funcionario> findAll() {
		return repo.findAll();
	}

	public Funcionario findById(Long id) {
		Optional<Funcionario> result = repo.findById(id);
		if (result.isEmpty()) {
			throw new RuntimeException("Funcionário não encontrado");
		} else {
			return result.get();
		}
	}

	public Funcionario save(Funcionario b) {
		try {
			return repo.save(b);
		} catch (Exception e) {
			throw new RuntimeException("falha ao salvar funcionário");
		}
	}

	public Funcionario update(Funcionario b) {
		Funcionario obj = findById(b.getId());
		try {
			return repo.save(b);
		} catch (Exception e) {
			throw new RuntimeException("Falha ao salvar funcionário");
		}
	}

	public void delete(Long id) {
		Funcionario obj = findById(id);
		if (obj.getOrdensServico().isEmpty()) {
			try {
				repo.delete(obj);
			} catch (Exception e) {
				throw new RuntimeException("Falha ao apagar funcionário");
			}
		}
	}

	public Funcionario update(Funcionario funcionario, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
		// Verifica se funcionário já existe
		Funcionario obj = findById(funcionario.getId());
		// Verifica alteração de senha
		alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);

		try {
			funcionario.setCpfOuCnpj(funcionario.getCpfOuCnpj());
			funcionario.setEmail(funcionario.getEmail());
			return repo.save(funcionario);
		} catch (Exception e) {
			throw new RuntimeException("Falha ao atualizar o Cliente ");
		}
	}

	private void alterarSenha(Funcionario obj, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
		if (!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()) {
			if (!senhaAtual.equals(obj.getUsuario().getSenha())) {
				throw new RuntimeException("Senha atual está incorreta");
			}
			if (!novaSenha.equals(confirmarNovaSenha)) {
				throw new RuntimeException("Nova senha e confirmar nova senha não conferem");
			}

			obj.getUsuario().setSenha(novaSenha);
		}
	}
}
