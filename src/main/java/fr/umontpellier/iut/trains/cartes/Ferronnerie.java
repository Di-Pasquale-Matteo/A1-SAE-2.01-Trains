package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Ferronnerie extends Carte {
    public Ferronnerie() {
        super("Ferronnerie",TypeCarte.ACTION,1,4);
    }

    @Override
    public void jouer(Joueur joueur) {
        if (!joueur.getListeEffet().contains("Jouer rail")){
            joueur.getListeEffet().add("Jouer rail");
        }
    }
}
