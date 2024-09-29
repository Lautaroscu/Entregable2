package services;

import DTOs.CarreraDTO;
import DTOs.CarreraMapper;
import entities.Carrera;
import repositorios.RepositorioCarrera;

public class ServicioCarrera {

    private CarreraMapper carreraMapper;
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

    public void adicionarCarrera(CarreraDTO carrera) {
        Carrera c = transformarDtoAEntidad(carrera);
        repositorioCarrera.adicionarCarrera(c);
    }

    public void removerCarrera(int id) {
        repositorioCarrera.removerCarrera(id);
    }

    public void modificarCarrera(CarreraDTO carrera) {
        Carrera c = transformarDtoAEntidad(carrera);
        repositorioCarrera.modificarCarrera(c);
    }

    private Carrera transformarDtoAEntidad(CarreraDTO carreraDTO) {
        Carrera c = new Carrera(carreraDTO.getId_carrera(), carreraDTO.getNombre());
        return c;
    }
}
