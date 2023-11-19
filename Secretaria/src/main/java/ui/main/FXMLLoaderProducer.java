package ui.main;


import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import javafx.fxml.FXMLLoader;

public class FXMLLoaderProducer {

    @Produces
    public FXMLLoader createLoader(Instance<Object> instance) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(controller -> instance.select(controller).get());
        return loader;
    }
}
