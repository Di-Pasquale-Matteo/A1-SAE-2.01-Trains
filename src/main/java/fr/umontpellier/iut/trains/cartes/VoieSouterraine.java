package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class VoieSouterraine extends Carte {
    public VoieSouterraine() {
        super("Voie souterraine",TypeCarte.RAILS,0,7);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherFerraille();
        joueur.addPointRail(1);
        if (!joueur.getListeEffet().contains("Surcout")){
            joueur.getListeEffet().add("Surcout");
        }
    }
}
