package ui.pantallas.listado;


import domain.usecases.AddProfesorUseCase;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.List;

public class ListadoViewModel {


    private final ObjectProperty<ListadoState> _state;
    private final AddProfesorUseCase loginUseCase;

    @Inject
    public ListadoViewModel(AddProfesorUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;

//        ListadoState ls = null;
//        List<Cromo> cromos = loginUseCase.loadCromos();
//        if (cromos == null)
//            ls = new ListadoState(null, "no se han podido cargar cromos", false);
//        else
//            ls = new ListadoState(cromos, null, false);
//
        _state = new SimpleObjectProperty<>(new ListadoState(null, null, false));
    }

    public ReadOnlyObjectProperty<ListadoState> getState() {
        return _state;
    }

    public void loadCromos() {
        ListadoState ls = null;
//        List<Cromo> cromos = loginUseCase.loadCromos();
//        if (cromos == null)
//            ls = new ListadoState(null, "no se han podido cargar cromos", false);
//        else
//            ls = new ListadoState(cromos, null, false);
        _state.setValue(ls);
    }




}
