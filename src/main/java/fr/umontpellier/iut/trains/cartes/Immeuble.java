package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Immeuble extends Carte {
    public Immeuble() {
        super("Immeuble",TypeCarte.VICTOIRE,0,5);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherFerraille();
        joueur.marque(2);
    }
}
