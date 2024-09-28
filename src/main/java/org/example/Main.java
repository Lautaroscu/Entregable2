package org.example;

import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repositorios.RepositorioAlumno;
import repositorios.RepositorioAlumnoImpl;
import services.ServicioAlumno;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TP");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        ServicioAlumno sa = new ServicioAlumno(em);

    System.out.println();


        em.getTransaction().commit();

        em.close();
        emf.close();

    }
}