package entities;

import DTOs.CarreraDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@Table(name = "carrera")
@NamedQuery(
        name = "Carrera.ReporteInscriptosEgresados",
        query = "SELECT new DTOs.CarreraReporteDTO(c.nombre, YEAR(i.fechaInscripcion), " +
                "COUNT(i), " +
                "SUM(CASE WHEN i.seGraduo = TRUE THEN 1 ELSE 0 END)) " +
                "FROM Inscripcion i " +
                "JOIN i.carrera c " +
                "GROUP BY c.nombre, YEAR(i.fechaInscripcion) " +
                "ORDER BY c.nombre, YEAR(i.fechaInscripcion)"
)
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_carrera;

    @Column
    private String nombre;

    public Carrera() {
    }

    public Carrera(String nombre) {
        this.nombre = nombre;
    }

    public Carrera (CarreraDTO carreraDTO) {
        this.id_carrera = carreraDTO.getId_carrera();
        this.nombre = carreraDTO.getNombre();
    }
}
