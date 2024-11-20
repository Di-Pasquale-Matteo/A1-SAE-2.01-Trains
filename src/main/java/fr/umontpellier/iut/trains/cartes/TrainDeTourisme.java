package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TrainDeTourisme extends Carte {
    public TrainDeTourisme() {
        super("Train de tourisme",TypeCarte.TRAIN_ACTION,1,4);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.marque(1);
    }
}
