package entities;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "alumno")
@Getter
@ToString
public class Alumno {

    @Setter
    @EmbeddedId
    private AlumnoId alumnoId;


    @Column
    private String nombre;
    @Column
    private String apellido;

    @Column
    private int edad;

    @Column
    private String genero;

    @Column
    private String ciudad_residencia;

    public Alumno() {
    }
    public Alumno(AlumnoId alumnoId, String nombre, String apellido, int edad, String genero, String ciudad_residencia) {
        this.alumnoId = alumnoId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad_residencia = ciudad_residencia;
    }

}
