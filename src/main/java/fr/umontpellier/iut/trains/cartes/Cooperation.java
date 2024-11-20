package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Cooperation extends Carte {
    public Cooperation() {
        super("Coopération",TypeCarte.RAILS,0,5);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherFerraille();
        joueur.addPointRail(1);
        if (!joueur.getListeEffet().contains("Surcout sur rail")){
            joueur.getListeEffet().add("Surcout sur rail");
        }
    }
}
