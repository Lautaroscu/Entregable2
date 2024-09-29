package entities;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable

public class AlumnoId implements Serializable {

    private int dni;
    private int nro_libreta;

    public AlumnoId() {
    }

    public AlumnoId(int dni, int nro_libreta) {
        this.dni = dni;
        this.nro_libreta = nro_libreta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlumnoId alumnoId = (AlumnoId) o;
        return dni == alumnoId.dni && nro_libreta == alumnoId.nro_libreta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nro_libreta);
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getNro_libreta() {
        return nro_libreta;
    }

    public void setNro_libreta(int nro_libreta) {
        this.nro_libreta = nro_libreta;
    }
}
