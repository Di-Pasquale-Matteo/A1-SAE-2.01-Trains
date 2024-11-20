package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class GratteCiel extends Carte {
    public GratteCiel() {
        super("Gratte-Ciel",TypeCarte.VICTOIRE,0,8);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherFerraille();
        joueur.marque(4);
    }
}