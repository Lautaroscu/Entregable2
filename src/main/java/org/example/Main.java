package org.example;

import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TP");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        AlumnoId ida1 = new AlumnoId(12345 , 2);
        Alumno a2 = new Alumno(ida1 , "pepe" , "apellidoPepe" , 20 , "masculino" , "Tandil");

        Carrera c1 = new Carrera("TUDAI");
        InscripcionId insID = new InscripcionId(c1.getId_carrera() , ida1);
        Inscripcion i1 = new Inscripcion( insID,a2 , c1 , LocalDate.now());

        em.persist(c1);
        em.persist(i1);
        em.getTransaction().commit();
        em.close();
        emf.close();

    }
}