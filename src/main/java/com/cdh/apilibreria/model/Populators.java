package com.cdh.apilibreria.model;

import com.cdh.apilibreria.repository.*;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "spring.jpa.hibernate.ddl-auto", havingValue = "create", matchIfMissing = false)
public class Populators {
    private LibroRepository librosRepository;
    private AutoresRepository autorRepository;
    private TemasRepository temasRepository;
    private EdicionesRepository edicionRepository;
    private FormatosRepository formatoRepository;
    private UserRepository userRepository;
    private DatosBankRepository datosBankRepository;
    private PedidoRepository pedidoRepository;

    
    public Populators(LibroRepository librosRepository, AutoresRepository autorRepository,
			TemasRepository temasRepository, EdicionesRepository edicionRepository,
			FormatosRepository formatoRepository, UserRepository userRepository,
			DatosBankRepository datosBankRepository, PedidoRepository carritoRepository) {
		super();
		this.librosRepository = librosRepository;
		this.autorRepository = autorRepository;
		this.temasRepository = temasRepository;
		this.edicionRepository = edicionRepository;
		this.formatoRepository = formatoRepository;
		this.userRepository = userRepository;
		this.datosBankRepository = datosBankRepository;
		this.pedidoRepository = pedidoRepository;
	}


	@Transactional
    @PostConstruct
    public void populate(){
        // Autores
        Autor autor1 = new Autor(
            "Julia Navarro",
            "julia.jpg",
            "Julia Navarro es una destacada periodista y novelista española nacida en Madrid en 1953. A lo largo de su carrera, ha combinado su experiencia en el periodismo con su pasión por la escritura de novelas históricas y de intriga. " +
            "Navarro se dio a conocer en el mundo literario con su primera novela, \"La Hermandad de la Sábana Santa\" (2004), que se convirtió en un éxito inmediato. Entre sus obras más destacadas se encuentran \"La Biblia de Barro\" (2005), \"Dime quién soy\" (2010) y \"Historia de un canalla\" (2016)."
        );
        autorRepository.save(autor1);
        
        Autor autor2 = new Autor(
            "Arturo Pérez-Reverte",
            "arturo.jpg",
            "Arturo Pérez-Reverte es un destacado escritor y periodista español, nacido en Cartagena en 1951. Trabajó como corresponsal de guerra durante más de dos décadas, lo que enriqueció sus obras. " +
            "Es conocido por sus novelas históricas y de aventuras, como \"Las aventuras del capitán Alatriste\", \"La tabla de Flandes\" y \"El club Dumas\". En 2003 fue elegido miembro de la Real Academia Española."
        );
        autorRepository.save(autor2);
        
        Autor autor3 = new Autor(
            "José de Espronceda",
            "joseDeEspronceda.jpg",
            "José de Espronceda (1808-1842) fue un poeta y escritor español, uno de los máximos representantes del Romanticismo en España. Fundó la sociedad secreta Los Numantinos y vivió en el exilio en varias ocasiones. " +
            "Entre sus obras más destacadas se encuentran \"El estudiante de Salamanca\", \"Canción del pirata\" y \"Sancho Saldaña\"."
        );
        autorRepository.save(autor3);
        
        Autor autor4 = new Autor(
            "Carolina Coronado",
            "carolinaCoronado.jpg",
            "Carolina Coronado (1820-1911) fue una destacada escritora, poetisa y novelista española del Romanticismo. Es conocida por su poesía y sus novelas, como \"Paquita\" y \"La Sigea\". " +
            "Coronado también fue una figura revolucionaria y apoyó a Isabel II durante la guerra civil."
        );
        autorRepository.save(autor4);
        
        Autor autor5 = new Autor(
            "Miguel de Cervantes",
            "miguelDeCervantes.jpg",
            "Miguel de Cervantes Saavedra (1547-1616) fue un novelista, poeta y dramaturgo español, considerado una de las figuras más importantes de la literatura mundial. " +
            "Su obra más famosa, \"Don Quijote de la Mancha\", es considerada la primera novela moderna y una de las mejores obras de la literatura universal."
        );
        autorRepository.save(autor5);
    

        // Temas
        Temas tema1 = new Temas("Historia","historia.jpeg"," Un libro con el tema de historia trata sobre eventos, personajes y contextos históricos, que pueden ser reales o ficticios, pero siempre basados en algún período específico del pasado. Algunos tipos de libros históricos incluyen:\n"
        		+ "\n"
        		+ "Novelas Históricas: Narrativas que mezclan hechos históricos con ficción, creando tramas emocionantes y educativas.\n"
        		+ "\n"
        		+ "Biografías: Relatos detallados sobre la vida de personas importantes en la historia.\n"
        		+ "\n"
        		+ "Ensayos Históricos: Análisis e interpretaciones de eventos, sociedades y culturas del pasado.\n"
        		+ "\n"
        		+ "Crónicas: Registros detallados de eventos históricos, a menudo en orden cronológico.\n"
        		+ "\n"
        		+ "El objetivo principal de estos libros es informar y educar sobre hechos del pasado, proporcionando al lector una comprensión más profunda de cómo esos eventos han influido en el presente.");
        temasRepository.save(tema1);
        Temas tema2 = new Temas("Bélico","belico.jpg","Un libro con el tema bélico trata sobre guerras, conflictos armados y sus efectos en los individuos y las sociedades. Algunos tipos de libros bélicos incluyen:\n"
        		+ "\n"
        		+ "Novelas Bélicas: Narrativas ficticias que se desarrollan en contextos de guerra, explorando la vida de los soldados y civiles afectados por el conflicto.\n"
        		+ "\n"
        		+ "Memorias: Relatos personales de veteranos de guerra que describen sus experiencias y vivencias en el campo de batalla.\n"
        		+ "\n"
        		+ "Ensayos Bélicos: Análisis profundos sobre la estrategia, tácticas y consecuencias de guerras históricas.\n"
        		+ "\n"
        		+ "Historias de Batallas: Descripciones detalladas de batallas específicas, incluyendo el desarrollo de los combates y sus resultados.\n"
        		+ "\n"
        		+ "El objetivo principal de estos libros es ofrecer una comprensión más profunda de los horrores, sacrificios y heroísmos asociados con los conflictos armados, así como de las lecciones que se pueden aprender de ellos.");
        temasRepository.save(tema2);
        Temas tema3 = new Temas("Poesía","poesia.jpg","Un libro con el tema de poesía trata sobre la expresión artística a través del uso de versos, rimas y figuras literarias. Algunos tipos de libros de poesía incluyen:\n"
        		+ "\n"
        		+ "Poemarios: Colecciones de poemas escritos por un mismo autor, a menudo organizados en torno a un tema o estilo particular.\n"
        		+ "\n"
        		+ "Antologías Poéticas: Compilaciones de poemas de diversos autores, seleccionados por su calidad o relevancia temática.\n"
        		+ "\n"
        		+ "Poesía Épica: Poemas largos que narran hechos heroicos o históricos, a menudo con un estilo grandilocuente.\n"
        		+ "\n"
        		+ "Poesía Lírica: Poemas que expresan sentimientos personales y emociones, con un lenguaje más íntimo y musical.\n"
        		+ "\n"
        		+ "El objetivo principal de estos libros es capturar y transmitir emociones, pensamientos y experiencias de manera estética y evocadora, utilizando el poder del lenguaje poético.");
        temasRepository.save(tema3);
        Temas tema4 = new Temas("Biografia","biografia.jpg","Un libro con el tema de biografía trata sobre la vida de una persona real, explorando sus experiencias, logros y desafíos. Algunos tipos de libros de biografía incluyen:\n"
        		+ "\n"
        		+ "Biografía Autorizada: Relato de la vida de una persona escrito con su consentimiento y, a menudo, con su colaboración.\n"
        		+ "\n"
        		+ "Biografía No Autorizada: Relato de la vida de una persona escrito sin su consentimiento ni colaboración, basado en investigaciones independientes.\n"
        		+ "\n"
        		+ "Autobiografía: Relato de la vida de una persona escrito por ella misma, ofreciendo una perspectiva personal y directa.\n"
        		+ "\n"
        		+ "Memorias: Relato en primera persona de las experiencias y recuerdos significativos del autor, a menudo centrado en eventos específicos de su vida.\n"
        		+ "\n"
        		+ "El objetivo principal de estos libros es ofrecer una comprensión profunda y detallada de la vida de una persona, proporcionando al lector una visión íntima de sus logros, desafíos y legado.");
        temasRepository.save(tema4);
        Temas tema5 = new Temas("terror","terror.jpg","Un libro con el tema de terror está diseñado para provocar miedo, suspenso y emociones intensas en el lector. Algunos tipos de libros de terror incluyen:\n"
        		+ "\n"
        		+ "Novelas de Terror Psicológico: Exploran el miedo y la paranoia a través de la mente de los personajes, jugando con su percepción de la realidad.\n"
        		+ "\n"
        		+ "Terror Sobrenatural: Incluyen elementos como fantasmas, demonios, vampiros y otros seres paranormales que amenazan a los personajes.\n"
        		+ "\n"
        		+ "Horror Corporal: Describen transformaciones físicas grotescas y mutilaciones, buscando causar repulsión y horror visceral.\n"
        		+ "\n"
        		+ "Thrillers de Terror: Combinan el suspenso con el horror, manteniendo al lector en un estado constante de tensión.\n"
        		+ "\n"
        		+ "El objetivo principal de estos libros es sumergir al lector en atmósferas inquietantes y espeluznantes, proporcionando experiencias de lectura emocionantes y aterradoras.");
        temasRepository.save(tema5);
        Temas tema6 = new Temas("Novela","novela.jpg","Un libro con el tema de novela es una obra literaria de ficción que narra una historia compleja y desarrollada a través de personajes, tramas y ambientes. Algunos tipos de novelas incluyen:\n"
        		+ "\n"
        		+ "Novelas Románticas: Se centran en relaciones amorosas y emocionales entre los personajes.\n"
        		+ "\n"
        		+ "Novelas Históricas: Combinan hechos históricos con ficción, situando la trama en un contexto histórico específico.\n"
        		+ "\n"
        		+ "Novelas de Ciencia Ficción: Exploran futuros hipotéticos, tecnologías avanzadas y mundos alternativos.\n"
        		+ "\n"
        		+ "Novelas Policíacas: Se centran en la resolución de crímenes y la investigación detectivesca.\n"
        		+ "\n"
        		+ "Novelas de Fantasía: Narran historias en mundos imaginarios, con elementos mágicos y seres sobrenaturales.\n"
        		+ "\n"
        		+ "El objetivo principal de estas novelas es entretener y capturar la imaginación del lector, ofreciendo una experiencia inmersiva y emocionalmente rica.");
        temasRepository.save(tema6);

        // Ediciones
        Edicion edicion1 = new Edicion("Fisico");
        edicionRepository.save(edicion1);
        Edicion edicion2 = new Edicion("Digital");
        edicionRepository.save(edicion2);

        // Formatos
        Formato formato1 = new Formato("Tapa Dura");
        formatoRepository.save(formato1);
        Formato formato2 = new Formato("Tapa Blanda");
        formatoRepository.save(formato2);
        Formato formato3 = new Formato("PDF");
        formatoRepository.save(formato3);
        Formato formato4 = new Formato("ePub");
        formatoRepository.save(formato4);
        
        //user
        User admin = new User("ivan","ruiz","06200","calle benito","45559223h","ivanruizalcala@gmail.com","1234","admin");
        userRepository.save(admin);
        User user1 = new User("dario","ruiz","06200","calle benito","1231233h","darioruizpena@gmail.com","1111","member");
        userRepository.save(user1);
        
        //datos bancarios
        DatosBank datos1 = new DatosBank(admin,"456456","768","ivan");
        datosBankRepository.save(datos1);
        DatosBank datos2 = new DatosBank(user1,"456456","768","ivan");
        datosBankRepository.save(datos2);
        

        // Libros
        Libro libro1 = new Libro("El niño que perdió la guerra","123456789101D", "libro1.jpg", autor1, tema1,19.99, edicion1, formato1,0);
        librosRepository.save(libro1);
        Libro libro2 = new Libro("El pintor de batallas","735125019219A", "pintor.jpg", autor2, tema2,23.99, edicion1, formato1,0);
        librosRepository.save(libro2);
        Libro libro3 = new Libro("El estudiante de Salamanca","495115914119B", "estudiante.jpg", autor3, tema3,17.99, edicion1, formato2,0);
        librosRepository.save(libro3);
        Libro libro4 = new Libro("Se va mi sombra, pero yo me quedo","995115777188V", "carolina.png", autor3, tema3,14.99, edicion1, formato1,0);
        librosRepository.save(libro4);
        
        // Carritos
        LocalDate fechaCompra = LocalDate.now(ZoneId.systemDefault());

        ArrayList<Libro> libros = new ArrayList<>();
        libros.add(libro4);
        Pedido pedido = new Pedido(user1,libros,10L,fechaCompra,150L);
        
    }
}