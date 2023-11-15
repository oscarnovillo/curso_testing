package data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import config.Configuracion;
import data.impl.DataBase;

import di.GsonProducer;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import modelo.Client;
import modelo.ClientNormal;
import nl.altindag.log.LogCaptor;
import nl.altindag.log.model.LogEvent;
import org.jboss.weld.junit5.EnableWeld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldSetup;
import org.jboss.weld.junit5.auto.EnableAlternatives;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(MockitoExtension.class)
@EnableWeld
//@ExtendWith(WeldJunit5AutoExtension.class)
@Log4j2
class DataBaseTest {


    //clase a probar




    @Inject
    private DataBase database;

    @WeldSetup
    public WeldInitiator weld =
             WeldInitiator.of(WeldInitiator.createWeld()
            .enableDiscovery());
   private static SeContainer container;

    @BeforeAll
    static void beforeAll() {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        container = initializer.initialize();
        //database = container.select(DataBase.class).get();

    }

    @BeforeEach
    void setUp() {
        try {
            Files.delete(Paths.get("test/data/cliente.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }

      //database = weld.select(DataBase.class).get();


    }

    @AfterAll
    static void afterAll() {
        container.close();
    }



    @Test
    void loadClientesFicheroNoExiste() {
        //given
        Map<String, Client> resultado;
        LogCaptor logCaptor = LogCaptor.forClass(DataBase.class);
        logCaptor.setLogLevelToInfo();

        //when
        resultado = database.loadClientes();

        //then

        List<LogEvent> logEvents = logCaptor.getLogEvents();
        assertThat(logEvents).hasSize(1);
        LogEvent logEvent = logEvents.get(0);
        //assertThat(logEvent.getMessage()).isEqualTo("Caught unexpected exception");
        assertThat(logEvent.getLevel()).isEqualTo("ERROR");
        assertThat(logEvent.getThrowable()).isPresent();
        assertThat(logEvent.getThrowable().get())
               // .hasMessage("KABOOM!")
                .isInstanceOf(FileNotFoundException.class);


        assertThat(resultado).isNull();
    }

@Test
void probandoLogs(){
    log.error("KABOOM! ERROR");
    log.debug("KABOOM! DEBUG");
    log.info("KABOOM! INFO");
}
    @Test
    void loadClientes() {
        //given
        Map<String, Client> resultado;
        try {
            Files.copy(Paths.get("test/data/clienteLoadTest.json"),
                    Paths.get("test/data/cliente.json"));
        } catch (IOException e) {
            assertThat(e).isNull();
        }

        //when
        resultado = database.loadClientes();

        //then
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get("1").getName()).isEqualTo("BLA");


    }

    @Test
    @Disabled
    void saveClientesMalFichero()  {
        //given
        Map<String, Client> clientes = new HashMap<>(); // Map.of(new Client("Juan", "123"));
//        try {
//            Files.delete(Paths.get("test/data/cliente.json"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //when
        database.saveClientes(clientes);

        //then
//        assertAll(() ->assertThat(new File("test/data/cliente.json")).doesNotExist(),
//                () -> assertThat(retorno).isFalse());


    }
    @Test
    void saveClientes() {
        //given
        Map<String, Client> clientes = Map.of("1", new ClientNormal("BLA", "1"));

        //when
        database.saveClientes(clientes);
//
        //then
        GsonProducer dp = new GsonProducer();
//
//
        Gson gson = dp.gson();
        Type userListType = new TypeToken<Map<String, Client>>() {
        }.getType();
//
//
        Map<String,Client> resultado = new HashMap<>();
        try(FileReader r = new FileReader("test/data/cliente.json")) {
            resultado = gson.fromJson(
                    r,
                    userListType);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        assertThat(resultado).hasSize(1);
        assertThat(resultado.get("1").getName()).isEqualTo("BLA");



        assertThat(new File("test/data/cliente.json"))
                .hasContent("{\"1\":{\"type\":\"ClientNormal\",\"name\":\"BLA\",\"dni\":\"1\",\"wallets\":[],\"shoppingCart\":[],\"previousPurchases\":[],\"allergens\":[]}}");



    }
}
