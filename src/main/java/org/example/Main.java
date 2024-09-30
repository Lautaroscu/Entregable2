package org.example;

import DTOs.AlumnoDTO;
import DTOs.CarreraDTO;
import DTOs.CarreraReporteDTO;
import DTOs.InscripcionDTO;
import entities.Alumno;
import entities.Carrera;
import entities.Inscripcion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import mappers.AlumnoMapper;
import mappers.CarreraMapper;
import mappers.InscripcionMapper;
import repositorios.FactoryRepositorios;
import repositorios.RepositorioAlumnoImpl;
import repositorios.RepositorioCarreraImpl;
import repositorios.RepositorioInscripcionImpl;
import services.ServicioAlumno;
import services.ServicioCarrera;
import services.ServicioInscripcion;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("TP");
            em = emf.createEntityManager();
//            em.getTransaction().begin();

            // Instancias singleton repositorios
            RepositorioAlumnoImpl repoAlumnos = FactoryRepositorios.getRepositorioAlumno(em);
            RepositorioInscripcionImpl repoInscripciones = FactoryRepositorios.getRepositorioInscripcion(em);
            RepositorioCarreraImpl repoCarreras = FactoryRepositorios.getRepositorioCarrera(em);

            ServicioAlumno servicioAlumnos = new ServicioAlumno(repoAlumnos, repoCarreras);
            ServicioCarrera servicioCarrera = new ServicioCarrera(repoCarreras);
            ServicioInscripcion servicioInscripcion = new ServicioInscripcion(repoInscripciones);

            List<CarreraReporteDTO> carreraReporteDTO = servicioCarrera.generarReporteInscriptosEgresados();

            for(CarreraReporteDTO carreraReporte : carreraReporteDTO) {
                System.out.println(carreraReporte.toString());
            }
//            // Crear varios alumnos originales
//            Alumno a1 = new Alumno("Juan", "Gonzalez", 19, "M", "Tandil");
//            Alumno a2 = new Alumno("Maria", "Fernandez", 22, "F", "Mar del Plata");
//            Alumno a3 = new Alumno("Carlos", "Martinez", 18, "M", "Azul");
//            Alumno a4 = new Alumno("Ana", "Lopez", 21, "F", "Laprida");
//            Alumno a5 = new Alumno("Pedro", "Perez", 19, "M", "Tandil");
//
//            // Crear 10 alumnos adicionales
//            Alumno a6 = new Alumno("Lucia", "Ramirez", 24, "F", "Necochea");
//            Alumno a7 = new Alumno("Jorge", "Diaz", 20, "M", "Olavarria");
//            Alumno a8 = new Alumno("Carla", "Sanchez", 23, "F", "Quequen");
//            Alumno a9 = new Alumno("Diego", "Torres", 25, "M", "Bahía Blanca");
//            Alumno a10 = new Alumno("Laura", "Mendez", 21, "F", "Mar del Plata");
//            Alumno a11 = new Alumno("Santiago", "Garcia", 22, "M", "Azul");
//            Alumno a12 = new Alumno("Valeria", "Ortiz", 18, "F", "Laprida");
//            Alumno a13 = new Alumno("Fernando", "Gomez", 26, "M", "Necochea");
//            Alumno a14 = new Alumno("Patricia", "Gutierrez", 24, "F", "Olavarria");
//            Alumno a15 = new Alumno("Martin", "Ruiz", 20, "M", "Quequen");
//
//            // Crear varias carreras
//            Carrera c1 = new Carrera("Ingenieria en Sistemas");
//            Carrera c2 = new Carrera("Licenciatura en Gestion Ambiental");
//            Carrera c3 = new Carrera("TUDAI");
//            Carrera c4 = new Carrera("Licenciatura en Fisica");
//            Carrera c5 = new Carrera("Profesorado de Informatica");
//
//            // Instancia del mapper para carreras
//            CarreraMapper carreraMapper = new CarreraMapper();
//
//            // Convertir carreras a DTOs usando el mapper
//            CarreraDTO cDTO1 = carreraMapper.apply(c1);
//            CarreraDTO cDTO2 = carreraMapper.apply(c2);
//            CarreraDTO cDTO3 = carreraMapper.apply(c3);
//            CarreraDTO cDTO4 = carreraMapper.apply(c4);
//            CarreraDTO cDTO5 = carreraMapper.apply(c5);
//
//            // Usar el servicio para adicionar carreras
//            servicioCarrera.adicionarCarrera(cDTO1);
//            servicioCarrera.adicionarCarrera(cDTO2);
//            servicioCarrera.adicionarCarrera(cDTO3);
//            servicioCarrera.adicionarCarrera(cDTO4);
//            servicioCarrera.adicionarCarrera(cDTO5);
//
//            // Instancia del mapper para alumnos
//            AlumnoMapper alumnoMapper = new AlumnoMapper();
//
//            // Convertir a DTOs usando el mapper
//            AlumnoDTO alumnoDTO1 = alumnoMapper.apply(a1);
//            AlumnoDTO alumnoDTO2 = alumnoMapper.apply(a2);
//            AlumnoDTO alumnoDTO3 = alumnoMapper.apply(a3);
//            AlumnoDTO alumnoDTO4 = alumnoMapper.apply(a4);
//            AlumnoDTO alumnoDTO5 = alumnoMapper.apply(a5);
//            AlumnoDTO alumnoDTO6 = alumnoMapper.apply(a6);
//            AlumnoDTO alumnoDTO7 = alumnoMapper.apply(a7);
//            AlumnoDTO alumnoDTO8 = alumnoMapper.apply(a8);
//            AlumnoDTO alumnoDTO9 = alumnoMapper.apply(a9);
//            AlumnoDTO alumnoDTO10 = alumnoMapper.apply(a10);
//            AlumnoDTO alumnoDTO11 = alumnoMapper.apply(a11);
//            AlumnoDTO alumnoDTO12 = alumnoMapper.apply(a12);
//            AlumnoDTO alumnoDTO13 = alumnoMapper.apply(a13);
//            AlumnoDTO alumnoDTO14 = alumnoMapper.apply(a14);
//            AlumnoDTO alumnoDTO15 = alumnoMapper.apply(a15);
//
//            // Usar el servicio para alta de alumnos
//            servicioAlumnos.altaAlumno(alumnoDTO1);
//            servicioAlumnos.altaAlumno(alumnoDTO2);
//            servicioAlumnos.altaAlumno(alumnoDTO3);
//            servicioAlumnos.altaAlumno(alumnoDTO4);
//            servicioAlumnos.altaAlumno(alumnoDTO5);
//            servicioAlumnos.altaAlumno(alumnoDTO6);
//            servicioAlumnos.altaAlumno(alumnoDTO7);
//            servicioAlumnos.altaAlumno(alumnoDTO8);
//            servicioAlumnos.altaAlumno(alumnoDTO9);
//            servicioAlumnos.altaAlumno(alumnoDTO10);
//            servicioAlumnos.altaAlumno(alumnoDTO11);
//            servicioAlumnos.altaAlumno(alumnoDTO12);
//            servicioAlumnos.altaAlumno(alumnoDTO13);
//            servicioAlumnos.altaAlumno(alumnoDTO14);
//            servicioAlumnos.altaAlumno(alumnoDTO15);

