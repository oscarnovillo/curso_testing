package servicios.impl;

import data.DaoClients;
import data.impl.DaoClientsImpl;
import modelo.Client;
import modelo.ClientNormal;
import modelo.ClientWithDiscount;
import modelo.Ingredient;
import modelo.error.ErrorClientAccounts;
import modelo.error.ErrorIngredient;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import servicios.ServicesClients;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicesClientsImplTest {


    //class under test
    @InjectMocks
    ServicesClientsImpl servicesClients;

    //dependencias
    @Mock
    DaoClients daoClients;


    @BeforeEach
    void setUp() {


    }

    @Test
    void isEmptyClientList() {
        //given
        when(daoClients.isEmptyClientList()).thenReturn(true);

        //when
        boolean respuesta = servicesClients.isEmptyClientList();

        //then
        assertThat(respuesta).isTrue();
    }

    @Test
    void printClientList() {
        //given

        List<Client> list = List.of(new ClientNormal("123", "pepe"));
        when(daoClients.showClientList()).thenReturn(list);

        //when
        List<Client> lista = servicesClients.printClientList();

        //then
        assertThat(lista).containsAll(list);
    }

    @Test
    void getClient() {
        //given

        Client clientPrueba = new ClientNormal("123", "pepe");
        when(daoClients.getClient(any())).thenReturn(clientPrueba);

        //when
        Client respuesta = servicesClients.getClient("123");
        //then
        assertThat(respuesta).isEqualTo(clientPrueba);
    }

    @Test
    void isClientWithDiscount() {
        try (LogCaptor logCaptor = LogCaptor.forClass(ServicesClientsImpl.class)) {
            ClientWithDiscount c = new ClientWithDiscount("123", "pepe", 10);
            servicesClients.isClientWithDiscount(c);

            assertThat(logCaptor.getDebugLogs().get(0)).isEqualTo("isClientWithDiscount: ");
        }

    }

    @Test
    void addClient() {
    }

    @Test
    void changeDni() {
    }

    @Test
    void changeName() {
    }

    @Test
    @DisplayName("Añadir alérgeno que existe")
    void addAllergen() {

        //given
        Client cli = new ClientNormal("123", "pepe");
        Ingredient ing = new Ingredient("cacahuete");
        when(daoClients.containsAllergen(cli, ing)).thenReturn(true);

        //when
        ErrorIngredient error = servicesClients.addAllergen(cli, ing);

        //then
        assertThat(error).isEqualTo(ErrorIngredient.DUPLICATED);

    }

    @Test
    @DisplayName("Añadir alérgeno que no existe")
    void addAllergenNoExiste() {

        //given
        Client cli = new ClientNormal("123", "pepe");
        Ingredient ing = new Ingredient("cacahuete");
        when(daoClients.containsAllergen(cli, ing)).thenReturn(false);

        //when
        ErrorIngredient error = servicesClients.addAllergen(cli, ing);

        //then
        assertThat(error).isNull();
        verify(daoClients).addAllergen(cli, ing);

    }


    @Nested
    @DisplayName("Test de getClient")
    class ContainsClient {
        @Test
        void containsClient() {
            //given
            String dni = "125";
            when(daoClients.containsClient(dni)).thenReturn(true);

            //when
            ErrorClientAccounts error = servicesClients.containsClient(dni);

            //then
            assertThat(error).isNull();

        }

        @Test
        void notContainsClient() {
            //given
            String dni = "123";
            when(daoClients.containsClient(dni)).thenReturn(false);

            //when
            ErrorClientAccounts error = servicesClients.containsClient(dni);

            //then
            assertThat(error).isEqualTo(ErrorClientAccounts.NOT_FOUND);

        }
    }

    @Nested
    class removeCliente {

        @Test
        void removeClientNoExiste() {

            //given
            String dni = "123";
            when(daoClients.containsClient(dni)).thenReturn(false);

            //when
            ErrorClientAccounts respuesta = servicesClients.removeClient(dni);

            //then

            assertThat(respuesta).isEqualTo(ErrorClientAccounts.NOT_FOUND);

        }


        @Test
        void removeClientExiste() {

            //given
            String dni = "123";
            when(daoClients.containsClient(dni)).thenReturn(true);

            //when
            ErrorClientAccounts respuesta = servicesClients.removeClient(dni);

            //then

            assertThat(respuesta).isNull();
            verify(daoClients, times(1)).removeClient(dni);
        }

    }
}
