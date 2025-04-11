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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdh.apilibreria.model.Autor;
import com.cdh.apilibreria.model.Pedido;
import com.cdh.apilibreria.repository.AutoresRepository;
import com.cdh.apilibreria.repository.PedidoRepository;
import com.cdh.apilibreria.unimplemented.controller.GenericController;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
public class PedidoController implements GenericController<Pedido> {
	
	 @Autowired
	    private PedidoRepository pedidoRepository;

	    @GetMapping("/api/pedido")
	    @Override
	    public ResponseEntity<List<Pedido>> get() {
	        return ResponseEntity.ok(pedidoRepository.findAll());
	    }

	    @PostMapping("/api/pedido")
	    @Override
	    public ResponseEntity<Pedido> post(@RequestBody Pedido pedido) {
	        if (pedidoRepository.existsById(pedido.getId())) {
	            return ResponseEntity.badRequest().build();
	        }

	        return ResponseEntity.ok(pedidoRepository.save(pedido));
	    }
	    @PutMapping("/api/pedido")
	    @Override
	    public ResponseEntity<Pedido> put(@RequestBody Pedido pedido) {
	        if (pedidoRepository.existsById(pedido.getId())) {
	        	pedidoRepository.save(pedido);
	            return ResponseEntity.ok(pedido);
	        }
	        return ResponseEntity.notFound().build();
	    }

	    
	    @DeleteMapping("/api/pedido/{id}")
	    @Override
	    public ResponseEntity<Pedido> delete(@PathVariable int id) {
	        if (pedidoRepository.existsById(id)) {
	        	pedidoRepository.deleteById(id);
	        	 return ResponseEntity.ok().build();
	        }
	        return ResponseEntity.notFound().build();
	    }
	}


