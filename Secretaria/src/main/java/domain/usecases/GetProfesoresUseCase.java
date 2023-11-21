package domain.usecases;

import dao.ProfesorDao;
import domain.errores.ErrorApp;
import domain.modelo.Profesor;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetProfesoresUseCase {


    private final ProfesorDao profesorDao;

    @Inject
    public GetProfesoresUseCase(ProfesorDao profesorDao) {
        this.profesorDao = profesorDao;
    }

    public Either<ErrorApp, List<Profesor>> dameTodos() {
        return profesorDao.dameTodos();
    }

}
