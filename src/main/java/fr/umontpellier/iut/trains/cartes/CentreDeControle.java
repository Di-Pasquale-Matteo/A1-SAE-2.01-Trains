package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class CentreDeControle extends Carte {
    public CentreDeControle() {
        super("Centre de contrôle", TypeCarte.ACTION, 0, 3);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.getMain().addAll(joueur.piocher(1));
        List<String> choixPossibles = joueur.getJeu().getListeNomsCartes().stream().toList();

        List<Bouton> boutons = new ArrayList<>();
        for (String nomCarte : choixPossibles) {
            Bouton boutonCarte = new Bouton(nomCarte, nomCarte);
            boutons.add(boutonCarte);
        }

        String nomCarteDevinee = joueur.choisir("Nommez une carte", choixPossibles, boutons, false);
        joueur.log("Vous avez nommé : " + nomCarteDevinee);

        Carte carteSurSommet = joueur.regarderLaProchaineCarte();
        if (carteSurSommet != null) {
            joueur.log("La carte sur le dessus de votre pioche est : " + carteSurSommet.getNom());
            if (carteSurSommet.getNom().equals(nomCarteDevinee)) {
                joueur.getMain().add(carteSurSommet);
                joueur.getPioche().remove(carteSurSommet);
                joueur.log("La carte devinée était correcte et a été ajoutée à votre main.");
            } else {
                joueur.log("La carte devinée était incorrecte et reste sur le dessus de la pioche.");
            }
        } else {
            joueur.log("Il n'y a pas de cartes dans la pioche pour vérifier votre prédiction.");
        }
    }
}
