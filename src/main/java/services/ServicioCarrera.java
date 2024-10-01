package services;

import DTOs.carrera.CantInscriptosCarreraDTO;
import DTOs.carrera.CarreraDTO;
import DTOs.carrera.CarreraMapper;
import DTOs.carrera.CarreraReporteDTO;
import entities.Carrera;
import repositorios.interfaces.RepositorioCarrera;

import java.util.ArrayList;
import java.util.List;

public class ServicioCarrera {

    private CarreraMapper carreraMapper;
    private CantInscriptosCarreraDTO cantInscriptosCarreraDTO;
    private RepositorioCarrera repositorioCarrera;

    public ServicioCarrera(RepositorioCarrera repositorioCarrera) {
        this.carreraMapper = new CarreraMapper();
        this.repositorioCarrera = repositorioCarrera;
    }

    public CarreraDTO obtenerCarrera(int id) {
        Carrera carrera = repositorioCarrera.recuperarCarrera(id);
        CarreraDTO carreraDTO = carreraMapper.apply(carrera);
        return carreraDTO;
    }

    public void adicionarCarrera(Carrera carrera) {
        repositorioCarrera.adicionarCarrera(carrera);
    }

    public void removerCarrera(int id) {
        repositorioCarrera.removerCarrera(id);
    }

    public void modificarCarrera(CarreraDTO carrera) {
        Carrera c = transformarDtoAEntidad(carrera);
        repositorioCarrera.modificarCarrera(c);
    }

    public List<CarreraReporteDTO> generarReporteInscriptosEgresados() {
        return repositorioCarrera.obtenerReporteInscriptosEgresados();
    }

    private Carrera transformarDtoAEntidad(CarreraDTO carreraDTO) {
        Carrera c = new Carrera(carreraDTO.getId_carrera(), carreraDTO.getNombre());
        return c;
    }
    public List<CantInscriptosCarreraDTO> obtenerCarrerasPorCantInscriptos() {
      return repositorioCarrera.recuperarCarrerasSortByCantInscp();

    }

}
