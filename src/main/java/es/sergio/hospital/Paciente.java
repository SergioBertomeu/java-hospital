package es.sergio.hospital;

import java.util.ArrayList;
import java.util.List;

public class Paciente {

    private final Long id;
    private final String nombre;
    private final int edad;
    private final List<Cita> citas;

    public Paciente (Long id, String nombre, int edad, List<Cita> citas){

        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.citas = ( citas == null) ? new ArrayList<>() : new ArrayList<>(citas);

    }

    public Long getId() {
        return id;
    }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad;}
    public List<Cita> getCitas(){
        return citas;
    }
    public void addCita (Cita cita){
        this.citas.add(cita);
    }

}
