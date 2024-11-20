package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class CabineDuConducteur extends Carte {
    public CabineDuConducteur() {
        super("Cabine du conducteur",TypeCarte.ACTION,0,2);
    }

    public void jouer(Joueur joueur){
        int nbCartes = 0;
        List<String> choixPossibles = joueur.getMain().stream().filter(cartes -> cartes != this).map(Carte::getNom).toList();
        String choisir = joueur.choisir("Choisissez une carte à défausser",choixPossibles,null,true);
        while(!choisir.isEmpty()){
            nbCartes++;
            joueur.defausser(joueur.getMain().getCarte(choisir));
            choixPossibles = joueur.getMain().stream().filter(cartes -> cartes != this).map(Carte::getNom).toList();
            choisir = joueur.choisir("Choisissez une carte à défausser",choixPossibles,null,true);
        }
        joueur.getMain().addAll(joueur.piocher(nbCartes));
    }
}
