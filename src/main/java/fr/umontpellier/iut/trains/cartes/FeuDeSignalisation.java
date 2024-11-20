package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
public class FeuDeSignalisation extends Carte {
    public FeuDeSignalisation() {
        super("Feu de signalisation", TypeCarte.ACTION, 0, 2);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.getMain().addAll(joueur.piocher(1));
        Carte premiereCarteDuDeck = joueur.piocher(1).get(0);
        if (premiereCarteDuDeck != null) {
            ArrayList<Bouton> listeBoutons = new ArrayList<>();
            listeBoutons.add(new Bouton("oui","oui"));
            listeBoutons.add(new Bouton("non","non"));
            String action = joueur.choisir("Voulez-vous défausser la carte suivante : " +
                    premiereCarteDuDeck.getNom() + " ? oui/non", List.of("oui", "non"), listeBoutons, false);
            if (action.equals("oui")) {
                joueur.getDefausse().add(premiereCarteDuDeck);
            } else {
                joueur.getPioche().add(0,premiereCarteDuDeck);
            }
        }
    }
}
