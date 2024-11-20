package fr.umontpellier.iut.trains;

import java.util.List;

import org.junit.jupiter.api.Test;

import fr.umontpellier.iut.trains.cartes.*;

import static org.junit.jupiter.api.Assertions.*;

public class CartesTest extends  BaseTestClass{

    @Test
    void testBureauDuChefDeGareAvecHoraireEstivaux() {
        // Initialisation du jeu pour le test spécifique
        setupJeu("Bureau du chef de gare");
        initialisation();

        // Création des cartes spécifiques pour ce test
        Carte bureau = new BureauDuChefDeGare();
        Carte horairesEstivaux = new HorairesEstivaux();

        // Ajout des cartes à la main du joueur
        addAll(main, bureau, horairesEstivaux);

        // Simulation du tour de jeu où les actions sont exécutées
        jouerTourPartiel("Bureau du chef de gare", "Horaires estivaux", "oui");

        // Vérifications des conditions après le tour
        assertTrue(containsReferences(cartesEcartees, bureau), "La carte Bureau du Chef de Gare doit être écartée.");
        assertTrue(containsReferences(main, horairesEstivaux), "La carte Horaires Estivaux doit rester en main.");
        assertEquals(3, getArgent(joueur), "Le joueur doit avoir gagné 3 d'argent suite à l'action.");
    }
}

