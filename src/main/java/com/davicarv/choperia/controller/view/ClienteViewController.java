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

import com.davicarv.choperia.domain.Cliente;
import com.davicarv.choperia.service.ClienteService;

@Controller
@RequestMapping(path="/clientes")
public class ClienteViewController {
	@Autowired
	private ClienteService service;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("clientes", service.findAll()); 
		return "clientes";
	}
	
	@GetMapping(path = "/cadastro")
	public String cadastro(Model model) {
		model.addAttribute("clientes", new Cliente());
		return "formcliente";
	}
	
	@GetMapping(path="/save")
	public String save(@Valid @ModelAttribute Cliente cliente, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getFieldErrors());
			
		}
		cliente.setId(null);
		try {
			service.save(cliente);
		}catch (Exception e) {
			model.addAttribute("msgErros", new ObjectError("cliente", e.getMessage()));
			
			return "formCliente";
		}
		
		model.addAttribute("msgSucesso", "Cliente cadastrado com sucesso");
		model.addAttribute("cliente", new Cliente());
		return "formCliente";
	}
	
	@GetMapping(path = "/alterar")
	public String alterar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("cliente", service.findById(id));
		return "formCliente";
	}
	
	@GetMapping(path="/{id}/deletar")
	public String deletar(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/clientes";
	}
}
