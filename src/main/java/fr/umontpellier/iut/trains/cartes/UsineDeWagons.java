package fr.umontpellier.iut.trains.cartes;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;

public class UsineDeWagons extends Carte {
    public UsineDeWagons() {
        super("Usine de wagons", TypeCarte.ACTION, 0, 5);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choixPossibles = joueur.getMain().stream().filter(cartes -> cartes.getType() == TypeCarte.TRAIN || cartes.getType()
                == TypeCarte.TRAIN_ACTION).map(Carte::getNom).toList();
        String choisir = joueur.choisir("Choisissez la carte train à reveler",choixPossibles,null,true);
        if (!choisir.isEmpty()){
            Carte carteEcartee = joueur.getMain().retirer(choisir);
            joueur.getJeu().getCartesEcartees().add(carteEcartee);
            List<String> choixPossibles2 = new ArrayList<>();
            for (String nomCarte: joueur.getJeu().getReserve().keySet()) {
                if (joueur.getJeu().getReserve().get(nomCarte).getCarte(nomCarte) != null){
                    if(carteEcartee.getCout() +3 >= joueur.getJeu().getReserve().get(nomCarte).getCarte(nomCarte).getCout()){
                        choixPossibles2.add("ACHAT:" + nomCarte);
                    }
                }
            }
            String choisir2 = joueur.choisir("Choisissez la carte train à reveler",choixPossibles2,null,true);
            String splitCarte = choisir2.split(":")[1];
            joueur.getMain().add(joueur.getJeu().prendreDansLaReserve(splitCarte));
        }
    }
}

