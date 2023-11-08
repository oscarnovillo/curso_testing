package servicios.impl;

import data.DaoClients;
import data.impl.DaoClientsImpl;
import modelo.Client;
import modelo.ClientNormal;
import modelo.Ingredient;
import modelo.error.ErrorClientAccounts;
import modelo.error.ErrorIngredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Nested;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServicesClientsImplTest {
    //class under test
    @InjectMocks
    ServicesClientsImpl servicesClients;  //La clase en la que estás probando
    //dependencias
    @Mock
    DaoClients daoClients;  //La clase de la que neceistas objetos para los mocks
    @BeforeEach
    void setUp() {
//        daoClients = mock(DaoClientsImpl.class);
//        servicesClients = new ServicesClientsImpl(daoClients);

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
    }

    @Test
    void getClient() {
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


    @Test
    void isClientWithDiscount() {
    }

    @Test
    void addClient() {
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

    @Test
    void changeDni() {
    }

    @Test
    void changeName() {
    }


    @Test
    void addAllergenOK() {
        Client cli=new ClientNormal("123", "pepe");
        Ingredient ing=new Ingredient("pera");
        //given
        when(daoClients.containsAllergen(cli, ing)).thenReturn(false);
        //when
        boolean respuesta = servicesClients.isEmptyClientList();
        ErrorIngredient error=  servicesClients.addAllergen(cli,ing);
        //then
        assertThat(error).isNull();
        verify(daoClients).addAllergen(cli,ing);
    }
    @Test
    void addAllergenError() {
        Client cli=new ClientNormal("123", "pepe");
        Ingredient ing=new Ingredient("pera");

        //given
        when(daoClients.containsAllergen(cli, ing)).thenReturn(true);

        //when
        boolean respuesta = servicesClients.isEmptyClientList();
        ErrorIngredient error=  servicesClients.addAllergen(cli,ing);

        //then
        assertThat(error).isEqualTo(ErrorIngredient.DUPLICATED);
    }
}
