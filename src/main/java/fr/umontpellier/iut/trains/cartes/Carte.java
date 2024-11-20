package fr.umontpellier.iut.trains  .cartes;

import fr.umontpellier.iut.trains.Joueur;

public abstract class Carte {
    private final String nom;
    private final TypeCarte type;
    private final int valeur;
    private final int cout;


    /**
     * Constructeur simple
     * <p>
     * Important : La classe Carte est actuellement très incomplète. Vous devrez
     * ajouter des attributs et des méthodes et donc probablement définir au moins
     * un autre constructeur plus complet. Les sous-classes de Cartes qui vous sont
     * fournies font appel à ce constructeur simple mais au fur et à mesure que vous
     * les compléterez, elles devront utiliser les autres constructeurs de Carte. Si
     * vous n'utilisez plus ce constructeur, vous pouvez le supprimer.
     * 
     * @param nom
     */
    public Carte(String nom, TypeCarte type, int valeur, int cout) {
        this.nom = nom;
        this.type = type;
        this.valeur = valeur;
        this.cout = cout;
    }

    public String getNom() {
        return nom;
    }

    public TypeCarte getType() {
        return type;
    }

    public int getValeur() {
        return valeur;
    }

    public int getCout() {
        return cout;
    }

    /**
     * Cette fonction est exécutée lorsqu'un joueur joue la carte pendant son tour.
     * Toutes les cartes ont une méthode jouer, mais elle ne fait rien par défaut.
     * 
     * @param joueur le joueur qui joue la carte
     */
    public void jouer(Joueur joueur) {
    }


    @Override
    public String toString() {
        return nom;
    }
}
