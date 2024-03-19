
import clubdeportivo.ClubDeportivo;
import clubdeportivo.ClubDeportivoAltoRendimiento;
import clubdeportivo.ClubException;
import clubdeportivo.Grupo;

public class ClubDeportivoMain {
	public static void main(String[] args) throws ClubException {
		String [] grupo1 = {"123A","Kizomba","10","10","25.0"};
		
		try {
			ClubDeportivo club = new ClubDeportivo("UMA",1);
			Grupo pilates = new Grupo("456B","Pilates",8,5,50.0);
			club.anyadirActividad(grupo1);
			club.anyadirActividad(pilates);
			System.out.println(club);			
			System.out.println("Ingresos: " + club.ingresos());
			
		} catch (ClubException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("");

		try {
			ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("UMA",1,1,1);
			Grupo pilates = new Grupo("456B","Pilates",8,5,50.0);
			club.anyadirActividad(grupo1);
			club.anyadirActividad(pilates); // ???????
			System.out.println(club);			
			System.out.println("Ingresos: " + club.ingresos());
			
		} catch (ClubException e) {
			System.out.println(e.getMessage());
		}

		/*ClubDeportivoAltoRendimiento club = new ClubDeportivoAltoRendimiento("nombre", 6, 1);
        String datos[] = {"nombre", "actividad", "10", "7","30"};

        club.anyadirActividad(datos);

		System.out.println(club.toString());*/

		
	}
}
