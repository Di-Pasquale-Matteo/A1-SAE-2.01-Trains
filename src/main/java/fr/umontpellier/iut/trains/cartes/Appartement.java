package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Appartement extends Carte {
    public Appartement() {
        super("Appartement",TypeCarte.VICTOIRE,0,3);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherFerraille();
        joueur.marque(1);
    }
}
