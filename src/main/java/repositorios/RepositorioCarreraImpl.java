package repositorios;

import entities.Carrera;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepositorioCarreraImpl extends BaseRepository implements RepositorioCarrera {

    public RepositorioCarreraImpl(EntityManager em) {
        super.em = em;
    }

    @Override
    public Carrera recuperarCarrera(int id) {
        Carrera c = em.find(Carrera.class, id);
        return c;
    }

    @Override
    public void adicionarCarrera(Carrera carrera) {
        em.persist(carrera);
    }

    @Override
    public void removerCarrera(int id) {
        Carrera carrera = recuperarCarrera(id);
        if (carrera != null) {
            em.remove(carrera);
        }
    }

    @Override
    public void modificarCarrera(Carrera carrera) {
        if (em.contains(carrera)) {
            em.merge(carrera);
        }
    }
}
