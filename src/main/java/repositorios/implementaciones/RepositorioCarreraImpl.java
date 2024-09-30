package repositorios.implementaciones;

import DTOs.carrera.CantInscriptosCarreraDTO;
import DTOs.carrera.CarreraReporteDTO;
import entities.Carrera;
import entities.Inscripcion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.Getter;
import lombok.Setter;
import repositorios.BaseRepository;
import repositorios.interfaces.RepositorioCarrera;

import java.util.ArrayList;
import java.util.List;

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

        try {
            em.getTransaction().begin();  // Iniciar la transacción

            em.persist(carrera);

            em.getTransaction().commit();  // Confirmar la transacción

        } catch (Exception e) {
            if (em.getTransaction() != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();  // Si hay un error, hacer rollback
            }
            throw e;
        }
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

    @Override
    public List<CarreraReporteDTO> obtenerReporteInscriptosEgresados() {
        return em.createNamedQuery("Carrera.ReporteInscriptosEgresados", CarreraReporteDTO.class)
                .getResultList();
    }
    @Override
    public List<CantInscriptosCarreraDTO> recuperarCarrerasSortByCantInscp() {
        List<CantInscriptosCarreraDTO> result = new ArrayList<>();
     TypedQuery<CantInscriptosCarreraDTO> q=  em.createNamedQuery(Carrera.CARRERASSORTCANTINSC , CantInscriptosCarreraDTO.class);
     for (CantInscriptosCarreraDTO row : q.getResultList()) {
         Carrera c = new Carrera(row.getId_carrera() , row.getNombre());
         CantInscriptosCarreraDTO nuevo = new CantInscriptosCarreraDTO(c , row.getCantInscriptos());
         result.add(nuevo);
     }
     return result;

    }

}
