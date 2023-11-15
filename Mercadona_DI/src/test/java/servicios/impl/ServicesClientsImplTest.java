package servicios.impl;

import data.DaoClients;
import modelo.Client;
import modelo.ClientNormal;
import modelo.ClientWithDiscount;
import modelo.Ingredient;
import modelo.error.ErrorClientAccounts;
import modelo.error.ErrorIngredient;
import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicesClientsImplTest {


    //class under test
    @InjectMocks
    ServicesClientsImpl servicesClients;

    //dependencias
    @Mock
    DaoClients daoClients;


    @Captor
    ArgumentCaptor<Client> captorCliente;

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

    @Nested
    class AddClient {
        @Test
        @DisplayName("añadir cliente que ya existe")
        void addClientDuplicado() {
            //given
            Client c = new ClientNormal("123", "pepe");
            when(daoClients.containsClient(c)).thenReturn(true);
            //when
            ErrorClientAccounts error = servicesClients.addClient(c);

            //then
            assertThat(error).isEqualTo(ErrorClientAccounts.DUPLICATED);

        }

        @Test
        @DisplayName("añadir cliente normal que no existe, happy path")
        void addClientSinDescuento() {
            //given
            Client c = new ClientNormal("pepe", "123");
            when(daoClients.containsClient(c)).thenReturn(false);
            //when
            ErrorClientAccounts error = servicesClients.addClient(c);

            //then
            assertAll(() -> assertThat(error).isNull(),
                    () -> verify(daoClients).addClient(captorCliente.capture()),
                    () -> assertThat(captorCliente.getValue().getDni()).isEqualTo("123"),
                    () -> assertThat(captorCliente.getValue().getName()).isEqualTo("pepe")
            );

        }

        @Test
        @DisplayName("añadir cliente con descuento , happy path")
        void addClientConDescuento() {
            //given
            Client c = new ClientWithDiscount("pepe", "123", 70);
            when(daoClients.containsClient(c)).thenReturn(false);
            //when
            ErrorClientAccounts error = servicesClients.addClient(c);

            //then
            assertAll(() -> assertThat(error).isNull(),
                    () -> verify(daoClients).addClient(captorCliente.capture()),
                    () -> assertThat(captorCliente.getValue().getDni()).isEqualTo("123"),
                    () -> assertThat(captorCliente.getValue().getName()).isEqualTo("pepe")
            );

        }

        @Test
        @DisplayName("añadir cliente con descuento negativo")
        void addClientConDescuentoNegativo() {
            //given
            Client c = new ClientWithDiscount("pepe", "123", -12);
            when(daoClients.containsClient(c)).thenReturn(false);
            //when
            ErrorClientAccounts error = servicesClients.addClient(c);

            //then
            assertAll(() -> assertThat(error).isEqualTo(ErrorClientAccounts.LOW_DISCOUNT),
                    () -> verify(daoClients, times(0)).addClient(any())
            );

        }

        @Test
        @DisplayName("añadir cliente con descuento demasiado grande")
        void addClientConDescuentoDemasiadoGrande() {
            //given
            Client c = new ClientWithDiscount("pepe", "123", 112);
            when(daoClients.containsClient(c)).thenReturn(false);
            //when
            ErrorClientAccounts error = servicesClients.addClient(c);

            //then
            assertAll(() -> assertThat(error).isEqualTo(ErrorClientAccounts.HIGH_DISCOUNT),
                    () -> verify(daoClients, times(0)).addClient(any())
            );

        }

    }



    @Test
    void changeDni() {
    }

    @Test
    void changeName() {
    }

    @Test
    @DisplayName("añadir alergeno que existe")
    void addAllergen() {
        //GIVEN
        Client c = new ClientNormal("123", "pepe");
        Ingredient i = new Ingredient("cacahuete");
        when(daoClients.containsAllergen(c, i)).thenReturn(true);

        //when
        ErrorIngredient error = servicesClients.addAllergen(c, i);

        //then
        assertThat(error).isEqualTo(ErrorIngredient.DUPLICATED);
    }

    @Test
    @DisplayName("añadir alergeno que no existe")
    void addAllergenNoExiste() {
        //GIVEN
        Client c = new ClientNormal("123", "pepe");
        Ingredient i = new Ingredient("cacahuete");
        when(daoClients.containsAllergen(c, i)).thenReturn(false);

        //when
        ErrorIngredient error = servicesClients.addAllergen(c, i);

        //then
        assertThat(error).isNull();
        verify(daoClients, times(2)).addAllergen(c, i);
    }


    @Nested
    @DisplayName("Test de getClient")
    class ContainsClient {
        @Test
        void containsClient() {
            //given
            String dni = "123";
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
