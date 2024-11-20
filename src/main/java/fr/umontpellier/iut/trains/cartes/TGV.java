package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TGV extends Carte {
    public TGV() {
        super("TGV", TypeCarte.TRAIN_ACTION, 1, 2);
    }

    @Override
    public void jouer(Joueur joueur) {
        boolean argentAjouter = false;

        for (Carte carte : joueur.getCartesEnJeu()) {
            if (carte.getNom().equals("Train omnibus") && !argentAjouter) {
                joueur.ajouterArgent(1);
                argentAjouter = true;
            }
        }
    }
}
