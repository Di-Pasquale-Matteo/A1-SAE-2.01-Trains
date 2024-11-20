package fr.umontpellier.iut.trains.cartes;
import fr.umontpellier.iut.trains.Joueur;

import java.util.List;
import java.util.ArrayList;

public class TrainDeMarchandises extends Carte {
    public TrainDeMarchandises() {
        super("Train de marchandises",TypeCarte.TRAIN_ACTION,1,4);
    }

    @Override
    public void jouer(Joueur joueur) {
        boolean passe = false;
        while (!passe) {
            List<String> choixPossibles = new ArrayList<>();
            if (joueur.getMain().getCarte("Ferraille")!=null){
                choixPossibles.add("Ferraille");
            }
            String choix = joueur.choisir("Mettez une carte Ferraille sur la pile Ferraille ou passez",
                    choixPossibles, null, true);
            passe=choix.isEmpty();
            if (!passe){
                joueur.mettreDansLaReserve(joueur.getMain().retirer("Ferraille"));
                joueur.ajouterArgent(1);
            }
        }
    }
}
