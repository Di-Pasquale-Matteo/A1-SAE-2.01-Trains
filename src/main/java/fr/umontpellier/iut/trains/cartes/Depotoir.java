package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Depotoir extends Carte {
    public Depotoir() {
        super("Dépotoir",TypeCarte.ACTION,1,5);
    }

    @Override
    public void jouer(Joueur joueur) {
        if (!joueur.getListeEffet().contains("Piocher ferraille")){
            joueur.getListeEffet().add("Piocher ferraille");
        }
    }
}
