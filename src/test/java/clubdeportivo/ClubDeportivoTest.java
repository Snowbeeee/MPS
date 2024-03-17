package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

public class ClubDeportivoTest {

    private String miClub = "Club Deportivo";
    private int nGrupos = 10;
    
    @Test
    public void constructor_Nombre_NotNull_Test() throws ClubException {
        //Arrange and act
        ClubDeportivo cd = new ClubDeportivo(miClub);
        //Assert
        assertNotNull(cd);
    }

    @Test
    public void constructor_NombreyTamanio_NotNull_Test() throws ClubException {
        //Arrange and act
        ClubDeportivo cd = new ClubDeportivo(miClub, nGrupos);
        //Assert
        assertNotNull(cd);
    }

    @Test 
    public void constructor_TamanyoCero_ReturnsException_Test() throws ClubException {
        //Arrange and act
        ClubException thrown = assertThrows(ClubException.class, () -> {
            new ClubDeportivo(miClub, 0);
        });
        //Assert
        assertEquals(thrown.getMessage(), "ERROR: el club no puede crearse con un número de grupos 0 o negativo");
    }

    @Test
    public void constructor_TamanioNegativo_ReturnsException_Test() throws ClubException {
        //Arrange and act
        ClubException thrown = assertThrows(ClubException.class, () -> {
            new ClubDeportivo(miClub, -1);
        });
        //Assert
        assertEquals(thrown.getMessage(), "ERROR: el club no puede crearse con un número de grupos 0 o negativo");
    }

    @Test
    public void anyadirActividad_DatosValidos_ActividadAnyadida_Test() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, 1);
        Grupo gr = new Grupo("Actividad 1", "Descripción 1", 10, 0, 50.0);
        String[] datos = {"Actividad 1", "Descripción 1", "10", "0", "50.0"};
        // Act
        cd.anyadirActividad(datos);
        String actividad = gr.toString();
        Boolean activityAdded = cd.toString().contains(actividad);
        //Assert
        assertTrue(activityAdded);
    }

    @Test
    public void anyadirActividad_AnyadirMasDeUnaActividad_Test() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, 1);
        Grupo gr = new Grupo("Actividad 1", "Descripción 1", 10, 0, 50.0);
        Grupo gr2 = new Grupo("Actividad 2", "Descripción 2", 10, 0, 50.0);
        // Act
        cd.anyadirActividad(gr);
        cd.anyadirActividad(gr2);
        String actividades = cd.toString();
        Boolean actividadesAnyadidas = actividades.contains(gr.toString()) && actividades.contains(gr2.toString());
        //Assert
        assertTrue(actividadesAnyadidas);
    }

    @Test
    public void anyadirActividad_DatosInvalidos_ReturnsException_Test() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub);
        String[] datos = {"Actividad 1", "Descripción 1", "10", "0", "Invalid"};
        // Act
        ClubException thrown = assertThrows(ClubException.class, () -> {
            cd.anyadirActividad(datos);
        });
        //Assert
        assertEquals(thrown.getMessage(), "ERROR: formato de número incorrecto");
    }

    @Test 
    public void anyadirActividad_GrupoNuevoValido_ActividadAnyadida_Test() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub,1);
        Grupo gr = new Grupo("Actividad 1", "Descripción 1", 10, 0, 50.0);
        // Act
        cd.anyadirActividad(gr);
        String actividad = gr.toString();
        String actividades = cd.toString();
        Boolean activityAdded = actividades.contains(actividad);
        //Assert
        assertTrue(activityAdded);
    }

    @Test
    public void anyadirActividad_GrupoExistente_ActualizaPlazas_Test() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub,1);
        Grupo gr = new Grupo("Codigo 1", "Actividad 1", 10, 0, 50.0);
        Grupo gr2 = new Grupo("Codigo 1", "Actividad 1", 20, 0, 50.0);
        // Act
        cd.anyadirActividad(gr);
        cd.anyadirActividad(gr2);
        //Assert
        assertEquals(20, cd.plazasLibres("Actividad 1")); 
    }

    @Test
    public void anyadirActividad_GrupoNulo_ReturnsException_Test() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub);
        Grupo gr = null;
        // Act
        ClubException thrown = assertThrows(ClubException.class, () -> {
            cd.anyadirActividad(gr);
        });
        //Assert
        assertEquals(thrown.getMessage(), "ERROR: el grupo es nulo");
    }

    @Test
    public void plazasLibres_ActividadExistente_ReturnsPlazasLibres_Test()  throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, 2);
        Grupo gr1 = new Grupo("Codigo 1", "Actividad 1",    10, 0, 50.0);
        Grupo gr2 = new Grupo("Codigo 2", "Actividad 2",    5, 0, 30.0);
        cd.anyadirActividad(gr1);
        cd.anyadirActividad(gr2);

        // Act
        int plazasLibres = cd.plazasLibres("Actividad 1");

        // Assert
        assertEquals(10, plazasLibres);
    }

    @Test
    public void plazasLibres_ActividadNoExistente_ReturnsCero_Test() throws     ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, 2);
        Grupo gr1 = new Grupo("Actividad 1", "Descripción 1",   10, 0, 50.0);
        Grupo gr2 = new Grupo("Actividad 2", "Descripción 2",   5, 0, 30.0);
        cd.anyadirActividad(gr1);
        cd.anyadirActividad(gr2);

        // Act
        int plazasLibres = cd.plazasLibres("Actividad 3");

        // Assert
        assertEquals(0, plazasLibres);
    }

    @Test
    public void ingresos_CalculatesTotalIncome_Test() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, nGrupos);
        Grupo gr1 = new Grupo("Actividad 1", "Descripción 1",   10, 5, 50.0);
        Grupo gr2 = new Grupo("Actividad 2", "Descripción 2",   5, 3, 30.0);
        cd.anyadirActividad(gr1);
        cd.anyadirActividad(gr2);

        // Act
        double ingresos = cd.ingresos();

        // Assert
        assertEquals(550.0, ingresos);
    }

    @Test
    public void ingresos_NoGroups_ReturnsZero_Test() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, 0);

        // Act
        double totalIncome = cd.ingresos();

        // Assert
        assertEquals(0.0, totalIncome);
    }

    @Test
    public void ingresos_EmptyGroups_ReturnsZero_Test() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, nGrupos);
        Grupo gr1 = new Grupo("Actividad 1", "Descripción 1",   10, 0, 50.0);
        Grupo gr2 = new Grupo("Actividad 2", "Descripción 2",   5, 0, 30.0);
        cd.anyadirActividad(gr1);
        cd.anyadirActividad(gr2);

        // Act
        double totalIncome = cd.ingresos();

        // Assert
        assertEquals(0.0, totalIncome);
    }

    
}   
