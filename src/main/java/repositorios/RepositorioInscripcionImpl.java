package repositorios;

import entities.Alumno;
import entities.Carrera;
import entities.Inscripcion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.time.LocalDate;
import java.util.List;

public class RepositorioInscripcionImpl extends BaseRepository implements RepositorioInscripcion {

    public RepositorioInscripcionImpl(EntityManager em) {
        super.em = em;
    }

    @Override
    public Inscripcion obtenerInscripcion(int id) {
        Inscripcion i = em.find(Inscripcion.class, id);
        return i;
    }

    @Override
    public void agregarInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null) {
            em.persist(inscripcion);
        }
    }

    @Override
    public void modificarInscripcion(Inscripcion inscripcion) {
        if (inscripcion != null) {
            em.merge(inscripcion);
        }
    }

    @Override
    public void eliminarInscripcion(int id) {
        Inscripcion i = obtenerInscripcion(id);
        if (i != null) {
            em.remove(i);
        }
    }

    @Override
    public void matricularAlumnoCarrera(Alumno alumno, Carrera carrera) {
        Inscripcion inscripcion = new Inscripcion(alumno, carrera);
        em.persist(inscripcion);
    }

    @Override
    public List<Carrera> recuperarCarrerasSortByCantInscp() {
        Query q = em.createNamedQuery(Inscripcion.CARRERASSORTCANTINSC);
        return q.getResultList();
    }
}
