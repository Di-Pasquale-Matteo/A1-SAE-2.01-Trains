package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Echangeur extends Carte {
    public Echangeur() {
        super("Échangeur", TypeCarte.ACTION, 1, 3);
    }

    @Override
    public void jouer(Joueur joueur) {
        ListeDeCartes cartesEnJeu = joueur.getCartesEnJeu();

        String choixCarte = joueur.choisir("Choisissez une carte TRAIN à remettre sur le dessus de votre deck",
                cartesEnJeu.stream()
                        .filter(carte -> carte.getType() == TypeCarte.TRAIN)
                        .map(Carte::getNom)
                        .toList(),
                null,
                true);

        if (!choixCarte.isEmpty()) {
            Carte carteChoisie = cartesEnJeu.retirer(choixCarte);
            joueur.ajouterALaPioche(carteChoisie);
            joueur.log("La carte " + carteChoisie.getNom() + " a été remise sur le dessus du deck.");
        } else {
            joueur.log("Aucune carte remise sur le dessus du deck.");
        }
    }
}
