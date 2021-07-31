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

import com.davicarv.choperia.domain.Funcionario;
import com.davicarv.choperia.service.FuncionarioService;

@Controller
@RequestMapping(path="/funcionarios")
public class FuncionarioViewController {
	@Autowired
	private FuncionarioService service;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("funcionarios", service.findAll()); 
		return "funcionarios";
	}
	
	@GetMapping(path = "/cadastro")
	public String cadastro(Model model) {
		model.addAttribute("funcionarios", new Funcionario());
		return "formfuncionario";
	}
	
	@GetMapping(path="/save")
	public String save(@Valid @ModelAttribute Funcionario funcionario, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getFieldErrors());
			
		}
		funcionario.setId(null);
		try {
			service.save(funcionario);
		}catch (Exception e) {
			model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
			
			return "formfuncionario";
		}
		
		model.addAttribute("msgSucesso", "funcionario cadastrado com sucesso");
		model.addAttribute("funcionario", new Funcionario());
		return "formfuncionario";
	}
	
	@GetMapping(path = "/alterar")
	public String alterar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("funcionario", service.findById(id));
		return "formFuncionario";
	}
	
	@GetMapping(path="/{id}/deletar")
	public String deletar(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/funcionarios";
	}
}
