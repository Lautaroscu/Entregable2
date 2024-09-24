package entities;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Getter
@Setter
@Table(name = "inscripcion")
public class Inscripcion  implements Serializable {

    @EmbeddedId
    private InscripcionId inscripcionId;

    @ManyToOne
    @MapsId("alumnoId")
    @JoinColumns({
            @JoinColumn(name = "dniAlumno", referencedColumnName = "dni"),
            @JoinColumn(name = "nroLibreta", referencedColumnName = "nro_libreta")
    })
    private Alumno alumno;

    @ManyToOne
    @MapsId("idCarrera")
    @JoinColumn(name = "idCarrera", referencedColumnName = "id_carrera")
    private Carrera carrera;

    @Column
    private LocalDate fecha_inscripcion;

    @Column
    private boolean seGraduo;

    public Inscripcion() {

    }

    public String getAntiguedad() {
        return Period.between(fecha_inscripcion, LocalDate.now()).toString();
    }

    public Inscripcion( InscripcionId id ,Alumno alumno, Carrera carrera, LocalDate fecha_inscripcion) {
        this.inscripcionId = id;
        this.alumno = alumno;
        this.carrera = carrera;
        this.fecha_inscripcion = fecha_inscripcion;
    }
}
