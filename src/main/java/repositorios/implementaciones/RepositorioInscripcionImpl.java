package repositorios.implementaciones;

import entities.Alumno;
import entities.Carrera;
import entities.Inscripcion;
import entities.InscripcionId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import repositorios.BaseRepository;
import repositorios.interfaces.RepositorioInscripcion;

import java.time.LocalDate;
import java.util.List;

public class RepositorioInscripcionImpl extends BaseRepository implements RepositorioInscripcion {

    public RepositorioInscripcionImpl(EntityManager em) {
        super.em = em;
    }

    @Override
    public Inscripcion obtenerInscripcion(InscripcionId id) {
        Inscripcion i = em.find(Inscripcion.class, id);
        return i;
    }

    private void agregarInscripcion(Inscripcion inscripcion) {
        if(inscripcion != null) {
            try {
                em.getTransaction().begin();  // Iniciar la transacción

                em.persist(inscripcion);

                em.getTransaction().commit();  // Confirmar la transacción

            } catch (Exception e) {
                if (em.getTransaction() != null && em.getTransaction().isActive()) {
                    em.getTransaction().rollback();  // Si hay un error, hacer rollback
                }
                throw e;
            }        }
    }

    @Override
    public void modificarInscripcion(Inscripcion inscripcion) {
        if(inscripcion != null) {
            em.merge(inscripcion);
        }
    }

    @Override
    public void eliminarInscripcion(InscripcionId id) {
        Inscripcion i = obtenerInscripcion(id);
        if(i != null) {
            em.remove(i);
        }
    }

    @Override
    public void matricularAlumnoCarrera(Alumno alumno, Carrera carrera) {
        Inscripcion inscripcion = new Inscripcion(alumno, carrera, LocalDate.now());
        this.agregarInscripcion(inscripcion);
    }


}
