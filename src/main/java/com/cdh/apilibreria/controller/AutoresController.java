package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.Autor;
import com.cdh.apilibreria.repository.AutoresRepository;
import com.cdh.apilibreria.unimplemented.controller.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class AutoresController implements GenericController<Autor> {

    @Autowired
    private AutoresRepository autorRepository;

    @GetMapping("/api/autores")
    @ResponseBody
    @Override
    public ResponseEntity<List<Autor>> get() {
        return ResponseEntity.ok(autorRepository.findAll());
    }

    @PostMapping("/api/autores")
    @Override
    public ResponseEntity<Autor> post(@RequestBody Autor autor) {
        if (autorRepository.existsById(autor.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(autorRepository.save(autor));
    }
    @PutMapping("/api/autores")
    @Override
    public ResponseEntity<Autor> put(@RequestBody Autor autor) {
        if (autorRepository.existsById(autor.getId())) {
            autorRepository.save(autor);
            return ResponseEntity.ok(autor);
        }
        return ResponseEntity.notFound().build();
    }

    
    @DeleteMapping("/api/autores/{id}")
    @Override
    public ResponseEntity<Autor> delete(@PathVariable int id) {
        if (autorRepository.existsById(id)) {
        	 autorRepository.deleteById(id);
        	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}