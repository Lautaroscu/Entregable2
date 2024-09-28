package repositorios;

import entities.Alumno;
import entities.Carrera;
import entities.Inscripcion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.List;

public class RepositorioInscripcionImpl implements RepositorioInscripcion{
    private EntityManager em;

    public RepositorioInscripcionImpl(EntityManager em) {
        this.em = em;
    }
    @Override
    public void matricularAlumnoCarrera(Alumno alumno, Carrera carrera) {
        Inscripcion inscripcion = new Inscripcion(alumno , carrera , LocalDate.now());
        em.persist(inscripcion);
    }

    @Override
    public List<Carrera> recuperarCarrerasSortByCantInscp() {
        Query q = em.createNamedQuery(Inscripcion.CARRERASSORTCANTINSC);
        return q.getResultList();
    }
}
