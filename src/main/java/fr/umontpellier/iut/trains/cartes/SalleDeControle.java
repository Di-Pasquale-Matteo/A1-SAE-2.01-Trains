package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;


public class SalleDeControle extends Carte {
    public SalleDeControle() {
        super("Salle de contrôle", TypeCarte.ACTION,0,7);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.getMain().addAll(joueur.piocher(3));
    }
}
