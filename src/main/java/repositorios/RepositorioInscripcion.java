package repositorios;

import entities.Alumno;
import entities.Carrera;
import entities.Inscripcion;

import java.util.List;

public interface RepositorioInscripcion {
    Inscripcion obtenerInscripcion(int id);

    void agregarInscripcion(Inscripcion inscripcion);

    void modificarInscripcion(Inscripcion inscripcion);

    void eliminarInscripcion(int id);

    void matricularAlumnoCarrera(Alumno alumno, Carrera carrera);

    List<Carrera> recuperarCarrerasSortByCantInscp();
}
