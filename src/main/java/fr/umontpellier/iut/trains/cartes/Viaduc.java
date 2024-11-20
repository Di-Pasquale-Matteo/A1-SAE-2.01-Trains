package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Viaduc extends Carte {
    public Viaduc() {
        super("Viaduc",TypeCarte.RAILS,0,5);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherFerraille();
        joueur.addPointRail(1);
        if (!joueur.getListeEffet().contains("Surcout ville")){
            joueur.getListeEffet().add("Surcout ville");
        }
    }
}
