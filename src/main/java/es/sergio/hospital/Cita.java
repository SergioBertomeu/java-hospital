package es.sergio.hospital;

import java.time.LocalDate;

public class Cita {

    private final Long id;
    private final LocalDate fecha;
    private final String especialidad;

    public Cita (Long id, LocalDate fecha, String especialidad) {

        this.id = id;
        this.fecha = fecha;
        this.especialidad = especialidad;
    }

    public Long getId(){
        return id;
    }
    public LocalDate getFecha(){
        return fecha;
    }
    public String getEspecialidad(){
        return especialidad;
    }

}
