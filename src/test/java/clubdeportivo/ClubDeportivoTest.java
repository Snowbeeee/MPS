package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

public class ClubDeportivoTest {

    private String miClub = "Club Deportivo";
    private int nGrupos = 10;
    
    @Test
    public void constructorTest() throws ClubException {
        //Arrange and act
        ClubDeportivo cd = new ClubDeportivo(miClub);
        Boolean creado = cd.toString().contains(miClub);
        //Assert
        assertTrue(creado);
    }

    @Test
    public void constructorTamTest() throws ClubException {
        //Arrange and act
        ClubDeportivo cd = new ClubDeportivo(miClub, nGrupos);
        Boolean creado = cd.toString().contains(miClub);
        //Assert
        assertTrue(creado);
    }

    @Test 
    public void constructorTamCeroReturnsException_Test() throws ClubException {
        //Arrange and act
        ClubException thrown = assertThrows(ClubException.class, () -> {
            new ClubDeportivo(miClub, 0);
        });
        //Assert
        assertEquals(thrown.getMessage(), "ERROR: el club no puede crearse con un número de grupos 0 o negativo");
    }

    @Test
    public void constructorTamNegativoReturnsException_Test() throws ClubException {
        //Arrange and act
        ClubException thrown = assertThrows(ClubException.class, () -> {
            new ClubDeportivo(miClub, -1);
        });
        //Assert
        assertEquals(thrown.getMessage(), "ERROR: el club no puede crearse con un número de grupos 0 o negativo");
    }

