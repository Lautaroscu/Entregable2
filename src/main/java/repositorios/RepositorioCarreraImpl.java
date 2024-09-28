package repositorios;

import entities.Carrera;
import jakarta.persistence.EntityManager;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class RepositorioCarreraImpl  implements  RepositorioCarrera{
    private EntityManager em;
    private static RepositorioCarreraImpl instance;
    private RepositorioCarreraImpl() {}
    @Override
    public Carrera recuperarCarrera(int id) {
    return null;
    }
    public synchronized static RepositorioCarreraImpl getInstance() {
        if (instance == null) {
            instance = new RepositorioCarreraImpl();
        }
        return instance;
    }
}
