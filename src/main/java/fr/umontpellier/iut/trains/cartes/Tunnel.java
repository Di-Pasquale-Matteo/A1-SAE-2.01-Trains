package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Tunnel extends Carte {
    public Tunnel() {
        super("Tunnel",TypeCarte.RAILS,0,5);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherFerraille();
        joueur.addPointRail(1);
        if (!joueur.getListeEffet().contains("Surcout montagne")){
            joueur.getListeEffet().add("Surcout montagne");
        }
    }
}
