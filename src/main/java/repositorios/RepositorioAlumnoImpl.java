package repositorios;

import entities.Alumno;
import entities.Carrera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RepositorioAlumnoImpl extends BaseRepository implements RepositorioAlumno {

    public RepositorioAlumnoImpl(EntityManager em) {
        super.em = em;
    }

    @Override
    public void altaAlumno(Alumno alumno) {
        if (alumno != null)
            em.persist(alumno);
    }

    @Override
    public void bajaAlumno(int nroLibreta) {
        Alumno a = this.recuperarAlumnoPorNroLib(nroLibreta);
        if (a != null)
            em.remove(a);
        else
            throw new IllegalArgumentException("El alumno no existe");
    }

    @Override
    public void modificarAlumno(Alumno alumno) {
        if (alumno == null || alumno.getNro_libreta() == 0) {
            throw new IllegalArgumentException("El alumno debe existir en la base de datos");
        }
        em.merge(alumno);
    }

    @Override
    public List<Alumno> listarAlumnos() {
        return em.createNamedQuery(Alumno.LISTARALUMNOS, Alumno.class).getResultList();
    }

    @Override
    public List<Alumno> recuperarAlumnosSortByNroLib() {
        return em.createNamedQuery(Alumno.ALUMNOSSORTNROLIB, Alumno.class).getResultList();
    }

    @Override
    public Alumno recuperarAlumnoPorNroLib(int nroLib) {
        return em.find(Alumno.class, nroLib);
    }

    @Override
    public List<Alumno> recuperarAlumnosPorGenero(String genero) {
        TypedQuery<Alumno> q = em.createNamedQuery(Alumno.ALUMNOSPORGENERO, Alumno.class);
        q.setParameter("genero", genero);
        return q.getResultList();
    }

    @Override
    public List<Alumno> recuperarAlumnosPorCarrerayCiudad(Carrera carrera, String ciudad) {
        TypedQuery<Alumno> q = em.createNamedQuery(Alumno.ALUMNOCARRERAYCIUDAD, Alumno.class);
        q.setParameter("ciudad", ciudad);
        q.setParameter("carrera", carrera);
        return q.getResultList();
    }
}
