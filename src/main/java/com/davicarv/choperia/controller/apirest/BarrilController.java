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
import org.springframework.web.bind.annotation.RestController;

import com.davicarv.choperia.domain.Barril;
import com.davicarv.choperia.service.BarrilService;

@RestController
@RequestMapping(path = "/apirest/barris")
public class BarrilController {
	@Autowired
	private BarrilService service;

	@GetMapping
	public ResponseEntity getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity getOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@PostMapping
	public ResponseEntity save(@Valid @RequestBody Barril barril) {
		barril.setId(null);
		service.save(barril);

		return ResponseEntity.status(HttpStatus.CREATED).body(barril);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody Barril barril) {
		barril.setId(id);
		service.update(barril);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
