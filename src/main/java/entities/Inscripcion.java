package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Getter
@Setter
@Table(name = "inscripcion")
@IdClass(InscripcionId.class)
@NamedQuery(
        name = Inscripcion.CARRERASSORTCANTINSC,
        query = "SELECT i.carrera , count(i) as cant_inscriptos " +
                "FROM Inscripcion i " +
                "GROUP BY i.carrera.nombre " +
                "ORDER BY cant_inscriptos"
)

public class Inscripcion implements Serializable {
    public final static String CARRERASSORTCANTINSC = "Inscripcion.CarrerasSortCantInsc";

    @Id
    @Column(name = "id_carrera")
    private int idCarrera;

    @Id
    @Column(name = "nro_libreta")
    private int nroLibreta;

    @ManyToOne
    @JoinColumn(name = "id_carrera", insertable = false, updatable = false)
    private Carrera carrera;

    @ManyToOne
    @JoinColumn(name = "nro_libreta", insertable = false, updatable = false)
    private Alumno alumno;

    @Column(name = "fecha_inscripcion")
    private LocalDate fechaInscripcion;

    @Column(name = "se_graduo")
    private boolean seGraduo;

    public Inscripcion() {
    }

    public Inscripcion(Alumno alumno, Carrera carrera, LocalDate fechaInscripcion) {
        this.nroLibreta = alumno.getNro_libreta();
        this.idCarrera = carrera.getId_carrera();
        this.fechaInscripcion = fechaInscripcion;
        this.seGraduo = false;
    }

    public String getAntiguedad() {
        Period antiguedad = Period.between(fechaInscripcion, LocalDate.now());
        return antiguedad.getYears() + " años, " + antiguedad.getMonths() + " meses";
    }
}
