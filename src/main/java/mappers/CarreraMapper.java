package mappers;

import DTOs.CarreraDTO;
import entities.Carrera;

import java.util.function.Function;

public class CarreraMapper implements Function<Carrera, CarreraDTO> {
    @Override
    public CarreraDTO apply(Carrera carrera) {
        return new CarreraDTO(carrera);
    }

    public Carrera toEntity(CarreraDTO carreraDTO) {
        return new Carrera(carreraDTO);
    }
}