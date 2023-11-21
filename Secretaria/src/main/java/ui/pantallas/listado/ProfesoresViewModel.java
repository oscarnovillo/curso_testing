package ui.pantallas.listado;


import domain.modelo.Profesor;
import domain.usecases.AddProfesorUseCase;
import domain.usecases.GetProfesoresUseCase;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ProfesoresViewModel {


    private final ObjectProperty<ProfesoresState> _state;
    private final AddProfesorUseCase addProfesorUseCase;
    private final GetProfesoresUseCase getProfesoresUseCase;

    @Inject
    public ProfesoresViewModel(AddProfesorUseCase addProfesorUseCase, GetProfesoresUseCase getProfesoresUseCase) {
        this.addProfesorUseCase = addProfesorUseCase;
        this.getProfesoresUseCase = getProfesoresUseCase;
        _state = new SimpleObjectProperty<>(new ProfesoresState(null, null, false));


    }

    public ReadOnlyObjectProperty<ProfesoresState> getState() {
        return _state;
    }


    public void loadProfesores()
    {
        getProfesoresUseCase.dameTodos()
                .peek(profesores -> {
                    _state.setValue(
                            new ProfesoresState(profesores, null, false)
                    );
                })
                .peekLeft(errorApp -> {
                    _state.setValue(
                            new ProfesoresState(null, errorApp.getMensaje(), false)
                    );
                });
    }

    public void addProfesor(Profesor profesor)
    {
        addProfesorUseCase.addProfesor(profesor)
                .peek(aBoolean -> {
                    loadProfesores();
                })
                .peekLeft(errorApp -> {
                    _state.setValue(
                            new ProfesoresState(null, errorApp.getMensaje(), false)
                    );
                });
    }

}
