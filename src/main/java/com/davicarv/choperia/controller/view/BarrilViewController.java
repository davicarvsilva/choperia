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

import com.davicarv.choperia.domain.Barril;
import com.davicarv.choperia.service.BarrilService;

@Controller
@RequestMapping(path = "/barris")
public class BarrilViewController {
	@Autowired
	private BarrilService service;

	@GetMapping
	public String getAll(Model model) {
		model.addAttribute("barris", service.findAll());
		return "barris";
	}

	@GetMapping(path = "/barril")
	public String cadastro(Model model) {
		model.addAttribute("barril", new Barril());
		return "formBarril";
	}

	@GetMapping(path = "/save")
	public String save(@Valid @ModelAttribute Barril barril, BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("msgErros", result.getFieldErrors());

		}
		
		barril.setId(null);
		
		try {
			service.save(barril);
		} catch (Exception e) {
			model.addAttribute("msgErros", new ObjectError("Barril", e.getMessage()));

			return "formBarril";
		}

		model.addAttribute("msgSucesso", "Barril cadastrado com sucesso");
		model.addAttribute("barril", new Barril());
		return "formBarril";
	}

	@GetMapping(path = "/alterar")
	public String alterar(@PathVariable("id") Long id, Model model) {
		model.addAttribute("barril", service.findById(id));
		return "formBarril";
	}

	@GetMapping(path = "/{id}/deletar")
	public String deletar(@PathVariable("id") Long id) {
		service.delete(id);
		return "redirect:/barris";
	}
}
