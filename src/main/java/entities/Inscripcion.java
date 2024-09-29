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
@IdClass(InscripcionId.class)
@NamedQuery(name = Inscripcion.CARRERASSORTCANTINSC , query = "SELECT i.carrera , count(i) as cant_inscriptos FROM Inscripcion i GROUP BY i.carrera.nombre " +
        "ORDER BY cant_inscriptos")
public class Inscripcion implements Serializable {
    public final static String CARRERASSORTCANTINSC = "Inscripcion.CarrerasSortCantInsc";

    @Id
    @Column(name = "id_carrera")
    private int idCarrera; // Debe ser el tipo de la clave primaria de Carrera (Long o el tipo que uses)

    @Id
    @Column(name = "nro_libreta")
    private int nroLibreta;  // Debe ser el tipo de la clave primaria de Alumno (Long o el tipo que uses)

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

    public Inscripcion() {}

    public String getAntiguedad() {
        Period antiguedad = Period.between(fechaInscripcion, LocalDate.now());
        return antiguedad.getYears() + " años, " + antiguedad.getMonths() + " meses";
    }

    public Inscripcion(Alumno alumno, Carrera carrera, LocalDate fechaInscripcion) {
        this.nroLibreta = alumno.getNro_libreta(); // Suponiendo que `nro_libreta` es la PK de Alumno
        this.idCarrera = carrera.getId_carrera(); // Suponiendo que `id_carrera` es la PK de Carrera
        this.fechaInscripcion = fechaInscripcion;
        this.seGraduo = false;
    }
}
