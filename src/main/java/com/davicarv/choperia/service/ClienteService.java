package com.davicarv.choperia.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.davicarv.choperia.domain.Cliente;
import com.davicarv.choperia.domain.Pessoa;
import com.davicarv.choperia.repository.ClienteRepository;
import com.mysql.cj.xdevapi.Result;

import jdk.jfr.events.ErrorThrownEvent;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repo;

	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public Cliente findById(Long id) {
		Optional<Cliente> result = repo.findById(id);
		if (result.isEmpty()) {
			throw new RuntimeException("Cliente não encontrado");
		} else {
			return result.get();
		}
	}

	public Cliente save(Cliente b, MultipartFile file) {
		verificaEmailCadastrado(b.getEmail());
		
		try {
			return repo.save(b);
		} catch (Exception e) {
			throw new RuntimeException("falha ao salvar cliente");
		}
	}

	public Cliente update(Cliente b) {
		Cliente obj = findById(b.getId());
		try {
			return repo.save(b);
		} catch (Exception e) {
			throw new RuntimeException("Falha ao salvar cliente");
		}
	}

	public void delete(Long id) {
		Cliente obj = findById(id);
		if (obj.getOrdensServico().isEmpty()) {
			try {
				repo.delete(obj);
			} catch (Exception e) {
				throw new RuntimeException("Falha ao apagar cliente");
			}
		}
		else {
			throw new RuntimeException("Cliente está ligado à Ordem de Serviço");
		}
	}

	public Cliente update(Cliente cliente, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
		// Verifica se cliente já existe
		Cliente obj = findById(cliente.getId());
		// Verifica alteração de senha
		alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);

		try {
			cliente.setCpfOuCnpj(cliente.getCpfOuCnpj());
			cliente.setEmail(cliente.getEmail());
			return repo.save(cliente);
		} catch (Exception e) {
			throw new RuntimeException("Falha ao atualizar o Cliente ");
		}
	}

	private void alterarSenha(Cliente obj, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
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
	
	private void verificaEmailCadastrado(String email) {
		List<Pessoa> listaPessoas = repo.findByEmail(email);
		if(!listaPessoas.isEmpty()) {
			throw new RuntimeException("Email já cadastrado");
		}
	}
	
	/*
	private void salvarArquivo(MultipartFile file, String novoNome) {
		if(file.getContentType().equals(MediaType.APPLICATION_PDF_VALUE	)) {
			Path dest = Paths.get("uploads", novoNome);
			file.transferTo(dest);
		}
	}
	*/
}
