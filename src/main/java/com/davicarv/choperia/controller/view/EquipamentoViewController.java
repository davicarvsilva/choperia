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

import com.davicarv.choperia.domain.Equipamento;
import com.davicarv.choperia.service.EquipamentoService;

@Controller
@RequestMapping(path="/equipamentos")
public class EquipamentoViewController {
	@Autowired
	private EquipamentoService service;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("equipamentos", service.findAll()); 
		return "equipamentos";
	}
	
	@GetMapping(path = "/cadastro")
	public String cadastro(Model model) {
		model.addAttribute("equipamentos", new Equipamento());
		return "formequipamento";
	}
	
	@GetMapping(path="/save")
	public String save(@Valid @ModelAttribute Equipamento equipamento, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getFieldErrors());
			
		}
		equipamento.setId(null);
		try {
			service.save(equipamento);
		}catch (Exception e) {
			model.addAttribute("msgErros", new ObjectError("equipamento", e.getMessage()));
			
			return "formequipamento";
		}
		
		model.addAttribute("msgSucesso", "Equipamento cadastrado com sucesso");
		model.addAttribute("equipamento", new Equipamento());
		return "formequipamento";
	}
	
	@GetMapping(path = "/alterar")
	public String alterar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("equipamento", service.findById(id));
		return "formEquipamento";
	}
	
	@GetMapping(path="/{id}/deletar")
	public String deletar(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/equipamentos";
	}
}
