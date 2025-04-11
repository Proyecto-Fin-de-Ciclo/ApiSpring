package com.cdh.apilibreria.controller;

import com.cdh.apilibreria.model.Libro;
import com.cdh.apilibreria.repository.LibroRepository;
import com.cdh.apilibreria.unimplemented.controller.GenericController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class LibrosController implements GenericController<Libro> {

    @Autowired
    private LibroRepository libroRepository;

    @RequestMapping(path = "/api/libros", method = RequestMethod.GET)
    @ResponseBody
    @Override
    public ResponseEntity<List<Libro>> get() {
        return ResponseEntity.ok(libroRepository.findAll());

    }
    @RequestMapping(path = "/api/libros", method = RequestMethod.POST)
    @Override
    public ResponseEntity<Libro> post(@RequestBody Libro libro) {
        return ResponseEntity.ok(libroRepository.save(libro));
    }

    @PutMapping("/api/libros")
    @Override
    public ResponseEntity<Libro> put(@RequestBody Libro libro) {
        if (libroRepository.existsById(libro.getId())) {
            libroRepository.save(libro);
            return ResponseEntity.ok(libro);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/api/libros/{id}")
    @Override
    public ResponseEntity<Libro> delete(@PathVariable int id) {
        if (libroRepository.existsById(id)) {
            libroRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
