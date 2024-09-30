package services;

import DTOs.AlumnoDTO;
import DTOs.CarreraDTO;
import DTOs.InscripcionDTO;
import entities.Alumno;
import entities.Carrera;
import entities.Inscripcion;
import mappers.AlumnoMapper;
import mappers.CarreraMapper;
import mappers.InscripcionMapper;
import repositorios.RepositorioInscripcion;

import java.util.ArrayList;
import java.util.List;

public class ServicioInscripcion {
    private InscripcionMapper inscripcionMapper;
    private CarreraMapper carreraMapper;
    private AlumnoMapper alumnoMapper;
    private RepositorioInscripcion repositorioInscripcion;

    public ServicioInscripcion(RepositorioInscripcion repositorioInscripcion) {
        this.inscripcionMapper = new InscripcionMapper();
        this.carreraMapper = new CarreraMapper();
        this.alumnoMapper = new AlumnoMapper();
        this.repositorioInscripcion = repositorioInscripcion;
    }

    public InscripcionDTO obtenerInscripcion(int id) {
        Inscripcion inscripcion = repositorioInscripcion.obtenerInscripcion(id);
        return inscripcionMapper.apply(inscripcion);
    }

    public void agregarInscripcion(InscripcionDTO inscripcion) {
        Inscripcion i = inscripcionMapper.toEntity(inscripcion);
        repositorioInscripcion.agregarInscripcion(i);
    }

    public void eliminarInscripcion(int id) {
        repositorioInscripcion.eliminarInscripcion(id);
    }

    public void modificarInscripcion(Inscripcion inscripcion) {
        repositorioInscripcion.modificarInscripcion(inscripcion);
    }

    public void matricularAlumnoCarrera(AlumnoDTO alumno, CarreraDTO carrera) {
        Alumno a = alumnoMapper.toEntity(alumno);
        Carrera c = carreraMapper.toEntity(carrera);
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
