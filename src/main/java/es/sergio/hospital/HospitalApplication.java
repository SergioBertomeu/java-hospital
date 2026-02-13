package es.sergio.hospital;

import java.util.Optional;
import java.time.LocalDate;
import java.util.List;

public class HospitalApplication {

    public static void main(String[] args) {

        Paciente ana = new Paciente(
                1L,
                "Ana",
                35,
                List.of(
                        new Cita(1L, LocalDate.of(2024, 3, 10), "Pediatría")
                )
        );

        Paciente luis = new Paciente(
                2L,
                "Luis",
                50,
                List.of(
                        new Cita(2L, LocalDate.of(2024, 3, 12), "Cardiología"),
                        new Cita(3L, LocalDate.of(2024, 4, 2), "Traumatología")
                )
        );

        Paciente marta = new Paciente(
                3L,
                "Marta",
                65,
                List.of(
                        new Cita(4L, LocalDate.of(2024, 5, 5), "Dermatología")
                )
        );


        List<Paciente> pacientes = List.of(ana, luis, marta);

        HospitalService service = new HospitalService(pacientes);

        System.out.println("\nListado inicial:");
        service.listarPacientes().forEach(p -> System.out.println("- " + p.getNombre()));

        System.out.println("\nAñadir paciente nuevo (Pepe, 45):");
        service.anadirPaciente(new Paciente(4L, "Pepe", 45, List.of()));
        service.listarPacientes().forEach(p -> System.out.println("- " + p.getNombre()));

        System.out.println("\nEliminar paciente ID 1 (Ana):");
        boolean eliminado = service.eliminarPacientePorId(1L);
        System.out.println(eliminado ? "Eliminado ✅" : "No existe ❌");

        System.out.println("\nListado final:");
        service.listarPacientes().forEach(p -> System.out.println("- " + p.getNombre()));


        System.out.println("\nBuscar por ID 2:");
        service.buscarPorId(2L).ifPresentOrElse(
                p -> System.out.println("Encontrado: " + p.getNombre()),
                () -> System.out.println("No existe el pacente con ID 2")
        );

        System.out.println("\nCitas de Cardiología:");
        service.listarCitasPorEspecialidad("Cardiología")
                .forEach(c -> System.out.println("- " + c.getFecha()+ " | " + c.getEspecialidad()));




        System.out.println("\nAñadir cita nueva a paciente ID 2 (Luis):");
        boolean ok = service.anadirCitaAPaciente(
                2L,
                new Cita(99L, java.time.LocalDate.of(2024, 6, 1), "Oftalmología")
        );

        System.out.println(ok ? "Cita añadida ✅" : "Paciente no encontrado ❌");

// Ver citas de Luis otra vez
        service.buscarPorId(2L).ifPresent(p -> {
            imprimirCitas(p);
        });





        String nombreBuscado = "Luis";

        Optional<Paciente> encontrado = service.buscarPorNombre(nombreBuscado);

        encontrado.ifPresentOrElse(
                p ->{
                    imprimirCitas(p);

        },
                () -> System.out.println("No existe el paciente: "+ nombreBuscado)
        );




        System.out.println("\nPacientes mayores de 40:");

        service.buscarMayoresDe( 40)
                .forEach(p -> System.out.println("- " + p.getNombre()));




    }

    private static void imprimirCitas(Paciente p) {
        System.out.println("Citas de " + p.getNombre() + ":");
        p.getCitas().forEach(c ->
                System.out.println("- " + c.getEspecialidad() + " (" + c.getFecha() + ")")
        );
    }

}
