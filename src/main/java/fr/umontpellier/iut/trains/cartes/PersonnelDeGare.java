package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class PersonnelDeGare extends Carte {
    public PersonnelDeGare() {
        super("Personnel de gare", TypeCarte.ACTION, 0, 2);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<Bouton> boutons = new ArrayList<>();
        boutons.add(new Bouton("piocher", "Piocher 1 carte"));
        boutons.add(new Bouton("argent", "Recevoir 1¥"));
        boutons.add(new Bouton("ferraille", "Remettre une carte Ferraille sur la pile Ferraille"));

        String choix = joueur.choisir("Choisissez entre piocher 1 carte, recevoir 1 argent ou remettre une carte Ferraille sur la pile Ferraille", List.of("piocher", "argent", "ferraille"), boutons, false);

        if (choix.equals("piocher")) {
            joueur.getMain().addAll(joueur.piocher(1));
            joueur.log("Vous avez choisi de piocher une carte.");
        } else if (choix.equals("argent")) {
            joueur.ajouterArgent(1);
            joueur.log("Vous avez choisi de recevoir 1¥.");
        } else if (choix.equals("ferraille")) {
            Carte carteFerraille = joueur.getMain().retirer("Ferraille");
            if (carteFerraille != null) {
                joueur.mettreDansLaReserve(carteFerraille);
                joueur.log("Vous avez choisi de remettre une carte Ferraille sur la pile Ferraille.");
            } else {
                joueur.log("Vous n'avez pas de carte Ferraille à remettre sur la pile.");
            }
        } else {
            joueur.log("Choix non reconnu, aucune action effectuée.");
        }
    }

}
