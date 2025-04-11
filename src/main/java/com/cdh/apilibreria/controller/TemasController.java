package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.Temas;
import com.cdh.apilibreria.repository.TemasRepository;
import com.cdh.apilibreria.unimplemented.controller.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class TemasController implements GenericController<Temas> {

    @Autowired
    private TemasRepository temaRepository;

    @GetMapping("/api/temas")
    @ResponseBody
    @Override
    public ResponseEntity<List<Temas>> get() {
        return ResponseEntity.ok(temaRepository.findAll());
    }

    @PostMapping("/api/temas")
    @Override
    public ResponseEntity<Temas> post(@RequestBody Temas temas) {
        if (temaRepository.existsById(temas.getId())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(temaRepository.save(temas));
    }

    @PutMapping("/api/temas")
    @Override
    public ResponseEntity<Temas> put(@RequestBody Temas temas) {
        if (temaRepository.existsById(temas.getId())) {
            temaRepository.save(temas); // Actualizar el registro sin eliminar
            return ResponseEntity.ok(temas);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/api/temas/{id}")
    @Override
    public ResponseEntity<Temas> delete(@PathVariable int id) {
        if (temaRepository.existsById(id)) {
        	temaRepository.deleteById(id);
       	 return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}