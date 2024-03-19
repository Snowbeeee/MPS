package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;


public class ClubDeportivoAltoRendimientoTest {
    @Test
    void crea_Objeto_Sin_tam_Correctamente() throws ClubException {
        String nombre = "nombre";
        int maximo = 5;
        int incremento = 2;

        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);

        assertNotNull(club);
   }

    @Test
    void crea_Objeto_Sin_tam_Parametro_maximo_Invalido() throws ClubException {
        String nombre = "nombre";
        int maximo = 0;
        int incremento = 2;

        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);
        }); 
    }

    @Test
    void crea_Objeto_Sin_tam_Parametro_incremento_Invalido() throws ClubException {
        String nombre = "nombre";
        int maximo = 2;
        int incremento = 0;

        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);
        }); 
    }

    @Test
    void crea_Objeto_Con_tam_Correctamente() throws ClubException {
        String nombre = "nombre";
        int tam = 5;
        int maximo = 5;
        int incremento = 2;

        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);

        assertNotNull(club);
   }

    @Test
    void crea_Objeto_Con_tam_Parametro_maximo_Invalido() throws ClubException {
        String nombre = "nombre";
        int tam = 5;
        int maximo = 0;
        int incremento = 1;

        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);
        }); 
     }

    @Test
    void crea_Objeto_Con_tam_Parametro_incremento_Invalido() throws ClubException {
        String nombre = "nombre";
        int tam = 10;
        int maximo = 6;
        int incremento = 0;

        assertThrows(ClubException.class, () -> {
            new ClubDeportivoAltoRendimiento(nombre, tam, maximo, incremento);
        }); 
    }

    @Test
    void anyadirActividad_Parametro_Datos_Longitud_Invalida() throws ClubException {
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("nombre", 6, 1);
        String datos[] = {"nombre", "actividad", "5", "3"};

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datos);
        });
    }

    @Test
    void anyadirActividad_Formato_Datos_Invalido() throws ClubException {
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("nombre", 6, 1);
        String datos[] = {"nombre", "actividad", "cinco", "3","30"};

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(datos);
        });
    }

    @Test
    void anyadirActividad_Numero_Plazas_Del_Array_Datos_Se_Cambia_Al_Maximo_Del_Club() throws ClubException {
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("nombre", 6, 1);
        String datos[] = {"nombre", "actividad", "10", "3","30"};

        club.anyadirActividad(datos);

        assertTrue(club.toString().contains("P:6"));
    }

    @Test
    void anyadirActividad_Numero_Matriculados_Se_Cambia_Al_Maximo_Del_Club() throws ClubException {
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("nombre", 6, 1);
        String datos[] = {"nombre", "actividad", "10", "7","30"};

        club.anyadirActividad(datos);

        assertTrue(club.toString().contains("P:6"));
    }



    @Test 
    void ingresos_Calcula_Correctamente() throws ClubException {
        ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("nombre", 5, 1);
        String datos[] = {"nombre", "actividad", "5", "3", "30"};
        club.anyadirActividad(datos);

        double resultado = club.ingresos();

        assertEquals(90.9, resultado);
    }

}
