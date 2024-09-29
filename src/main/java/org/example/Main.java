package org.example;

import DTOs.AlumnoDTO;
import DTOs.AlumnoMapper;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repositorios.*;
import repositorios.enums.Repositorios;
import services.ServicioAlumno;
import services.ServicioCarrera;
import services.ServicioInscripcion;


import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("TP");
            em = emf.createEntityManager();
            em.getTransaction().begin();

            // Setup
            // Instancias singleton repositorios
            RepositorioAlumnoImpl repoAlumnos = FactoryRepositorios.getRepositorioAlumno(em);
            RepositorioInscripcionImpl repoInscripciones = FactoryRepositorios.getRepositorioInscripcion(em);
            RepositorioCarreraImpl repoCarreras = FactoryRepositorios.getRepositorioCarrera(em);

            //Servicios
            ServicioAlumno servicioAlumnos = new ServicioAlumno(repoAlumnos, repoCarreras);
            ServicioCarrera servicioCarrera = new ServicioCarrera(repoCarreras);
            ServicioInscripcion servicioInscripcion = new ServicioInscripcion(repoInscripciones);

            //Alumnos
            Alumno a1 = new Alumno("Juan", "Gonzalez", 19, "M", "Tandil");
            Alumno a2 = new Alumno("Maria", "Fernandez", 22, "F", "Mar del Plata");
            Alumno a3 = new Alumno("Carlos", "Martinez", 18, "M", "Azul");
            Alumno a4 = new Alumno("Ana", "Lopez", 21, "F", "Laprida");
            Alumno a5 = new Alumno("Pedro", "Perez", 19, "M", "Tandil");

            //Carreras
            Carrera c1 = new Carrera("Ingenieria en Sistemas");
            Carrera c2 = new Carrera("Licenciatura en Gestion Ambiental");
            Carrera c3 = new Carrera("TUDAI");
            Carrera c4 = new Carrera("Licenciatura en Fisica");
            Carrera c5 = new Carrera("Profesorado de Informatica");

            AlumnoMapper alumnoMapper = new AlumnoMapper();
            AlumnoDTO alumnoDTO1 = alumnoMapper.apply(a1);
            AlumnoDTO alumnoDTO2 = alumnoMapper.apply(a2);
            AlumnoDTO alumnoDTO3 = alumnoMapper.apply(a3);
            AlumnoDTO alumnoDTO4 = alumnoMapper.apply(a4);
            AlumnoDTO alumnoDTO5 = alumnoMapper.apply(a5);

            servicioAlumnos.altaAlumno(alumnoDTO1);
            servicioAlumnos.altaAlumno(alumnoDTO2);
            servicioAlumnos.altaAlumno(alumnoDTO3);
            servicioAlumnos.altaAlumno(alumnoDTO4);
            servicioAlumnos.altaAlumno(alumnoDTO5);

            List<AlumnoDTO> alumnosFromDB = servicioAlumnos.listarAlumnos();
            for(AlumnoDTO alumnoFromDb : alumnosFromDB){
                System.out.println(alumnoFromDb.getNombre());
            }

            em.getTransaction().commit();
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