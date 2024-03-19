/*
 * @autor1 = Eduardo García Rivas
 * @autor2 = María Victoria Huesca Peláez
 */

package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class GrupoTest {
    @Test
    void crea_Objeto_Parametro_nplazas_Invalido() throws ClubException{
        String codigo = "codigo"; 
        String actividad = "actividad"; 
        int nplazas = 0;  
        int matriculados = 0;
        double tarifa = 20;

        assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }

    @Test
    void crea_Objeto_Parametro_matriculados_Invalido() throws ClubException{
        String codigo = "codigo"; 
        String actividad = "actividad"; 
        int nplazas = 1;  
        int matriculados = -1;
        double tarifa = 20;

        assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }

    @Test
    void crea_Objeto_Parametro_tarifa_Invalido() throws ClubException{
        String codigo = "codigo"; 
        String actividad = "actividad"; 
        int nplazas = 1;  
        int matriculados = 1;
        double tarifa = -20;

        assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }

    @Test
    void crea_Objeto_Parametro_Matriculados_Invalido() throws ClubException{
        String codigo = "codigo"; 
        String actividad = "actividad"; 
        int nplazas = 2;  
        int matriculados = 3;
        double tarifa = 30;

        assertThrows(ClubException.class, () -> {
            new Grupo(codigo, actividad, nplazas, matriculados, tarifa);
        });
    }

    @Test
    void getCodigo_Devuelve_Correctamente() throws ClubException {
        String codigo = "codigo";

        Grupo grupo = new Grupo(codigo, "actividad", 3, 2, 30);
        String resultado  = grupo.getCodigo();

        assertEquals("codigo",resultado);

    }

    @Test
    void actualizarPlazas_Parametro_n_Invalido() throws ClubException {
        int n = 0;
        Grupo grupo = new Grupo("codigo", "actividad", 3, 2, 30);

        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(n);
        });
    }

    @Test
    void actualizarPlazas_Parametro_n_Menor_Que_Matriculados() throws ClubException {
        int n = 1;
        Grupo grupo = new Grupo("codigo", "actividad", 3, 2, 30);

        assertThrows(ClubException.class, () -> {
            grupo.actualizarPlazas(n);
        });
    }

    @Test
    void matricular_No_Hay_Plazas_Libres() throws ClubException {
        int n = 2;
        Grupo grupo = new Grupo("codigo", "actividad", 3, 2, 30);

        assertThrows(ClubException.class, () -> {
            grupo.matricular(n);
        });
    }

    @Test
    void matricular_Parametro_n_Invalido() throws ClubException {
        int n = 0;
        Grupo grupo = new Grupo("codigo", "actividad", 3, 2, 30);

        assertThrows(ClubException.class, () -> {
            grupo.matricular(n);
        });
    }

    @Test
    void equals_Devuelve_False_Cuando_Ambos_Codigo_Y_Actividad_Son_Distintos() throws ClubException {
        Grupo grupo = new Grupo("codigo", "actividad", 3, 2, 30);
        Grupo grupo2 = new Grupo("codigo2", "actividad2", 5, 1, 60);

        boolean resultado = grupo.equals(grupo2);

        assertFalse(resultado);
    }

    @Test
    void equals_Devuelve_False_Solo_Cuando_Actividad_Es_Distinto() throws ClubException {
        Grupo grupo = new Grupo("codigo", "actividad", 3, 2, 30);
        Grupo grupo2 = new Grupo("codigo", "actividad2", 5, 1, 60);

        boolean resultado = grupo.equals(grupo2);

        assertFalse(resultado);
    }

    @Test
    void equals_Devuelve_False_Cuando_El_Parametro_No_Es_De_Tipo_Grupo() throws ClubException {
        Grupo grupo = new Grupo("codigo", "actividad", 3, 2, 30);
        ClubDeportivo club = null;

        boolean resultado = grupo.equals(club);

        assertFalse(resultado);
    }

    @Test
    void hashCode_Devuelve_Correctamente() throws ClubException {
        String codigo = "codigo";

        Grupo grupo = new Grupo(codigo, "actividad", 3, 2, 30);
        int resultado  = grupo.hashCode();

        assertEquals(689452726,resultado);
    }
}
