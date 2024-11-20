package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TrainMatinal extends Carte {
    public TrainMatinal() {
        super("Train matinal",TypeCarte.TRAIN_ACTION,2,5);
    }

    @Override
    public void jouer(Joueur joueur) {
        if (!joueur.getListeEffet().contains("Achat carte")){
            joueur.getListeEffet().add("Achat carte");
        }
    }
}
