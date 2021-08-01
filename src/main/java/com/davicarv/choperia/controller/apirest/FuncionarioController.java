package com.davicarv.choperia.controller.apirest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.davicarv.choperia.domain.Cliente;
import com.davicarv.choperia.domain.Funcionario;
import com.davicarv.choperia.service.FuncionarioService;

@RestController
@RequestMapping(path = "/apirest/funcionarios")
public class FuncionarioController {
	@Autowired
	private FuncionarioService service;

	@GetMapping
	public ResponseEntity getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity getOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PostMapping
	public ResponseEntity save(@Valid @RequestBody Funcionario funcionario) {
		funcionario.setId((Long) null);
		service.save(funcionario);

		return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
	}

	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Funcionario funcionario) {
		funcionario.setId(id);
		service.update(funcionario, "", "", "");
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping(path="/{id}/alterarSenha")
	public ResponseEntity alterarSenha(@PathVariable("id") Long id,
		@RequestParam(name = "senhaAtual", defaultValue = "", required = true) String senhaAtual, 
		@RequestParam(name = "novaSenha", defaultValue = "", required = true) String novaSenha, 
		@RequestParam(name = "confirmarNovaSenha", defaultValue = "", required = true) String confirmarNovaSenha) {
		
		Funcionario f = service.findById(id);
		service.update(f, senhaAtual, novaSenha, confirmarNovaSenha);
		return ResponseEntity.ok().build();
	}
}
