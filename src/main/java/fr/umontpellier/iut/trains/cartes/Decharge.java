package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Decharge extends Carte {
    public Decharge() {
        super("Décharge", TypeCarte.ACTION, 0, 2);
    }

    @Override
    public void jouer(Joueur joueur) {
        int nbrFerraille = joueur.getMain().count("Ferraille");

        for (int i = 0; i < nbrFerraille; i++) {
            joueur.mettreDansLaReserve(joueur.getMain().retirer("Ferraille"));
        }

        joueur.log("Retiré " + nbrFerraille + " cartes Ferraille de la main et remis dans la réserve.");
    }
}
