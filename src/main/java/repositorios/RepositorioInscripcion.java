package repositorios;

import entities.Alumno;
import entities.Carrera;

import java.util.List;

public interface RepositorioInscripcion {
    void matricularAlumnoCarrera(Alumno alumno , Carrera carrera);
    List<Carrera> recuperarCarrerasSortByCantInscp();


}
