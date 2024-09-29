package org.example;

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

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TP");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        //Instancias singleton repositorios
        RepositorioAlumnoImpl repoAlumnos =  FactoryRepositorios.getRepositorioAlumno(em);
        RepositorioInscripcionImpl repoInscripciones = FactoryRepositorios.getRepositorioInscripcion(em);
        RepositorioCarreraImpl repoCarreras = FactoryRepositorios.getRepositorioCarrera(em);


        ServicioAlumno servicioAlumnos = new ServicioAlumno(repoAlumnos, repoCarreras);
        ServicioCarrera servicioCarrera = new ServicioCarrera(repoCarreras);
        ServicioInscripcion servicioInscripcion = new ServicioInscripcion(repoInscripciones);

        System.out.println();

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}