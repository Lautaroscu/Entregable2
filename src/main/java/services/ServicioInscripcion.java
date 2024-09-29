package services;

import DTOs.*;
import entities.Alumno;
import entities.Carrera;
import entities.Inscripcion;
import repositorios.RepositorioInscripcion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServicioInscripcion {
    private InscripcionMapper inscripcionMapper;
    private CarreraMapper carreraMapper;
    private RepositorioInscripcion repositorioInscripcion;

    public ServicioInscripcion(RepositorioInscripcion repositorioInscripcion) {
        this.inscripcionMapper = new InscripcionMapper();
        this.carreraMapper = new CarreraMapper();
        this.repositorioInscripcion = repositorioInscripcion;
    }

    public InscripcionDTO obtenerInscripcion(int id) {
        Inscripcion inscripcion = repositorioInscripcion.obtenerInscripcion(id);
        return inscripcionMapper.apply(inscripcion);
    }

    public void agregarInscripcion(InscripcionDTO inscripcion) {
        Inscripcion i = new Inscripcion(inscripcion.getAlumno(), inscripcion.getCarrera(), LocalDate.now());
        repositorioInscripcion.agregarInscripcion(i);
    }

    public void eliminarInscripcion(int id) {
        repositorioInscripcion.eliminarInscripcion(id);
    }

    public void modificarInscripcion(Inscripcion inscripcion) {
        repositorioInscripcion.modificarInscripcion(inscripcion);
    }

    public void matricularAlumnoCarrera(AlumnoDTO alumno, CarreraDTO carrera) {
        Alumno a = new Alumno(alumno.getNombre(), alumno.getApellido(), alumno.getEdad(), alumno.getGenero(), alumno.getCiudad_residencia());
        Carrera c = new Carrera(carrera.getNombre());
        repositorioInscripcion.matricularAlumnoCarrera(a, c);
    }

    public List<CarreraDTO> obtenerCarrerasPorCantInscriptos() {
        List<Carrera> carreras = repositorioInscripcion.recuperarCarrerasSortByCantInscp();
        List<CarreraDTO> carrerasDTO = new ArrayList<>();
        for (Carrera c : carreras) {
            carrerasDTO.add(carreraMapper.apply(c));
        }

        return carrerasDTO;
    }
}
