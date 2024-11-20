package fr.umontpellier.iut.trains.cartes;
import fr.umontpellier.iut.trains.Joueur;

public class Aiguillage extends Carte {
    public Aiguillage() {
        super("Aiguillage",TypeCarte.ACTION,0,5);
    }
    public void jouer(Joueur joueur){
        joueur.getMain().addAll(joueur.piocher(2));
    }
}
