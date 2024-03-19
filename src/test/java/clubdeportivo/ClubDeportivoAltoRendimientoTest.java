package clubdeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.*;

public class ClubDeportivoAltoRendimientoTest {

    private String nombre = "UMA";
    private int maximo = 10;
    private int tam = 10;
    private int incremento = 50;

    @Test
    public void constructorDatosValidosTest() throws ClubException {
        //Arrange and act
        ClubDeportivoAltoRendimiento cda = new ClubDeportivoAltoRendimiento(nombre, maximo, incremento);
        Boolean creado = cda.toString().contains(nombre);
        //Asset
        assertTrue(creado);
    }

    @Test
    public void contructorMaximoInvalidoDevuelveExcepcionTest() {
        //Arrange and Act
        ClubException thrown = assertThrows(ClubException.class, ()->{
            new ClubDeportivoAltoRendimiento(nombre, -1, incremento);
        });
        String actual = thrown.getMessage();
        String expected = "ERRORES: valores 0 o negativos.";
        //Assert
        assertEquals(expected, actual);
    }
    
    @Test
    public void constructorIncrementoInvalidoDevuelveExcepcionTest() {
        //Arrange and act
        ClubException thrown = assertThrows(ClubException.class, ()->{
            new ClubDeportivoAltoRendimiento(nombre, tam, maximo,  0);
        });
        String actual = thrown.getMessage();
        String expected = "ERRORES: valores 0 o negativos.";
        //Assert
        assertEquals(expected, actual);
    }
     
    @Test
    public void constructorDatosInvalidosTest() {
        //Arrange and act
        ClubException thrown = assertThrows(ClubException.class, ()->{
            new ClubDeportivoAltoRendimiento(nombre, -1, 0);
        });
        String actual = thrown.getMessage();
        String expected = "ERRORES: valores 0 o negativos.";
        //Assert
        assertEquals(expected, actual);
    }
    
}