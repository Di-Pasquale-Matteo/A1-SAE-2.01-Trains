package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;
import java.util.List;
public class HorairesEstivaux extends Carte {
    public HorairesEstivaux() {
        super("Horaires estivaux",TypeCarte.ACTION,0,3);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<Bouton> boutons = List.of(new Bouton("oui", "oui"), new Bouton("non", "non"));

        String choix = joueur.choisir("Voulez-vous écarter cette carte pour recevoir 3 pièces ?", List.of("oui", "non"), boutons, false);

        if ("oui".equals(choix)) {
            joueur.ajouterArgent(3);
            joueur.setCarteSeRetire(true);
            joueur.log("La carte " + getNom() + " a été écartée pour 3 pièces.");
        } else {
            joueur.log("La carte " + getNom() + " n'a pas été écartée.");
        }
    }

}