    @Test
    public void anyadirActividadDatosValidosActividadAnyadidaTest() throws ClubException {
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
    public void anyadirActividadAnyadirMasDeUnaActividadTest() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, 1);
        Grupo gr1 = new Grupo("Codigo 1", "Actividad 1", 10, 0, 50.0);
        Grupo gr2 = new Grupo("Codigo 2", "Actividad 2", 10, 0, 50.0);
        // Act
        cd.anyadirActividad(gr1);
        cd.anyadirActividad(gr2);
        String actividades = cd.toString();
        Boolean actividadesAnyadidas = actividades.contains(gr1.toString()) && actividades.contains(gr2.toString());
        //Assert
        assertTrue(actividadesAnyadidas);
    }

    @Test
    public void anyadirActividad_DatosInvalidosReturnsExceptionTest() throws ClubException {
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
    public void anyadirActividadGrupoNuevoValidoActividadAnyadidaTest() throws ClubException {
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
    public void anyadirActividadGrupoExistenteActualizaPlazasTest() throws ClubException {
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
    public void anyadirActividadGrupoNuloReturnsExceptionTest() throws ClubException {
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
    public void plazasLibres_ActividadExistenteReturnsPlazasLibresTest()  throws ClubException {
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
    public void plazasLibresActividadNoExistenteReturnsCeroTest() throws     ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, 2);
        Grupo gr1 = new Grupo("Codigo 1", "Actividad 1",   10, 0, 50.0);
        Grupo gr2 = new Grupo("Codigo 2", "Actividad 2",   5, 0, 30.0);
        cd.anyadirActividad(gr1);
        cd.anyadirActividad(gr2);
        // Act
        int plazasLibres = cd.plazasLibres("Actividad 3");
        // Assert
        assertEquals(0, plazasLibres);
    }

    @Test
    public void ingresosCalculaIngresosTotalesTest() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub);
        Grupo gr1 = new Grupo("Codigo 1", "Actividad 1",   10, 5, 50.0);
        Grupo gr2 = new Grupo("Codigo 2", "Actividad 2",   5, 3, 30.0);
        cd.anyadirActividad(gr1);
        cd.anyadirActividad(gr2);
        // Act
        double ingresos = cd.ingresos();
        // Assert
        assertEquals(340.0, ingresos);
    }

    @Test
    public void ingresosNingunGrupoReturnsZeroTest() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub);
        // Act
        double resultado = cd.ingresos();
        double esperado = 0.0;
        // Assert
        assertEquals(esperado, resultado);
    }

    @Test
    public void ingresosGruposVaciosReturnsZeroTest() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub);
        Grupo gr1 = new Grupo("Codigo 1", "Actividad 1",   10, 0, 50.0);
        Grupo gr2 = new Grupo("Codigo 2", "Actividad 2",   5, 0, 30.0);
        cd.anyadirActividad(gr1);
        cd.anyadirActividad(gr2);
        // Act
        double resultado = cd.ingresos();
        double esperado = 0.0;
        // Assert
        assertEquals(esperado, resultado);
    }

    @Test
    public void matricularSufficientPlazasLibresMatriculaRealizadaTest() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, 1);
        Grupo gr = new Grupo("Codigo 1", "Actividad 1", 10, 0,     50.0);
        cd.anyadirActividad(gr);
        int npersonas = 5;
        // Act
        cd.matricular("Actividad 1", npersonas);
        int plazasLibres = cd.plazasLibres("Actividad 1");
        // Assert
        assertEquals(5, plazasLibres);
    }

    @Test
    public void matricularPlazasLibresInsuficientesReturnsExceptionTest() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, 1);
        Grupo gr = new Grupo("Actividad 1", "Descripción 1", 10, 0, 50.0);
        cd.anyadirActividad(gr);
        int npersonas = 15;
        // Act
        ClubException thrown = assertThrows(ClubException.class, () -> {
            cd.matricular("Actividad 1", npersonas);
        });
        //Assert
        assertEquals(thrown.getMessage(), "ERROR: no hay suficientes plazas libres para esa actividad en el club.");
    }

    @Test
    public void matricularMultipleGruposMatriculaRealizadaTest() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, 2);
        Grupo gr1 = new Grupo("Codigo 1", "Actividad 1", 10, 0,    50.0);
        Grupo gr2 = new Grupo("Codigo 2", "Actividad 2", 5, 0,     30.0);
        cd.anyadirActividad(gr1);
        cd.anyadirActividad(gr2);
        int npersonas = 5;
        // Act
        cd.matricular("Actividad 1", npersonas);
        cd.matricular("Actividad 2", npersonas);
        int plazasLibres1 = cd.plazasLibres("Actividad 1");
        int plazasLibres2 = cd.plazasLibres("Actividad 2");
        // Assert
        assertEquals(5, plazasLibres1);
        assertEquals(0, plazasLibres2);
    }

    @Test
    public void matricular_ActividadNoExistente_ReturnsException_Test() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, 1);
        int npersonas = 5;
        // Act
        ClubException thrown = assertThrows(ClubException.class, () -> {
            cd.matricular("Actividad 1", npersonas);
        });
        //Assert
        assertEquals("ERROR: no hay suficientes plazas libres para esa actividad en el club.", thrown.getMessage());
    }   

    @Test
    public void matricular_MatricularCeroPersonas_NoCambiaPlazasTest() throws ClubException{
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, 1);
        Grupo gr = new Grupo("Codigo 1", "Actividad 1", 10, 0, 50.0);
        cd.anyadirActividad(gr);
        int npersonas = 0;
        // Act
        cd.matricular("Actividad 1", npersonas);
        int plazasLibres = cd.plazasLibres("Actividad 1");
        // Assert
        assertEquals(10, plazasLibres);
    }

    @Test
    public void matricular_PlazasTotalesInsuficientes_ReturnsException_Test() throws ClubException {
        // Arrange
        ClubDeportivo cd = new ClubDeportivo(miClub, 2);
        Grupo gr1 = new Grupo("Codigo 1", "Actividad 1", 10, 0,     50.0);
        Grupo gr2 = new Grupo("Codigo 2", "Actividad 2", 5, 0,  30.0);
        cd.anyadirActividad(gr1);
        cd.anyadirActividad(gr2);
        int npersonas = 20; // Intentamos matricular más personas de las plazas totales disponibles
        // Act
        ClubException thrown = assertThrows(ClubException.class, () -> {
            cd.matricular("Actividad 1", npersonas);
        });
        // Assert
        assertEquals("ERROR: no hay suficientes plazas libres para esa actividad en el club.",  thrown.getMessage());
    }

}

