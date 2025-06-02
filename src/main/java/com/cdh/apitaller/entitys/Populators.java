package com.cdh.apitaller.entitys;

import com.cdh.apitaller.enums.EstadoReparacion;
import com.cdh.apitaller.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    private final OrdenDeTrabajoRepository ordenDeTrabajoRepository;

    public Populators(UserRepository userRepository, VehiculoRepository vehiculoRepository, CitaRepository citaRepository, CompaniaAseguradoraRepository aseguradoraRepository, TrabajadorRepository trabajadorRepository, PiezaRepository piezaRepository, ReparacionRepository reparacionRepository, OrdenDeTrabajoRepository ordenDeTrabajoRepository) {
        this.userRepository = userRepository;
        this.vehiculoRepository = vehiculoRepository;
        this.citaRepository = citaRepository;
        this.aseguradoraRepository = aseguradoraRepository;
        this.trabajadorRepository = trabajadorRepository;
        this.piezaRepository = piezaRepository;
        this.reparacionRepository = reparacionRepository;
        this.ordenDeTrabajoRepository = ordenDeTrabajoRepository;
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

        User user3 = new User();
        user3.setNombre("admin");
        user3.setApellidos("Ruiz");
        user3.setDni("87654322B");
        user3.setTelefono(699123453);
        user3.setEmail("admin@example.com");
        user3.setDireccion("Calle Sol 5");
        user3.setCiudad("Barcelona");
        user3.setProvincia("Barcelona");
        user3.setCp("08001");
        user3.setPais("España");
        user3.setNombreUsuarioApp("admin");
        user3.setPassword("admin");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        CompaniaAseguradora comp1 = new CompaniaAseguradora("Mapfre", "912345678", "info@mapfre.com", true);
        CompaniaAseguradora comp2 = new CompaniaAseguradora("AXA", "934567890", "contacto@axa.es", false);
        CompaniaAseguradora comp3 = new CompaniaAseguradora("Línea Directa", "919191919", "clientes@lineadirecta.com", true);
        CompaniaAseguradora comp4 = new CompaniaAseguradora("Mutua Madrileña", "913001122", "info@mutua.es", true);
        CompaniaAseguradora comp5 = new CompaniaAseguradora("Pelayo", "918273645", "atencion@pelayo.com", true);
        CompaniaAseguradora comp6 = new CompaniaAseguradora("Reale Seguros", "916789123", "clientes@reale.es", false);
        CompaniaAseguradora comp7 = new CompaniaAseguradora("Seguros Bilbao", "944123456", "info@segurosbilbao.com", false);
        CompaniaAseguradora comp8 = new CompaniaAseguradora("Fiatc Seguros", "933567890", "fiatc@fiatc.es", true);
        CompaniaAseguradora comp9 = new CompaniaAseguradora("Catalana Occidente", "935678123", "contacto@catalanaoccidente.com", false);
        CompaniaAseguradora comp10 = new CompaniaAseguradora("Helvetia Seguros", "954123321", "info@helvetia.es", true);
        CompaniaAseguradora comp11 = new CompaniaAseguradora("Generali Seguros", "911223344", "clientes@generali.es", false);
        CompaniaAseguradora comp12 = new CompaniaAseguradora("Plus Ultra Seguros", "917654321", "info@plusultra.es", true);
        CompaniaAseguradora comp13 = new CompaniaAseguradora("Caser Seguros", "912222333", "clientes@caser.es", false);
        CompaniaAseguradora comp14 = new CompaniaAseguradora("Zurich Seguros España", "914444555", "info@zurich.es", false);
        CompaniaAseguradora comp15 = new CompaniaAseguradora("Mutua Tinerfeña", "922123456", "atencion@mutuatinerfena.com", false);
        CompaniaAseguradora comp16 = new CompaniaAseguradora("Asisa Auto", "900123123", "contacto@asisaauto.es", false);
        CompaniaAseguradora comp17 = new CompaniaAseguradora("Occident", "934112233", "clientes@occident.com", true);
        CompaniaAseguradora comp18 = new CompaniaAseguradora("Atlantis Seguros", "911876543", "info@atlantis.es", false);
        CompaniaAseguradora comp19 = new CompaniaAseguradora("Divina Seguros", "960123456", "info@divinaseguros.com", false);
        CompaniaAseguradora comp20 = new CompaniaAseguradora("Direct Seguros", "917777777", "atencion@directseguros.com", true);

        aseguradoraRepository.save(comp1);
        aseguradoraRepository.save(comp2);
        aseguradoraRepository.save(comp3);
        aseguradoraRepository.save(comp4);
        aseguradoraRepository.save(comp5);
        aseguradoraRepository.save(comp6);
        aseguradoraRepository.save(comp7);
        aseguradoraRepository.save(comp8);
        aseguradoraRepository.save(comp9);
        aseguradoraRepository.save(comp10);
        aseguradoraRepository.save(comp11);
        aseguradoraRepository.save(comp12);
        aseguradoraRepository.save(comp13);
        aseguradoraRepository.save(comp14);
        aseguradoraRepository.save(comp15);
        aseguradoraRepository.save(comp16);
        aseguradoraRepository.save(comp17);
        aseguradoraRepository.save(comp18);
        aseguradoraRepository.save(comp19);
        aseguradoraRepository.save(comp20);


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

        Vehiculo vehiculo3 = new Vehiculo();
        vehiculo3.setMarca("Ford");
        vehiculo3.setModelo("Focus");
        vehiculo3.setMatricula("1234BBB");
        vehiculo3.setColor("Azul");
        vehiculo3.setNumeroBastidor("FOC987654322");
        vehiculo3.setPropietario(user3);
        vehiculo3.setCompaniaAseguradora(comp2);

        vehiculoRepository.save(vehiculo1);
        vehiculoRepository.save(vehiculo2);
        vehiculoRepository.save(vehiculo3);

        // Trabajadores
        Trabajador trabajador1 = new Trabajador();
        trabajador1.setNombreCompleto("José Martínez");
        trabajador1.setCodigoEmpleado("EMP001");
        trabajador1.setImagen("JoseMartinez.jpg");
        trabajador1.setTelefono("612345678");
        trabajador1.setNombreUsuarioApp("jose Martinez");

        Trabajador trabajador2 = new Trabajador();
        trabajador2.setNombreCompleto("Laura Gómez");
        trabajador2.setCodigoEmpleado("EMP002");
        trabajador2.setImagen("LauraGomez.jpg");
        trabajador2.setTelefono("698765432");
        trabajador2.setNombreUsuarioApp("Laura Gomez");

        Trabajador trabajador3 = new Trabajador();
        trabajador3.setNombreCompleto("admin");
        trabajador3.setCodigoEmpleado("EMP003");
        trabajador3.setImagen("JoseMartinez.jpg");
        trabajador3.setTelefono("612345678");
        trabajador3.setNombreUsuarioApp("admin");

        trabajadorRepository.save(trabajador1);
        trabajadorRepository.save(trabajador2);
        trabajadorRepository.save(trabajador3);

        Pieza pinturaCapot  = new Pieza("Pintura capó", "Pintura acrílica para capó", 120.0, 10, "PPG", "PINT-CAPOT");
        Pieza pieza2 = new Pieza("Pintura maletero", "Pintura poliuretánica para baúl", 110.0, 8, "Sherwin-Williams", "PINT-MAL");
        Pieza pieza3 = new Pieza("Pintura paragolpes delantero", "Pintura para paragolpes plástico", 90.0, 12, "Glasurit", "PINT-PDEL");
        Pieza pinturaParagolpes  = new Pieza("Pintura paragolpes trasero", "Pintura para paragolpes trasero", 90.0, 12, "Glasurit", "PINT-PTRAS");
        Pieza pieza5 = new Pieza("Pintura lateral izquierdo", "Pintura para lateral izquierdo completo", 130.0, 6, "PPG", "PINT-LIZQ");
        Pieza pieza6 = new Pieza("Máscara de protección", "Máscara para protección durante el pintado", 15.0, 30, "3M", "SEG-MASC");

        Pieza manoObraPintura  = new Pieza("Lijado y preparación de superficie", "Trabajo de lijado previo a pintado", 50.0, 100, "Servicio Interno", "MO-LIJADO");
        Pieza manoObraChapa  = new Pieza("Mano de obra reparación abolladura", "Reparación manual de abolladuras leves", 80.0, 100, "Servicio Interno", "MO-ABOLL");
        Pieza pieza9 = new Pieza("Mano de obra cambio de paragolpes", "Cambio de paragolpes completo", 100.0, 100, "Servicio Interno", "MO-PARG");
        Pieza pieza10 = new Pieza("Barniz transparente", "Barniz final para protección de pintura", 40.0, 25, "Spies Hecker", "MAT-BARNIZ");

        Pieza masilla = new Pieza("Masilla poliéster", "Masilla para relleno de superficie", 25.0, 40, "Novol", "MAT-MASILLA");
        Pieza pieza12 = new Pieza("Anticorrosivo", "Base anticorrosiva para piezas metálicas", 20.0, 20, "Glasurit", "MAT-ANTICOR");
        Pieza pieza13 = new Pieza("Cabina de pintura (uso)", "Uso de cabina presurizada por unidad", 60.0, 50, "Servicio Interno", "CABINA-USO");
        Pieza pieza14 = new Pieza("Desmontaje de faros", "Mano de obra por desmontaje de ópticas", 30.0, 100, "Servicio Interno", "MO-FAROS");
        Pieza pieza15 = new Pieza("Pulido final", "Pulido final para terminación brillante", 35.0, 100, "Servicio Interno", "MO-PULIDO");
        Pieza pieza16 = new Pieza("Cambio guardabarros derecho", "Reemplazo completo de guardabarros", 85.0, 5, "Proveedor Guardabarros SA", "GDR-DER");

        piezaRepository.saveAll(Arrays.asList(
                pinturaCapot, pieza2, pieza3, pinturaParagolpes, pieza5,
                pieza6, manoObraPintura, manoObraChapa, pieza9, pieza10,
                masilla, pieza12, pieza13, pieza14, pieza15,
                pieza16
        ));


        // Reparación 1: Pintura y arreglo de capó
        Reparacion rep1 = new Reparacion();
        rep1.setDescripcion("Reparación de abolladura y pintura del capó");
        rep1.setTrabajador(trabajador1);
        rep1.setHoraInicio(LocalDateTime.now().minusDays(2));
        rep1.addPieza(pinturaCapot);
        rep1.addPieza(masilla);
        rep1.addPieza(manoObraChapa);
        rep1.addPieza(manoObraPintura);
        rep1.setUser(user1);
        rep1.setVehiculo(vehiculo1);
        rep1.setEstado(EstadoReparacion.PENDIENTE);

// Reparación 2: Pintura del paragolpes trasero
        Reparacion rep2 = new Reparacion();
        rep2.setDescripcion("Reparación de grieta y pintura del paragolpes trasero");
        rep2.setTrabajador(trabajador2);
        rep2.setHoraInicio(LocalDateTime.now().minusDays(1));
        rep2.addPieza(pinturaParagolpes);
        rep2.addPieza(manoObraChapa);
        rep2.addPieza(manoObraPintura);
        rep2.setUser(user2);
        rep2.setVehiculo(vehiculo3);
        rep2.setEstado(EstadoReparacion.PENDIENTE);


        reparacionRepository.save(rep1);
        reparacionRepository.save(rep2);

        // Citas
        Cita cita1 = new Cita();
        cita1.setUser(user1);
        cita1.setFecha(LocalDateTime.now().plusDays(1));

        Cita cita2 = new Cita();
        cita2.setUser(user2);
        cita2.setFecha(LocalDateTime.now().plusDays(3));

        citaRepository.save(cita1);
        citaRepository.save(cita2);

        // Orden de trabajo 1: relacionada con rep1 (arreglo y pintura de capó)
        OrdenDeTrabajo orden1 = new OrdenDeTrabajo();
        orden1.setTrabajadores(List.of(trabajador1));
        orden1.setVehiculo(vehiculo1);
        orden1.setDescripcionTrabajo("Reparación de abolladura y pintura del capó");
        orden1.setEstadoOrdenDeTrabajo(EstadoReparacion.EN_PROCESO);
        orden1.setPiezas(List.of(
                pinturaCapot,
                masilla,
                manoObraChapa,
                manoObraPintura
        ));
        orden1.setUser(user1);

// Orden de trabajo 2: relacionada con rep2 (pintura del paragolpes trasero)
        OrdenDeTrabajo orden2 = new OrdenDeTrabajo();
        orden2.setTrabajadores(List.of(trabajador2));
        orden2.setVehiculo(vehiculo3);
        orden2.setDescripcionTrabajo("Reparación de grieta y pintura del paragolpes trasero");
        orden2.setEstadoOrdenDeTrabajo(EstadoReparacion.EN_PROCESO);
        orden2.setPiezas(List.of(
                pinturaParagolpes,
                manoObraChapa,
                manoObraPintura
        ));
        orden2.setUser(user2);

// Guardar en base de datos
        ordenDeTrabajoRepository.save(orden1);
        ordenDeTrabajoRepository.save(orden2);


    }
}

