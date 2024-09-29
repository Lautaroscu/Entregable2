package repositorios;

import DTOs.CarreraReporteDTO;
import entities.Carrera;
import jakarta.persistence.EntityManager;

import java.util.List;

public interface RepositorioCarrera {
    public Carrera recuperarCarrera(int id);

    public void adicionarCarrera(Carrera carrera);

    public void removerCarrera(int id);

    public void modificarCarrera(Carrera carrera);

    public List<CarreraReporteDTO> obtenerReporteInscriptosEgresados();
}