//            AlumnoMapper amapper = new AlumnoMapper();
//            AlumnoDTO aDto1 = servicioAlumnos.recuperarAlumnoPorNroLib(1);
//            Alumno a1 = amapper.toEntity(aDto1);
//
//            AlumnoDTO aDto2 = servicioAlumnos.recuperarAlumnoPorNroLib(2);
//            Alumno a2 = amapper.toEntity(aDto2);
//
//            AlumnoDTO aDto3 = servicioAlumnos.recuperarAlumnoPorNroLib(3);
//            Alumno a3 = amapper.toEntity(aDto3);
//
//            AlumnoDTO aDto4 = servicioAlumnos.recuperarAlumnoPorNroLib(4);
//            Alumno a4 = amapper.toEntity(aDto4);
//
//            AlumnoDTO aDto5 = servicioAlumnos.recuperarAlumnoPorNroLib(5);
//            Alumno a5 = amapper.toEntity(aDto5);
//
//            AlumnoDTO aDto6 = servicioAlumnos.recuperarAlumnoPorNroLib(6);
//            Alumno a6 = amapper.toEntity(aDto6);
//
//            AlumnoDTO aDto7 = servicioAlumnos.recuperarAlumnoPorNroLib(7);
//            Alumno a7 = amapper.toEntity(aDto7);
//
//            AlumnoDTO aDto8 = servicioAlumnos.recuperarAlumnoPorNroLib(8);
//            Alumno a8 = amapper.toEntity(aDto8);
//
//            AlumnoDTO aDto9 = servicioAlumnos.recuperarAlumnoPorNroLib(9);
//            Alumno a9 = amapper.toEntity(aDto9);
//
//            AlumnoDTO aDto10 = servicioAlumnos.recuperarAlumnoPorNroLib(10);
//            Alumno a10 = amapper.toEntity(aDto10);
//
//            AlumnoDTO aDto11 = servicioAlumnos.recuperarAlumnoPorNroLib(11);
//            Alumno a11 = amapper.toEntity(aDto11);
//
//            AlumnoDTO aDto12 = servicioAlumnos.recuperarAlumnoPorNroLib(12);
//            Alumno a12 = amapper.toEntity(aDto12);
//
//            AlumnoDTO aDto13 = servicioAlumnos.recuperarAlumnoPorNroLib(13);
//            Alumno a13 = amapper.toEntity(aDto13);
//
//            AlumnoDTO aDto14 = servicioAlumnos.recuperarAlumnoPorNroLib(14);
//            Alumno a14 = amapper.toEntity(aDto14);
//
//            AlumnoDTO aDto15 = servicioAlumnos.recuperarAlumnoPorNroLib(15);
//            Alumno a15 = amapper.toEntity(aDto15);
//
//            CarreraMapper cMapper = new CarreraMapper();
//            CarreraDTO cDto1 = servicioCarrera.obtenerCarrera(1);
//            Carrera c1 = cMapper.toEntity(cDto1);
//
//            CarreraDTO cDto2 = servicioCarrera.obtenerCarrera(2);
//            Carrera c2 = cMapper.toEntity(cDto2);
//
//            CarreraDTO cDto3 = servicioCarrera.obtenerCarrera(3);
//            Carrera c3 = cMapper.toEntity(cDto3);
//
//            CarreraDTO cDto4 = servicioCarrera.obtenerCarrera(4);
//            Carrera c4 = cMapper.toEntity(cDto4);
//
//            CarreraDTO cDto5 = servicioCarrera.obtenerCarrera(5);
//            Carrera c5 = cMapper.toEntity(cDto5);
//
//            Inscripcion i1 = new Inscripcion(a1, c1);
//            Inscripcion i2 = new Inscripcion(a2, c2);
//            Inscripcion i3 = new Inscripcion(a3, c3);
//            Inscripcion i4 = new Inscripcion(a4, c4);
//            Inscripcion i5 = new Inscripcion(a5, c5);
//            Inscripcion i6 = new Inscripcion(a6, c1);
//            Inscripcion i7 = new Inscripcion(a7, c2);
//            Inscripcion i8 = new Inscripcion(a8, c3);
//            Inscripcion i9 = new Inscripcion(a9, c4);
//            Inscripcion i10 = new Inscripcion(a10, c5);
//            Inscripcion i11 = new Inscripcion(a11, c1);
//            Inscripcion i12 = new Inscripcion(a12, c2);
//            Inscripcion i13 = new Inscripcion(a13, c3);
//            Inscripcion i14 = new Inscripcion(a14, c4);
//            Inscripcion i15 = new Inscripcion(a15, c5);
//
//            // Instancia del mapper para inscripciones
//            InscripcionMapper inscripcionMapper = new InscripcionMapper();
//
//            // Convertir inscripciones a DTOs usando el mapper
//            InscripcionDTO inscripcionDTO1 = inscripcionMapper.apply(i1);
//            InscripcionDTO inscripcionDTO2 = inscripcionMapper.apply(i2);
//            InscripcionDTO inscripcionDTO3 = inscripcionMapper.apply(i3);
//            InscripcionDTO inscripcionDTO4 = inscripcionMapper.apply(i4);
//            InscripcionDTO inscripcionDTO5 = inscripcionMapper.apply(i5);
//            InscripcionDTO inscripcionDTO6 = inscripcionMapper.apply(i6);
//            InscripcionDTO inscripcionDTO7 = inscripcionMapper.apply(i7);
//            InscripcionDTO inscripcionDTO8 = inscripcionMapper.apply(i8);
//            InscripcionDTO inscripcionDTO9 = inscripcionMapper.apply(i9);
//            InscripcionDTO inscripcionDTO10 = inscripcionMapper.apply(i10);
//            InscripcionDTO inscripcionDTO11 = inscripcionMapper.apply(i11);
//            InscripcionDTO inscripcionDTO12 = inscripcionMapper.apply(i12);
//            InscripcionDTO inscripcionDTO13 = inscripcionMapper.apply(i13);
//            InscripcionDTO inscripcionDTO14 = inscripcionMapper.apply(i14);
//            InscripcionDTO inscripcionDTO15 = inscripcionMapper.apply(i15);
//
//            // Usar el servicio para agregar inscripciones
//            servicioInscripcion.agregarInscripcion(inscripcionDTO1);
//            servicioInscripcion.agregarInscripcion(inscripcionDTO2);
//            servicioInscripcion.agregarInscripcion(inscripcionDTO3);
//            servicioInscripcion.agregarInscripcion(inscripcionDTO4);
//            servicioInscripcion.agregarInscripcion(inscripcionDTO5);
//            servicioInscripcion.agregarInscripcion(inscripcionDTO6);
//            servicioInscripcion.agregarInscripcion(inscripcionDTO7);
//            servicioInscripcion.agregarInscripcion(inscripcionDTO8);
//            servicioInscripcion.agregarInscripcion(inscripcionDTO9);
//            servicioInscripcion.agregarInscripcion(inscripcionDTO10);
//            servicioInscripcion.agregarInscripcion(inscripcionDTO11);
//            servicioInscripcion.agregarInscripcion(inscripcionDTO12);
//            servicioInscripcion.agregarInscripcion(inscripcionDTO13);
//            servicioInscripcion.agregarInscripcion(inscripcionDTO14);
//            servicioInscripcion.agregarInscripcion(inscripcionDTO15);
//
//            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}