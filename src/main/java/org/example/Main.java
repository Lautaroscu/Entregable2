package org.example;

import DTOs.alumno.AlumnoDTO;
import DTOs.carrera.CantInscriptosCarreraDTO;
import DTOs.carrera.CarreraDTO;
import DTOs.carrera.CarreraReporteDTO;
import DTOs.inscripcion.InscripcionDTO;
import entities.Alumno;
import entities.Carrera;
import entities.Inscripcion;
import entities.InscripcionId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repositorios.FactoryRepositorios;
import repositorios.implementaciones.RepositorioAlumnoImpl;
import repositorios.implementaciones.RepositorioCarreraImpl;
import repositorios.implementaciones.RepositorioInscripcionImpl;
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

            //2)
            // A)
            System.out.println("Alta de un estudiante..");
            Alumno alumnoNuevo = new Alumno("Lautaro" , "Scuffi" , 20 , "Masculino" , "Juarez");
            Alumno alumnoNuevo1 = new Alumno("Agustin" , "Alvarez" , 20 , "Masculino" , "Tandil");
            Alumno alumnoNuevo2= new Alumno("Pepe" , "Carrizo" , 20 , "Masculino" , "Tandil");
            Alumno alumnoNuevo3 = new Alumno("Micaela" , "Rodriguez" , 22 , "Femenino" , "Tandil");

            servicioAlumnos.altaAlumno(alumnoNuevo);
            servicioAlumnos.altaAlumno(alumnoNuevo1);
            servicioAlumnos.altaAlumno(alumnoNuevo2);
            servicioAlumnos.altaAlumno(alumnoNuevo3);
            //checkeo de que se inserto el dato
            AlumnoDTO alumnoRecuperado = servicioAlumnos.recuperarAlumnoPorNroLib(alumnoNuevo.getNro_libreta());
            System.out.println(alumnoRecuperado);
            //B)
            System.out.println("Matricular estudiante a una carrera");
            Carrera tudai = new Carrera("tudai");
            Carrera sistemas = new Carrera("ing sistemas");
            Carrera licAmbiental = new Carrera("lic ambiental");

            servicioCarrera.adicionarCarrera(tudai);
            servicioCarrera.adicionarCarrera(sistemas);
            servicioCarrera.adicionarCarrera(licAmbiental);

            servicioInscripcion.matricularAlumnoCarrera(alumnoNuevo , tudai);
            InscripcionId idInsc = new InscripcionId(tudai.getId_carrera() , alumnoRecuperado.getNro_libreta());

            InscripcionDTO inscripcionRecuperada = servicioInscripcion.obtenerInscripcion(idInsc);
            System.out.println("Inscripcion recuperada");
            System.out.println(inscripcionRecuperada);

            //C)
            System.out.println("Alumnos ordenados por Apellido ASC");
            System.out.println(servicioAlumnos.listarAlumnosOrdenadosByApellido());

            //D)
             AlumnoDTO alumnoDTOByNroLibreta = servicioAlumnos.recuperarAlumnoPorNroLib(1);
             System.out.println("Alumno Recuperado por numero de libreta : " + alumnoDTOByNroLibreta);

             //E)
            System.out.println("Alumnos dado un genero :");
            List<AlumnoDTO> alumnosByGenero = servicioAlumnos.listarAlumnosPorGenero("Femenino");

            System.out.println(alumnosByGenero);
//TODO LATER :
//            //F)
//            servicioInscripcion.matricularAlumnoCarrera(alumnoNuevo1 , tudai);
//            servicioInscripcion.matricularAlumnoCarrera(alumnoNuevo1 , sistemas);
//
//            servicioInscripcion.matricularAlumnoCarrera(alumnoNuevo2 , tudai);
//            servicioInscripcion.matricularAlumnoCarrera(alumnoNuevo2 , sistemas);
//            servicioInscripcion.matricularAlumnoCarrera(alumnoNuevo2 , licAmbiental);
//            List<CantInscriptosCarreraDTO> f = servicioCarrera.obtenerCarrerasPorCantInscriptos();
//
//            System.out.println(f);

            //G)
            System.out.println("Alumnos de una carrera que residen en una ciudad en especifica");
            List<AlumnoDTO> alumnosByCiudadyCarrera = servicioAlumnos.listarAlumnos("tudai", "Juarez");
            System.out.println(alumnosByCiudadyCarrera);


            System.out.println(" ");
            System.out.println(" ");

            //3)
            System.out.println("Reporte inscriptos y egresados por carrera");
            System.out.println("<------------------------------------------------>");
            List<CarreraReporteDTO> carreraReporteDTO = servicioCarrera.generarReporteInscriptosEgresados();

                System.out.println(carreraReporteDTO);


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