package DTOs;

import entities.Alumno;
import entities.Carrera;
import entities.Inscripcion;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class InscripcionDTO implements Serializable {

    private int idCarrera;
    private int nroLibreta;
    private Carrera carrera;
    private Alumno alumno;
    private LocalDate fechaInscripcion;
    private boolean seGraduo;

    public InscripcionDTO(Inscripcion i) {
        this.idCarrera = i.getIdCarrera();
        this.nroLibreta = i.getNroLibreta();
        this.carrera = i.getCarrera();
        this.alumno = i.getAlumno();
        this.fechaInscripcion = i.getFechaInscripcion();
        this.seGraduo = i.isSeGraduo();
    }
}
