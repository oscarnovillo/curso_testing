package domain.usecases;

import dao.ProfesorDao;
import domain.errores.ErrorApp;
import domain.modelo.Profesor;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class AddProfesorUseCase {

    private final ProfesorDao profesorDao;

    @Inject
    public AddProfesorUseCase(ProfesorDao profesorDao) {
        this.profesorDao = profesorDao;
    }

    public Either<ErrorApp,Boolean> addProfesor(Profesor profesor) {

        return profesorDao.addProfesor(profesor);
    }
}
