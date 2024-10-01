package services;

import DTOs.alumno.AlumnoDTO;
import DTOs.carrera.CarreraDTO;
import DTOs.carrera.CarreraMapper;
import DTOs.inscripcion.InscripcionDTO;
import DTOs.inscripcion.InscripcionMapper;
import entities.Alumno;
import entities.Carrera;
import entities.Inscripcion;
import entities.InscripcionId;
import repositorios.interfaces.RepositorioInscripcion;

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

    public InscripcionDTO obtenerInscripcion(InscripcionId id) {
        Inscripcion inscripcion = repositorioInscripcion.obtenerInscripcion(id);
        return new InscripcionDTO(inscripcion);
    }


    public void eliminarInscripcion(InscripcionId id) {
        repositorioInscripcion.eliminarInscripcion(id);
    }

    public void modificarInscripcion(Inscripcion inscripcion) {
        repositorioInscripcion.modificarInscripcion(inscripcion);
    }

    public void matricularAlumnoCarrera(Alumno alumno, Carrera carrera) {
        System.out.println(alumno);
        System.out.println(carrera);
        repositorioInscripcion.matricularAlumnoCarrera(alumno, carrera);
    }


}
