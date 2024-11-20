package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class TrainPostal extends Carte {
    public TrainPostal() {
        super("Train postal",TypeCarte.TRAIN_ACTION,1,4);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = joueur.getMain().stream().filter(cartes -> cartes != this).map(Carte::getNom).toList();
        String choisir = joueur.choisir("Choisissez une carte à défausser",choixPossibles,null,true);
        while(!choisir.isEmpty()){
            joueur.ajouterArgent(1);
            joueur.defausser(joueur.getMain().getCarte(choisir));
            choixPossibles = joueur.getMain().stream().filter(cartes -> cartes != this).map(Carte::getNom).toList();
            choisir = joueur.choisir("Choisissez une carte à défausser",choixPossibles,null,true);
        }

    }
}
