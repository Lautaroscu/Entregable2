package mappers;

import DTOs.InscripcionDTO;
import entities.Inscripcion;

import java.util.function.Function;

public class InscripcionMapper implements Function<Inscripcion, InscripcionDTO> {
    @Override
    public InscripcionDTO apply(Inscripcion inscripcion) {
        return new InscripcionDTO(inscripcion);
    }

    public Inscripcion toEntity(InscripcionDTO inscripcionDTO) {
        return new Inscripcion(inscripcionDTO);
    }
}