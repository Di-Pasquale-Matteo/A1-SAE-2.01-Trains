package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Remorquage extends Carte {
    public Remorquage() {
        super("Remorquage",TypeCarte.ACTION,0,3);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = joueur.getDefausse().stream()
                .filter(carte -> carte.getType() == TypeCarte.TRAIN || carte.getType() == TypeCarte.TRAIN_ACTION)
                .map(Carte::getNom)
                .toList();
        if (choixPossibles.isEmpty()) {
            choixPossibles.add("");
        }
        List<Bouton> boutons = new ArrayList<>();
        for (String nomCarte : choixPossibles) {
            Bouton boutonCarte = new Bouton(nomCarte, nomCarte);
            boutons.add(boutonCarte);
        }
        String choix = joueur.choisir("Choisissez la carte TRAIN à révéler", choixPossibles, boutons, true);
        if (!choix.isEmpty()) {
            Carte carteChoisie = joueur.getDefausse().retirer(choix);
            joueur.getMain().add(carteChoisie);
        }
    }
}
