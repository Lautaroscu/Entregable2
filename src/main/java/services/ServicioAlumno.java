package services;

import DTOs.AlumnoDTO;
import DTOs.AlumnoMapper;
import entities.Alumno;
import entities.Carrera;
import repositorios.RepositorioCarrera;
import repositorios.RepositorioAlumno;

import java.util.List;

public class ServicioAlumno {
    private AlumnoMapper alumnoMapper;
    private RepositorioAlumno repositorioAlumno;
    private RepositorioCarrera repositorioCarrera;

    public ServicioAlumno(RepositorioAlumno repositorioAlumno, RepositorioCarrera repositorioCarrera) {
        alumnoMapper = new AlumnoMapper();
        this.repositorioAlumno = repositorioAlumno;
        this.repositorioCarrera = repositorioCarrera;
    }

    public void altaAlumno(AlumnoDTO alumno) {
        Alumno nuevo = new Alumno(alumno.getNombre(), alumno.getApellido(), alumno.getEdad(), alumno.getGenero(), alumno.getCiudad_residencia());
        repositorioAlumno.altaAlumno(nuevo);
    }

    public void bajaAlumno(int nroLibreta) {
        repositorioAlumno.bajaAlumno(nroLibreta);
    }

    public void modificarAlumno(AlumnoDTO alumno) {
        Alumno modificado = repositorioAlumno.recuperarAlumnoPorNroLib(alumno.getNro_libreta());
        modificado.setNombre(alumno.getNombre());
        modificado.setApellido(alumno.getApellido());
        modificado.setEdad(alumno.getEdad());
        modificado.setGenero(alumno.getGenero());
        modificado.setCiudad_residencia(alumno.getCiudad_residencia());

        repositorioAlumno.modificarAlumno(modificado);

    }

    public List<AlumnoDTO> listarAlumnos() {
        return repositorioAlumno.listarAlumnos()
                .stream()
                .map(alumnoMapper)
                .toList();
    }

    public List<AlumnoDTO> listarAlumnosPorGenero(String genero) {
        return repositorioAlumno.recuperarAlumnosPorGenero(genero)
                .stream()
                .map(alumnoMapper)
                .toList();
    }

    public List<AlumnoDTO> listarAlumnosOrdenadosPorLibretaASC() {
        return repositorioAlumno
                .recuperarAlumnosSortByNroLib()
                .stream()
                .map(alumnoMapper)
                .toList();
    }

    public AlumnoDTO recuperarAlumnoPorNroLib(int nroLibreta) {
        Alumno al = repositorioAlumno.recuperarAlumnoPorNroLib(nroLibreta);
        return new AlumnoDTO(al);
    }

    public List<AlumnoDTO> listarAlumnos(int idCarrera, String ciudad) {
        Carrera carrera = repositorioCarrera.recuperarCarrera(idCarrera);
        return repositorioAlumno.recuperarAlumnosPorCarrerayCiudad(carrera, ciudad)
                .stream()
                .map(alumnoMapper)
                .toList();
    }
}
