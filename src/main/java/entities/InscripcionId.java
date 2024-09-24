package entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class InscripcionId implements Serializable {

    private int idCarrera;
    private AlumnoId alumnoId;

    public InscripcionId() {}

    public InscripcionId(int idCarrera, AlumnoId alumnoId) {
        this.idCarrera = idCarrera;
        this.alumnoId = alumnoId;
    }

    // Getters y Setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscripcionId that = (InscripcionId) o;
        return idCarrera == that.idCarrera && Objects.equals(alumnoId, that.alumnoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCarrera, alumnoId);
    }
}
