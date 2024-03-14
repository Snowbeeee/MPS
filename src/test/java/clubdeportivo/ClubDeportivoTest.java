package clubdeportivo;

import org.junit.jupiter.api.*;

public class ClubDeportivoTest {
    private String miClub = "Club Deportivo";
    
    @Test
    public void buscarGrupoEncontradoTest() throws ClubException {
        ClubDeportivo club = new ClubDeportivo(miClub);
        Grupo grupo = new Grupo("Pilates", "Lunes", 20, 10, 30.0);

        club.anyadirActividad(grupo);
        int pos = club.buscar(grupo);


    }







}   
