package com.cdh.apitaller.repository;

import com.cdh.apitaller.entitys.Presupuesto;
import com.cdh.apitaller.entitys.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresupuestoRepository extends JpaRepository<Presupuesto, Long> {
    boolean existsByVehiculoPropietarioIdAndVehiculoIdAndAceptadoTrue(Long userId, Long vehiculoId);
    List<Presupuesto> findAllByAceptadoTrue();
    Optional<Presupuesto> findByVehiculoAndAceptadoFalse(Vehiculo vehiculo);

}
