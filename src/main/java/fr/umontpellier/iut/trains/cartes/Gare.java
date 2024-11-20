package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.TuileVille;

import java.util.ArrayList;
import java.util.List;

public class Gare extends Carte {
    public Gare() {
        super("Gare",TypeCarte.GARE,0,3);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherFerraille();
        if (joueur.getJeu().getNbJetonsGare() > 0){
            List<String> choixPossibles = new ArrayList<>();
            for (int i = 0; i <= 75; i++) {
                if (joueur.getJeu().getTuile(i) instanceof TuileVille tuile){
                    if (tuile.getNbGares() != tuile.getNbGaresMax()){
                        choixPossibles.add("TUILE:"+i);
                    }
                }
            }
            if (!choixPossibles.isEmpty()){
                String choix = joueur.choisir("Choisissez une tuile sur laquelle ajouter une gare.", choixPossibles, null, false);
                int numTuile = Integer.parseInt(choix.split(":")[1]);
                joueur.getJeu().getTuile(numTuile).ajouterGare();
                joueur.getJeu().removeNbJetonGare();
            }
        }


    }
}
