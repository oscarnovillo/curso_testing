package dao;

import domain.errores.ErrorApp;
import domain.errores.ErrorIntegrityConstraint;
import domain.errores.ErrorNoConection;
import domain.modelo.Profesor;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.sql.DataSource;
import java.util.List;

public class ProfesorDao {


    private final DataSource dataSource;


    @Inject
    public ProfesorDao(
            DataSource dataSource) {
        this.dataSource = dataSource;

    }

    public Either<ErrorApp, List<Profesor>> dameTodos() {
        try {
            JdbcTemplate jtm = new JdbcTemplate(
                    dataSource);


            // select devuelve LIST
            return Either.right(jtm.query("Select * from profesores",
                    BeanPropertyRowMapper.newInstance(Profesor.class)));
        } catch (Exception e) {
            return Either.left(new ErrorNoConection(e.getMessage()));
        }
    }

    public Either<ErrorApp, Boolean> addProfesor(Profesor profesor) {
        try {
            JdbcTemplate jtm = new JdbcTemplate(
                    dataSource);
            jtm.update("insert into profesores (dni,nombre) values (?,?)",
                    profesor.getDni(), profesor.getNombre());
            // select devuelve LIST
            return Either.right(true);
        } catch(DuplicateKeyException e){
            return Either.left(new ErrorIntegrityConstraint("dni ya existe"));
        } catch (Exception e) {
            return Either.left(new ErrorNoConection(e.getMessage()));
        }
    }
}
