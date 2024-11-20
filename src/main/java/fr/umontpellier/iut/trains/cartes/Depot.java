package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import java.util.ArrayList;
import java.util.List;

public class Depot extends Carte {
    public Depot() {
        super("Dépôt", TypeCarte.ACTION, 1, 3);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.getMain().addAll(joueur.piocher(2));

        List<String> choixPossibles = new ArrayList<>(joueur.getMain().stream().map(Carte::getNom).toList());
        int nombreADefauser = Math.min(2, joueur.getMain().size());
        for (int i = 0; i < nombreADefauser; i++) {
            String nomCarteADefauser = joueur.choisir("Choisissez une carte à défausser", choixPossibles, null, false);
            Carte carteADefauser = joueur.getMain().retirer(nomCarteADefauser);
            joueur.defausser(carteADefauser);
            joueur.log("Défaussé : " + nomCarteADefauser);
            choixPossibles.remove(nomCarteADefauser);
        }
    }
}
