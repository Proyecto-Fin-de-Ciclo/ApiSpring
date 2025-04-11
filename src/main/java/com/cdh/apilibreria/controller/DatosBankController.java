package com.cdh.apilibreria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cdh.apilibreria.model.DatosBank;
import com.cdh.apilibreria.repository.DatosBankRepository;
import com.cdh.apilibreria.unimplemented.controller.GenericController;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class DatosBankController implements GenericController<DatosBank> {
	@Autowired
	DatosBankRepository datosBankRepository;
	
	@GetMapping("/api/datosBank")
	@Override
	public ResponseEntity<List<DatosBank>> get() {
		return ResponseEntity.ok(datosBankRepository.findAll());
	}
	
	@PostMapping("/api/datosBank")
	@Override
	public ResponseEntity<DatosBank> post(@RequestBody DatosBank datosBank) {
		if(datosBankRepository.existsById(datosBank.getId())) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(datosBankRepository.save(datosBank));
	}
	

	@PutMapping("/api/datosBank")
	@Override
	public ResponseEntity<DatosBank> put(@RequestBody DatosBank datosBank) {
		if(datosBankRepository.existsById(datosBank.getId())) {
			datosBankRepository.save(datosBank);
		return ResponseEntity.ok(datosBank);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/api/datosBank")
	@Override
	public ResponseEntity<DatosBank> delete(@PathVariable int id) {
		if(datosBankRepository.existsById(id)) {
			datosBankRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
