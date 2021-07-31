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

import com.davicarv.choperia.domain.OrdemServico;
import com.davicarv.choperia.service.OrdemServicoService;

@Controller
@RequestMapping(path="/ordemServicos")
public class OrdemServicoViewController {
	@Autowired
	private OrdemServicoService service;
	
	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("ordemServicos", service.findAll()); 
		return "ordemServicos";
	}
	
	@GetMapping(path = "/cadastro")
	public String cadastro(Model model) {
		model.addAttribute("ordemServicos", new OrdemServico());
		return "formordemServico";
	}
	
	@GetMapping(path="/save")
	public String save(@Valid @ModelAttribute OrdemServico ordemServico, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("msgErros", result.getFieldErrors());
			
		}
		ordemServico.setId(null);
		try {
			service.save(ordemServico);
		}catch (Exception e) {
			model.addAttribute("msgErros", new ObjectError("ordemServico", e.getMessage()));
			
			return "formordemServico";
		}
		
		model.addAttribute("msgSucesso", "Ordem de Servico cadastrada com sucesso");
		model.addAttribute("ordemServico", new OrdemServico());
		return "formordemServico";
	}
	
	@GetMapping(path = "/alterar")
	public String alterar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("ordemServico", service.findById(id));
		return "formordemServico";
	}
	
	@GetMapping(path="/{id}/deletar")
	public String deletar(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/ordensServico";
	}
}
