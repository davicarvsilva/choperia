package com.davicarv.choperia.controller.view;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.davicarv.choperia.domain.Funcionario;
import com.davicarv.choperia.repository.PermissaoRepository;
import com.davicarv.choperia.service.FuncionarioService;

@Controller
@RequestMapping(path = "/funcionarios")
public class FuncionarioViewController {
	@Autowired
	private FuncionarioService service;
	
	@Autowired
	private PermissaoRepository permissaoRepo;

	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("funcionarios", service.findAll());
		return "funcionarios";
	}

	@GetMapping(path = "/cadastro")
	public String cadastro(Model model) {
		model.addAttribute("funcionarios", new Funcionario());
		model.addAttribute("permissoes", new Funcionario());
		return "formFuncionario";
	}

	@GetMapping(path = "/save")
	public String save(@Valid @ModelAttribute Funcionario funcionario, 
			BindingResult result, 
			@RequestParam("confirmarSenha") String confirmarSenha,
			Model model) {
		
		if (result.hasErrors()) {
			model.addAttribute("msgErros", result.getFieldErrors());
		}
		
		if(funcionario.getSenha().equals(confirmarSenha)) {
			model.addAttribute("msgErrors", new ObjectError("funcionario", "Campos Senha e Confirmar Senha devem ser iguais"));
		}
		
		funcionario.setId(null);
		try {
			service.save(funcionario);
		} catch (Exception e) {
			model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));

			return "formFuncionario";
		}

		model.addAttribute("msgSucesso", "funcionario cadastrado com sucesso");
		model.addAttribute("funcionario", new Funcionario());
		return "formFuncionario";
	}

	@GetMapping(path = "/alterar")
	public String alterar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("funcionario", service.findById(id));
		model.addAttribute("permissoes", new Funcionario());
		return "formFuncionario";
	}

	@GetMapping(path = "/{id}/deletar")
	public String deletar(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/funcionarios";
	}
}
