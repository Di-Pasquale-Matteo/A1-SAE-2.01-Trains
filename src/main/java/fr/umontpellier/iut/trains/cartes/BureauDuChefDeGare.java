package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class BureauDuChefDeGare extends Carte {
    public BureauDuChefDeGare() {
        super("Bureau du chef de gare",TypeCarte.ACTION,0,4);
    }

    public void jouer(Joueur joueur){
        List<String> choixPossibles = joueur.getMain().stream().filter(cartes -> cartes.getType() == TypeCarte.ACTION
                && !cartes.getNom().equals(this.getNom()) || cartes.getType() == TypeCarte.TRAIN_ACTION ).map(Carte::getNom).toList();
        String choisir = joueur.choisir("Choisissez la carte action pour en copier l'effet",choixPossibles,null,choixPossibles.isEmpty());
        if (!choisir.isEmpty()) {
            joueur.log("Carte copiée : " + choisir);
            joueur.getMain().getCarte(choisir).jouer(joueur);
        }
    }
}
