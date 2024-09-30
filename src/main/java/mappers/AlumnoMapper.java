package mappers;

import DTOs.AlumnoDTO;
import entities.Alumno;

import java.util.function.Function;

public class AlumnoMapper implements Function<Alumno, AlumnoDTO> {
    @Override
    public AlumnoDTO apply(Alumno alumno) {
        return new AlumnoDTO(alumno);
    }

    public Alumno toEntity(AlumnoDTO alumnoDTO) {
        return new Alumno(alumnoDTO);
    }
}
