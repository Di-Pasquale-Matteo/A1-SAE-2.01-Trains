package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;

public class CentreDeRenseignements extends Carte {
    public CentreDeRenseignements() {
        super("Centre de renseignements", TypeCarte.ACTION, 1, 4);
    }

    @Override
    public void jouer(Joueur joueur) {
        ListeDeCartes cartesRevelees = new ListeDeCartes();
        cartesRevelees.addAll(joueur.piocher(4));
        ArrayList<String> choixPossibles = new ArrayList<>(cartesRevelees.stream().map(Carte::getNom).toList());
        joueur.log("Cartes révélées: " + choixPossibles);
        if (!choixPossibles.isEmpty()) {
            ArrayList<Bouton> listeBoutons = new ArrayList<>();
            for (String s : choixPossibles){
                Bouton b = new Bouton(s,s);
                listeBoutons.add(b);
            }
            String choix = joueur.choisir("Choisissez une carte à ajouter à votre main", choixPossibles, listeBoutons, true);
            if (!choix.isEmpty()) {
                joueur.getMain().add(cartesRevelees.retirer(choix));
                choixPossibles.remove(choix);
            }

            while (!choixPossibles.isEmpty()){
                listeBoutons = new ArrayList<>();
                for (String s : choixPossibles){
                    Bouton b = new Bouton(s,s);
                    listeBoutons.add(b);
                }
                String choixOrdre = joueur.choisir("Choisissez la carte a poser sur la pioche actuelle", choixPossibles, listeBoutons, false);
                joueur.getPioche().add(0,cartesRevelees.retirer(choixOrdre));
                choixPossibles.remove(choixOrdre);
            }
        } else {
            joueur.log("Pas assez de cartes dans le deck pour dévoiler.");
        }
    }
}