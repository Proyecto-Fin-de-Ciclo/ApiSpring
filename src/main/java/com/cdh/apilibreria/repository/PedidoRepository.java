package com.cdh.apilibreria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cdh.apilibreria.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

}
