package dao;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;
import common.config.Configuracion;
import domain.errores.ErrorApp;
import domain.errores.ErrorIntegrityConstraint;
import domain.modelo.Profesor;
import io.vavr.control.Either;
import org.assertj.db.type.Table;
import org.assertj.db.type.Value;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.db.api.Assertions.assertThat;
@Testcontainers
class ProfesorDaoTest {


    @Container
    static MySQLContainer<?> mySQLContainer =  new MySQLContainer<>("mysql:8.0-debian")
            .withInitScript("initScript.sql")
            .withDatabaseName("secretaria")
            .withUsername("root")
            .withPassword("quevedo2dam")
            ;

    MysqlDataSource mysql;
    ProfesorDao dao;
    @BeforeAll
    static void beforeAll() {

        //mySQLContainer.start();
    }

    @AfterAll
    static void afterAll() {
        //mySQLContainer.stop();

    }

    @BeforeEach
    void setUp() {
        mysql = new MysqlConnectionPoolDataSource();
        mysql.setUrl(mySQLContainer.getJdbcUrl());
        mysql.setUser(mySQLContainer.getUsername());
        mysql.setPassword(mySQLContainer.getPassword());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(mysql);
        jdbcTemplate.update("delete from profesores");
        dao = new ProfesorDao(mysql);
    }

    @Test
    void dameTodos() throws SQLException {
        //given
        JdbcTemplate jdbcTemplate = new JdbcTemplate(mysql);
        jdbcTemplate.update("insert into profesores (dni, nombre)\n" +
                "values (1, 'Juan'),\n" +
                "       (2, 'Pedro'),\n" +
                "       (3, 'Maria');\n");

        //when
        List<Profesor> profesores = dao.dameTodos().get();

        //then
        assertThat(profesores).hasSize(3);
        assertThat(profesores.get(0).getDni()).isEqualTo(1);

    }

    @Test
    void addProfesor() throws SQLException {

        Profesor profesor= new Profesor("12345678A","Pepe");

        Either<ErrorApp, Boolean> respuesta = dao.addProfesor(profesor);

        assertThat(respuesta.get()).isTrue();

        Table tablaProfesores = new Table(mysql, "profesores");

        assertThat(tablaProfesores).row(0)
                .value("dni").isEqualTo("12345678A")
                .value("nombre").isEqualTo("Pepe") ;
    }


    @Test
    void addProfesorYaExiste() throws SQLException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(mysql);
        jdbcTemplate.update("insert into profesores (dni, nombre)\n" +
                "values (1, 'Pepe'),\n" +
                "       (2, 'Pedro'),\n" +
                "       (3, 'Maria');\n");


        Profesor profesor= new Profesor("1","Pepe");
        ProfesorDao dao = new ProfesorDao(mysql);

        Either<ErrorApp, Boolean> respuesta = dao.addProfesor(profesor);

        assertThat(respuesta.getLeft()).isInstanceOf(ErrorIntegrityConstraint.class);
        assertThat(respuesta.getLeft().getMensaje()).isEqualTo("dni ya existe");
        Table tablaProfesores = new Table(mysql, "profesores");

        assertThat(tablaProfesores).row(0)
                .value("dni").isEqualTo("1")
                .value("nombre").isEqualTo("Pepe") ;

    }


}
