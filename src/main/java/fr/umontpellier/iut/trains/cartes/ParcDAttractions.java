package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import javax.swing.*;
import java.util.List;

public class ParcDAttractions extends Carte {
    public ParcDAttractions() {
        super("Parc d'attractions", TypeCarte.ACTION,1,4);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = joueur.getCartesEnJeu().stream().filter(cartes -> cartes.getType() == TypeCarte.TRAIN || cartes.getType()
                == TypeCarte.TRAIN_ACTION).map(Carte::getNom).toList();
        String choisir = joueur.choisir("Choisissez la carte train pour récupérer sa valeur",choixPossibles,null,true);
        if (!choisir.isEmpty()){
            joueur.ajouterArgent(joueur.getCartesEnJeu().getCarte(choisir));
        }
    }
}
