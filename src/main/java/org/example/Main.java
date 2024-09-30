package org.example;

import DTOs.AlumnoDTO;
import DTOs.CarreraReporteDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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

            // Instancias singleton repositorios
            RepositorioAlumnoImpl repoAlumnos = FactoryRepositorios.getRepositorioAlumno(em);
            RepositorioInscripcionImpl repoInscripciones = FactoryRepositorios.getRepositorioInscripcion(em);
            RepositorioCarreraImpl repoCarreras = FactoryRepositorios.getRepositorioCarrera(em);

            //Servicios
            ServicioAlumno servicioAlumnos = new ServicioAlumno(repoAlumnos, repoCarreras);
            ServicioCarrera servicioCarrera = new ServicioCarrera(repoCarreras);
            ServicioInscripcion servicioInscripcion = new ServicioInscripcion(repoInscripciones);

            //2) B)
            System.out.println("Lista de alumnos ordenados por numero de libreta");
            System.out.println("<------------------------------------------------>");
            List<AlumnoDTO> alumnosPorOrdenApellido = servicioAlumnos.listarAlumnos();
            for (AlumnoDTO alumno : alumnosPorOrdenApellido) {
                System.out.println(alumno.getNro_libreta() + ": " + alumno.getApellido() + ", " + alumno.getNombre());
            }

            System.out.println(" ");
            System.out.println(" ");

            //3)
            System.out.println("Reporte inscriptos y egresados por carrera");
            System.out.println("<------------------------------------------------>");
            List<CarreraReporteDTO> carreraReporteDTO = servicioCarrera.generarReporteInscriptosEgresados();

            for (CarreraReporteDTO carreraReporte : carreraReporteDTO) {
                System.out.println(carreraReporte.toString());
            }

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