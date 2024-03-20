package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class ClubDeportivoTest {
    @Test
    void crea_Objeto_Correctamente() throws ClubException{
        String nombre = "nombre";

        ClubDeportivo club = new ClubDeportivo(nombre);

        assertNotNull(club);
        assertTrue(club.toString().contains(nombre));
    }

    @Test
    void crea_Objeto_Parametro_Invalido() throws ClubException{
        String nombre = "nombre";
        int n = 0;

        assertThrows(ClubException.class, () -> {
            new ClubDeportivo(nombre,n);
        });
    }

    @Test
    void anyadirActividad_Grupo_Es_Null() throws ClubException{
        ClubDeportivo club = new ClubDeportivo("nombre");
        Grupo grupo = null;

        assertThrows(ClubException.class, () -> {
            club.anyadirActividad(grupo);
        });
    }

    @Test 
    void anyadirActividad_Grupo_Anyadido_Correctamente() throws ClubException{
        ClubDeportivo club = new ClubDeportivo("nombre");
        Grupo grupo = new Grupo("nombre", "actividad", 5, 3, 30);

        club.anyadirActividad(grupo);

        assertTrue(club.toString().contains("nombre - actividad - 30.0 euros - P:5 - M:3"));
    }

    @Test 
    void anyadirActividad_Anyadir_Grupo_Existente() throws ClubException{
        ClubDeportivo club = new ClubDeportivo("nombre");
        Grupo grupo = new Grupo("nombre", "actividad", 5, 3, 30);
        club.anyadirActividad(grupo);

        Grupo grupo2 = new Grupo("nombre", "actividad", 7, 4, 50);
        club.anyadirActividad(grupo2);

        assertTrue(club.toString().contains("nombre - actividad - 30.0 euros - P:7 - M:3"));
    }

    @Test 
    void anyadirActividad_Anyadir_Segundo_Grupo() throws ClubException{
        ClubDeportivo club = new ClubDeportivo("nombre");
        Grupo grupo = new Grupo("nombre", "actividad", 5, 3, 30);
        club.anyadirActividad(grupo);

        Grupo grupo2 = new Grupo("nombre2", "actividad2", 7, 4, 50);
        club.anyadirActividad(grupo2);

        assertTrue(club.toString().contains("nombre2 - actividad2 - 50.0 euros - P:7 - M:4"));
    }

    @Test
    void anyadirActividad_Formato_Datos_Invalido() throws ClubException{
        ClubDeportivo club = new ClubDeportivo("nombre");
        String datos[] = {"nombre", "actividad", "cinco", "3", "30"};

        assertThrows(ClubException.class,() -> {
            club.anyadirActividad(datos);
        } );
    }

    @Test
    void anyadirActividad_Array_Grupos_Aumenta_Tamanyo_con_String_Datos() throws ClubException{
        ClubDeportivo club = new ClubDeportivo("nombre",1);
        String datos[] = {"nombre", "actividad", "5", "3", "30"};
        club.anyadirActividad(datos);
        
        String datos2[] = {"nombre2", "actividad2", "7", "2", "20"};
        club.anyadirActividad(datos2);

        assertTrue(club.toString().contains("nombre2 - actividad2 - 20.0 euros - P:7 - M:2"));
    }

    @Test
    void anyadirActividad_Array_Grupos_Aumenta_Tamanyo() throws ClubException{
        ClubDeportivo club = new ClubDeportivo("nombre",1);
        Grupo grupo = new Grupo("nombre", "actividad", 5, 3, 30);
        club.anyadirActividad(grupo);
        
        Grupo grupo2 = new Grupo("nombre2", "actividad2", 7, 4, 50);
        club.anyadirActividad(grupo2);

        assertTrue(club.toString().contains("nombre2 - actividad2 - 50.0 euros - P:7 - M:4"));
    }



    @Test
    void anyadirActividad_Datos_Anyadidos_Correctamente() throws ClubException{
        ClubDeportivo club = new ClubDeportivo("nombre");
        String datos[] = {"nombre", "actividad", "5", "3", "30"};

        club.anyadirActividad(datos);

        assertTrue(club.toString().contains("nombre - actividad - 30.0 euros - P:5 - M:3"));
    }

    @Test
    void plazasLibres_Devuelve_El_Numero_De_Plazas_Correctamente() throws ClubException{
        ClubDeportivo club = new ClubDeportivo("nombre");
        Grupo grupo = new Grupo("nombre", "actividad", 5, 3, 30);
        club.anyadirActividad(grupo);

        int resultado = club.plazasLibres("actividad");

        assertEquals(2, resultado);
    }

    @Test
    void plazasLibres_No_Existe_Grupo_Y_No_Devuelve_Numero_De_Plazas_Valido() throws ClubException{
        ClubDeportivo club = new ClubDeportivo("nombre");
        Grupo grupo = new Grupo("nombre", "actividad", 5, 3, 30);
        club.anyadirActividad(grupo);

        int resultado = club.plazasLibres("actividad2");

        assertEquals(0, resultado);
    }

    @Test
    void matricular_Numero_Personas_Invalido() throws ClubException{
        ClubDeportivo club = new ClubDeportivo("nombre");
        Grupo grupo = new Grupo("nombre", "actividad", 5, 3, 30);
        club.anyadirActividad(grupo);
        int npersonas = 3;

        assertThrows(ClubException.class, () -> {
            club.matricular("actividad", npersonas);
        });
    }

    @Test
    void matricular_Numero_Personas_Menor_Que_Plazas_Disponibles() throws ClubException{
        ClubDeportivo club = new ClubDeportivo("nombre");
        Grupo grupo = new Grupo("nombre", "actividad", 5, 3, 30);
        club.anyadirActividad(grupo);
        int npersonas = 1;

        club.matricular("actividad", npersonas);
        int resultado = club.plazasLibres("actividad");

        assertEquals(1,resultado);
    }

    @Test
    void matricular_Matricula_Varias_Actividades_Numero_Personas_Igual_Que_Plazas_Disponibles() throws ClubException{
        ClubDeportivo club = new ClubDeportivo("nombre");
        Grupo grupo = new Grupo("nombre", "actividad", 5, 3, 30);
        club.anyadirActividad(grupo);
        Grupo grupo2 = new Grupo("nombre2", "actividad2", 7, 4, 50);
        club.anyadirActividad(grupo2);
        int npersonas = 2;
        int npersonas2 = 3;

        club.matricular("actividad", npersonas);
        int resultado = club.plazasLibres("actividad");
        club.matricular("actividad2",npersonas2);
        int resultado2 = club.plazasLibres("actividad2");

        assertEquals(0,resultado);
        assertEquals(0,resultado2);
    }

    @Test
    void ingresos_Calcula_Ingresos_Correctamente() throws ClubException{
        ClubDeportivo club = new ClubDeportivo("nombre");
        Grupo grupo = new Grupo("nombre", "actividad", 5, 3, 30);
        club.anyadirActividad(grupo);

        double resultado = club.ingresos();

        assertEquals(90,resultado);
    }


}
