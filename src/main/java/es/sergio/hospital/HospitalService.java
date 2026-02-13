package es.sergio.hospital;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

public class HospitalService {

    private final List<Paciente> pacientes;

    public HospitalService(List<Paciente> pacientesIniciales){
        this.pacientes = new ArrayList<>(pacientesIniciales);
    }

    public List<Paciente>listarPacientes(){
        return List.copyOf(pacientes); //devuelve copia segura
    }

    public void anadirPaciente(Paciente paciente){
        pacientes.add(paciente);
    }

    public boolean eliminarPacientePorId(Long id){
        return pacientes.removeIf(p -> p.getId().equals(id));
    }



    public Optional<Paciente>  buscarPorNombre (String nombre) {
        return pacientes.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    public List<Paciente> buscarMayoresDe(int edad) {
        return pacientes.stream()
        .filter(p -> p.getEdad() > edad)
        .toList();
    }

    public Optional<Paciente> buscarPorId(Long id){
        return pacientes.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public List<Cita> listarCitasPorEspecialidad(String especialidad) {
        return pacientes.stream()
                .flatMap(p -> p.getCitas().stream())
                .filter(c -> c.getEspecialidad().equalsIgnoreCase(especialidad))
                .toList();
    }

    public boolean anadirCitaAPaciente(Long pacienteId, Cita cita) {
        Optional<Paciente> pOpt = buscarPorId(pacienteId);
        if (pOpt.isEmpty()) return false;

        pOpt.get().addCita(cita);
        return true;
    }



}
