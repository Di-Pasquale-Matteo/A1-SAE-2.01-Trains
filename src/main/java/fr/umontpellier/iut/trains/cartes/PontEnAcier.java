package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class PontEnAcier extends Carte {
    public PontEnAcier() {
        super("Pont en acier",TypeCarte.RAILS,0,4);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherFerraille();
        joueur.addPointRail(1);
        if (!joueur.getListeEffet().contains("Surcout fleuve")){
            joueur.getListeEffet().add("Surcout fleuve");
        }
    }
}
