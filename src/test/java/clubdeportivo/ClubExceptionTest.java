/*
 * @autor1 = Eduardo García Rivas
 * @autor2 = María Victoria Huesca Peláez
 */

package clubdeportivo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class ClubExceptionTest {
    @Test
    void crea_Objeto_Sin_Parametros_Correctamente(){
        ClubException exception = new ClubException();

        assertNotNull(exception);
    }
}
