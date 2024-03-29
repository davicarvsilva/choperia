package com.davicarv.choperia.service;

import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.davicarv.choperia.domain.Funcionario;
import com.davicarv.choperia.domain.Permissao;
import com.davicarv.choperia.exception.NotFoundException;
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
			throw new NotFoundException("Funcionário não encontrado");
		} else {
			return result.get();
		}
	}

	public Funcionario save(Funcionario b) {
		verificaEmailCadastrado(b.getEmail());
		removePermissoesNulas(b);
		
		try {
			
			b.setSenha(new BCryptPasswordEncoder().encode(b.getSenha()));
			
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
		else {
			throw new RuntimeException("Funcionário está ligado à Ordem de Serviço");
		}
	}

	public Funcionario update(Funcionario funcionario, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
		// Verifica se funcionário já existe
		Funcionario obj = findById(funcionario.getId());
		// Verifica alteração de senha
		alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
		
		removePermissoesNulas(funcionario);

		try {
			funcionario.setCpfCnpj(obj.getCpfCnpj());
			funcionario.setEmail(obj.getEmail());
			funcionario.setSenha(obj.getSenha());
			return repo.save(funcionario);
		} catch (Exception e) {
			Throwable t = e;
			while(t.getCause() != null) {
				t = t.getCause();
				if(t instanceof ConstraintViolationException) {
					throw((ConstraintViolationException) t);
				}
			}
			
			throw new RuntimeException("Falha ao atualizar funcionário ");
		}
	}

	private void alterarSenha(Funcionario obj, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
		if (!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()) {
			if (!senhaAtual.equals(obj.getSenha())) {
				throw new RuntimeException("Senha atual está incorreta");
			}
			if (!novaSenha.equals(confirmarNovaSenha)) {
				throw new RuntimeException("Nova senha e confirmar nova senha não conferem");
			}

			obj.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
		}
	}
	
	private void verificaEmailCadastrado(String email) {
		Funcionario funcionario = repo.findByEmail(email);
		if(funcionario == null) {
			throw new RuntimeException("Email já cadastrado");
		}
	}
	
	public void removePermissoesNulas(Funcionario f) {
		f.getPermissoes().removeIf((Permissao p)->{
			return p.getId() == null;
		});
		
		if(f.getPermissoes().isEmpty()) {
			throw new RuntimeException("Funcionário deve conter no mínimo uma permissão");
		}
	}
}
