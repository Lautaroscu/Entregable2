package DTOs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class CarreraReporteDTO {
    private String nombreCarrera;
    private int anio;
    private long cantidadInscriptos;
    private long cantidadEgresados;

    public CarreraReporteDTO(String nombreCarrera, int anio, long cantidadInscriptos, long cantidadEgresados) {
        this.nombreCarrera = nombreCarrera;
        this.anio = anio;
        this.cantidadInscriptos = cantidadInscriptos;
        this.cantidadEgresados = cantidadEgresados;
    }
}
