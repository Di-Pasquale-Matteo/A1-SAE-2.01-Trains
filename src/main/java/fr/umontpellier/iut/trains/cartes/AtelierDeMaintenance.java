package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class AtelierDeMaintenance extends Carte {
    public AtelierDeMaintenance() {
        super("Atelier de maintenance",TypeCarte.ACTION,0,5);
    }

    public void jouer(Joueur joueur){
        List<String> choixPossibles = joueur.getMain().stream().filter(cartes -> cartes.getType() == TypeCarte.TRAIN || cartes.getType()
                == TypeCarte.TRAIN_ACTION).map(Carte::getNom).toList();
        String choisir = joueur.choisir("Choisissez la carte train à reveler",choixPossibles,null,choixPossibles.isEmpty());
        Carte nouveauTrain = null;
        if (!choisir.isEmpty()){
            joueur.log("Carte révélée : " + choisir);
            nouveauTrain = joueur.getJeu().prendreDansLaReserve(choisir);
        }
        if (nouveauTrain != null){
            joueur.nouvelleCarte(nouveauTrain);
            joueur.log("Nouvelle carte " + choisir + " obtenue.");
        }
        else{
            joueur.log("Carte " + choisir + " non disponible.");
        }
    }
}
