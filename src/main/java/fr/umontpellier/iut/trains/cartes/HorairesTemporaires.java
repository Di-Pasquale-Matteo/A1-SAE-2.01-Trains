package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class HorairesTemporaires extends Carte {
    public HorairesTemporaires() {
        super("Horaires temporaires",TypeCarte.ACTION,0,5);
    }

    @Override
    public void jouer(Joueur joueur) {
        int nbTrain=0;
        while (nbTrain < 2 && !joueur.getPioche().isEmpty()){
            Carte carte = joueur.piocher();
            if(carte.getType() == TypeCarte.TRAIN || carte.getType() == TypeCarte.TRAIN_ACTION){
                joueur.log("Carte obtenue : " + carte.getNom());
                joueur.getMain().add(carte);
                nbTrain++;
            }
            else{
                joueur.log("Carte dévoilée : " + carte.getNom());
                joueur.getDefausse().add(carte);
            }
        }
        if (nbTrain < 2){
            for (Carte c : joueur.getDefausse()){
                if(c.getType() == TypeCarte.TRAIN_ACTION || c.getType() == TypeCarte.TRAIN && nbTrain < 2){
                    joueur.getMain().add(joueur.getDefausse().retirer(c.getNom()));
                    nbTrain++;
                }
            }
        }
    }
}
