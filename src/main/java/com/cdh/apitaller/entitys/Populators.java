package com.cdh.apitaller.entitys;

import com.cdh.apitaller.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Populators implements CommandLineRunner {

    private final UserRepository userRepository;
    private final VehiculoRepository vehiculoRepository;
    private final CitaRepository citaRepository;
    private final CompaniaAseguradoraRepository aseguradoraRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final PiezaRepository piezaRepository;
    private final ReparacionRepository reparacionRepository;

    public Populators(UserRepository userRepository,
                      VehiculoRepository vehiculoRepository,
                      CitaRepository citaRepository,
                      CompaniaAseguradoraRepository aseguradoraRepository,
                      TrabajadorRepository trabajadorRepository,
                      PiezaRepository piezaRepository,
                      ReparacionRepository reparacionRepository) {
        this.userRepository = userRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.citaRepository = citaRepository;
        this.aseguradoraRepository = aseguradoraRepository;
        this.trabajadorRepository = trabajadorRepository;
        this.piezaRepository = piezaRepository;
        this.reparacionRepository = reparacionRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        // Borrar todo (opcional)
        reparacionRepository.deleteAll();
        piezaRepository.deleteAll();
        citaRepository.deleteAll();
        vehiculoRepository.deleteAll();
        userRepository.deleteAll();
        trabajadorRepository.deleteAll();
        aseguradoraRepository.deleteAll();

        // Usuarios
        User user1 = new User();
        user1.setNombre("Ana");
        user1.setApellidos("López");
        user1.setDni("12345678A");
        user1.setTelefono(612345678);
        user1.setEmail("ana@example.com");
        user1.setDireccion("Calle Luna 1");
        user1.setCiudad("Madrid");
        user1.setProvincia("Madrid");
        user1.setCp("28001");
        user1.setPais("España");
        user1.setNombreUsuarioApp("analopez");
        user1.setPassword("pass123");

        User user2 = new User();
        user2.setNombre("Carlos");
        user2.setApellidos("Ruiz");
        user2.setDni("87654321B");
        user2.setTelefono(699123456);
        user2.setEmail("carlos@example.com");
        user2.setDireccion("Calle Sol 5");
        user2.setCiudad("Barcelona");
        user2.setProvincia("Barcelona");
        user2.setCp("08001");
        user2.setPais("España");
        user2.setNombreUsuarioApp("carlosr");
        user2.setPassword("pass456");

        userRepository.save(user1);
        userRepository.save(user2);

        // Compañías aseguradoras
        CompaniaAseguradora comp1 = new CompaniaAseguradora();
        comp1.setNombre("Mapfre");
        comp1.setTelefono("912345678");
        comp1.setEmail("info@mapfre.com");
        comp1.setConcertado(true);

        CompaniaAseguradora comp2 = new CompaniaAseguradora();
        comp2.setNombre("AXA");
        comp2.setTelefono("934567890");
        comp2.setEmail("contacto@axa.es");
        comp2.setConcertado(false);

        aseguradoraRepository.save(comp1);
        aseguradoraRepository.save(comp2);

        // Vehículos
        Vehiculo vehiculo1 = new Vehiculo();
        vehiculo1.setMarca("Toyota");
        vehiculo1.setModelo("Yaris");
        vehiculo1.setMatricula("1234ABC");
        vehiculo1.setColor("Rojo");
        vehiculo1.setNumeroBastidor("BAS123456789");
        vehiculo1.setPropietario(user1);
        vehiculo1.setCompaniaAseguradora(comp1);

        Vehiculo vehiculo2 = new Vehiculo();
        vehiculo2.setMarca("Ford");
        vehiculo2.setModelo("Focus");
        vehiculo2.setMatricula("5678DEF");
        vehiculo2.setColor("Azul");
        vehiculo2.setNumeroBastidor("FOC987654321");
        vehiculo2.setPropietario(user2);
        vehiculo2.setCompaniaAseguradora(comp2);

        vehiculoRepository.save(vehiculo1);
        vehiculoRepository.save(vehiculo2);

        // Trabajadores
        Trabajador trabajador1 = new Trabajador();
        trabajador1.setNombreCompleto("José Martínez");
        trabajador1.setCodigoEmpleado("EMP001");

        Trabajador trabajador2 = new Trabajador();
        trabajador2.setNombreCompleto("Laura Gómez");
        trabajador2.setCodigoEmpleado("EMP002");

        trabajadorRepository.save(trabajador1);
        trabajadorRepository.save(trabajador2);

        // Piezas
        Pieza pieza1 = new Pieza();
        pieza1.setNombre("Filtro de aceite");
        pieza1.setDescripcion("Filtro para aceite de motor");
        pieza1.setPrecio(15.0);
        pieza1.setStock(50);
        pieza1.setProveedor("Proveedor1");
        pieza1.setReferencia("REF001");

        Pieza pieza2 = new Pieza();
        pieza2.setNombre("Pastillas de freno");
        pieza2.setDescripcion("Juego de pastillas delanteras");
        pieza2.setPrecio(30.0);
        pieza2.setStock(20);
        pieza2.setProveedor("Proveedor2");
        pieza2.setReferencia("REF002");

        piezaRepository.save(pieza1);
        piezaRepository.save(pieza2);

        // Reparaciones
        Reparacion rep1 = new Reparacion();
        rep1.setDescripcion("Cambio de aceite");
        rep1.setTrabajador(trabajador1);
        rep1.setHoraInicio(LocalDateTime.now().minusDays(2));
        rep1.setHoraFin(LocalDateTime.now().minusDays(2).plusHours(1));
        rep1.addPieza(pieza1);

        Reparacion rep2 = new Reparacion();
        rep2.setDescripcion("Cambio de frenos");
        rep2.setTrabajador(trabajador2);
        rep2.setHoraInicio(LocalDateTime.now().minusDays(1));
        rep2.setHoraFin(LocalDateTime.now().minusDays(1).plusHours(2));
        rep2.addPieza(pieza2);


        reparacionRepository.save(rep1);
        reparacionRepository.save(rep2);

        // Citas
        Cita cita1 = new Cita();
        cita1.setUser(user1);
        cita1.setVehiculoCita(vehiculo1);
        cita1.setFecha(LocalDateTime.now().plusDays(1));

        Cita cita2 = new Cita();
        cita2.setUser(user2);
        cita2.setVehiculoCita(vehiculo2);
        cita2.setFecha(LocalDateTime.now().plusDays(3));

        citaRepository.save(cita1);
        citaRepository.save(cita2);
    }
}

