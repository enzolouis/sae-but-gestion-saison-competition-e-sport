package sqlrequests;

public class CreateAll {

	public static void main(String[] args) {
		// On drop   en premier les Tables avec le "plus"  de FOREIGN KEY (pas forcément le plus mais voilà)
		// On create en premier les Tables avec le "moins" de FOREGIN KEY
		
		// 2 FOREIGN KEY (Equipe, Tournoi)
		CreateParticipation.Drop();
		
		// 1 FOREIGN KEY (Equipe pour les 3)
		CreateTournoi.Drop();
		CreateMatch.Drop();
		CreateJoueur.Drop();
		
		// 0 FOREIGN KEY
		CreateEquipe.Drop();
		CreateAdmin.Drop();
		CreateArbitre.Drop();
		
		CreateDual.Drop();
		
		CreateArbitre.Create();
		CreateAdmin.Create();
		CreateEquipe.Create();
		
		CreateJoueur.Create();
		CreateMatch.Create();
		CreateTournoi.Create();
		
		CreateParticipation.Create();
		
		CreateDual.Create();
		
	}
	
}