    package fr.umontpellier.iut.trains;

    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.HashSet;
    import java.util.List;
    import java.util.Map;
    import java.util.StringJoiner;

import fr.umontpellier.iut.trains.cartes.Carte;
import fr.umontpellier.iut.trains.cartes.FabriqueListeDeCartes;
import fr.umontpellier.iut.trains.cartes.ListeDeCartes;
import fr.umontpellier.iut.trains.cartes.TypeCarte;
import fr.umontpellier.iut.trains.plateau.*;

    public class Joueur {
        /**
         * Le jeu auquel le joueur est rattaché
         */
        private Jeu jeu;
        /**
         * Nom du joueur (pour les affichages console et UI)
         */
        private String nom;
        /**
         * Quantité d'argent que le joueur a (remis à zéro entre les tours)
         */
        private int argent;
        /**
         * Nombre de points rails dont le joueur dispose. Ces points sont obtenus en
         * jouant les cartes RAIL (vertes) et remis à zéro entre les tous
         */
        private int pointsRails;
        /**
         * Nombre de jetons rails disponibles (non placés sur le plateau)
         */
        private int nbJetonsRails;
        /**
         * Liste des cartes en main
         */
        private ListeDeCartes main;
        /**
         * Liste des cartes dans la pioche (le début de la liste correspond au haut de
         * la pile)
         */
        private ListeDeCartes pioche;
        /**
         * Liste de cartes dans la défausse
         */
        private ListeDeCartes defausse;
        /**
         * Liste des cartes en jeu (cartes jouées par le joueur pendant le tour)
         */
        private ListeDeCartes cartesEnJeu;
        /**
         * Liste des cartes reçues pendant le tour
         */
        private ListeDeCartes cartesRecues;
        /**
         * Couleur du joueur (utilisé par l'interface graphique)
         */
        private CouleurJoueur couleur;

        private int scoreCourant;

        private ArrayList<String> listeEffet;

        private boolean carteSeRetire;

        public Joueur(Jeu jeu, String nom, CouleurJoueur couleur) {
            this.jeu = jeu;
            this.nom = nom;
            this.couleur = couleur;
            argent = 0;
            pointsRails = 0;
            nbJetonsRails = 20;
            main = new ListeDeCartes();
            defausse = new ListeDeCartes();
            pioche = new ListeDeCartes();
            cartesEnJeu = new ListeDeCartes();
            cartesRecues = new ListeDeCartes();
            scoreCourant = 0;
            listeEffet = new ArrayList<>();
            carteSeRetire=false;

            // créer 7 Train omnibus (non disponibles dans la réserve)
            pioche.addAll(FabriqueListeDeCartes.creerListeDeCartes("Train omnibus", 7));
            // prendre 2 Pose de rails de la réserve
            for (int i = 0; i < 2; i++) {
                pioche.add(jeu.prendreDansLaReserve("Pose de rails"));
            }
            // prendre 1 Gare de la réserve
            pioche.add(jeu.prendreDansLaReserve("Gare"));

            // mélanger la pioche
            pioche.melanger();
            // Piocher 5 cartes en main
            // Remarque : on peut aussi appeler piocherEnMain(5) si la méthode est écrite
            for (int i = 0; i < 5; i++) {
                main.add(pioche.remove(0));
            }

        }

        public Jeu getJeu() {
            return jeu;
        }

        public String getNom() {
            return nom;
        }

        public CouleurJoueur getCouleur() {
            return couleur;
        }

        /**
         * Renvoie le score total du joueur
         * <p>
         * Le score total est la somme des points obtenus par les effets suivants :
         * <ul>
         * <li>points de rails (villes et lieux éloignés sur lesquels le joueur a posé
         * un rail)
         * <li>points des cartes possédées par le joueur (cartes VICTOIRE jaunes)
         * <li>score courant du joueur (points marqués en jouant des cartes pendant la
         * partie p.ex. Train de tourisme)
         * </ul>
         * public void jouer(Joueur joueur){
            int nbCartes = 0;
            List<String> choixPossibles = joueur.getMain().stream().filter(cartes -> cartes != this).map(Carte::getNom).toList();
            String choisir = joueur.choisir("Choisissez une carte à défausser",choixPossibles,null,true);
            while(!choisir.isEmpty()){
                nbCartes++;
                joueur.defausser(joueur.getMain().getCarte(choisir));
                choixPossibles = joueur.getMain().stream().filter(cartes -> cartes != this).map(Carte::getNom).toList();
                choisir = joueur.choisir("Choisissez une carte à défausser",choixPossibles,null,true);
            }
            joueur.piocher(nbCartes);
        }
         * @return le score total du joueur
         */
        public int getScoreTotal() {
            int pointDeRails = 0;
            for (Tuile tuile : jeu.getTuiles()){
                if (tuile.hasRail(this)){
                    if (tuile.getNbGares()>=1){
                        pointDeRails += (int) Math.pow(2,tuile.getNbGares());
                    }
                    else if (tuile instanceof TuileEtoile tuileEtoile){
                        pointDeRails += tuileEtoile.getValeur();
                    }
                }
            }
            return pointDeRails + scoreCourant;
        }

        public void marque(int points){
            scoreCourant+=points;
        }

        public void addPointRail(int nouvPointsRail){
            pointsRails+=nouvPointsRail;
        }

        public int getNbJetonsRails() {
            return nbJetonsRails;
        }

        /**
         * Retire et renvoie la première carte de la pioche.
         * <p>
         * Si la pioche est vide, la méthode commence par mélanger toute la défausse
         * dans la pioche.
         *
         * @return la carte piochée ou {@code null} si aucune carte disponible
         */
        public Carte piocher() {
            // À FAIRE
            if (pioche.isEmpty()){
                pioche.addAll(defausse.retirerAll());
                pioche.melanger();
            }
            if(pioche.isEmpty()){
                return null;
            }
            else{
                return pioche.remove(0);
            }
        }

        public ListeDeCartes getCartesRecues() {
            return cartesRecues;
        }

        public ArrayList<String> getListeEffet() {
            return listeEffet;
        }

        public void setCarteSeRetire(boolean carteSeRetire) {
            this.carteSeRetire = carteSeRetire;
        }

        public void enleverNbJetonsRails() {
            nbJetonsRails--;
        }

        /**
         * Retire et renvoie les {@code n} premières cartes de la pioche.
         * <p>
         * Si à un moment il faut encore piocher des cartes et que la pioche est vide,
         * la défausse est mélangée et toutes ses cartes sont déplacées dans la pioche.
         * S'il n'y a plus de cartes à piocher la méthode s'interromp et les cartes qui
         * ont pu être piochées sont renvoyées.
         *
         * @param n nombre de cartes à piocher
         * @return une liste des cartes piochées (la liste peut contenir moins de n
         *         éléments si pas assez de cartes disponibles dans la pioche et la
         *         défausse)
         */
        public List<Carte> piocher(int n) {
            // À FAIRE
            List<Carte> cartesPiochees = new ArrayList<>();
            int i = 0;
            Carte carte;
            boolean carteNull = false;
            while(i < n && !carteNull){
                carte = piocher();
                if (carte != null){
                    cartesPiochees.add(carte);
                }
                else{
                    carteNull = true;
                }
                i++;
            }
            return cartesPiochees;
        }

        public void defausser(Carte carte){
            main.retirer(carte.getNom());
            defausse.add(carte);
        }


        /**
         * Joue un tour complet du joueur
         * <p>
         * Le tour du joueur se déroule en plusieurs étapes :
         * <ol>
         * <li>Initialisation
         * <p>
         * Dans ce jeu il n'y a rien de particulier à faire en début de tour à part un
         * éventuel affichage dans le log.
         *
         * <li>Boucle principale
         * <p>
         * C'est le cœur de la fonction. Tant que le tour du joueur n'est pas terminé,
         * il faut préparer la liste de tous les choix valides que le joueur peut faire
         * (jouer des cartes, poser des rails, acheter des cartes, etc.), puis demander
         * au joueur de choisir une action (en appelant la méthode {@code choisir}).
         * <p>
         * Ensuite, en fonction du choix du joueur il faut exécuter l'action demandée et
         * recommencer la boucle si le tour n'est pas terminé.
         * <p>
         * Le tour se termine lorsque le joueur décide de passer (il choisit {@code ""})
         * ou lorsqu'il exécute une action qui termine automatiquement le tour (par
         * exemple s'il choisit de recycler toutes ses cartes Ferraille en début de
         * tour)
         *
         * <li>Finalisation
         * <p>
         * Actions à exécuter à la fin du tour : réinitialiser les attributs
         * du joueur qui sont spécifiques au tour (argent, rails, etc.), défausser
         * toutes les
         * cartes, piocher 5 nouvelles cartes en main, etc.
         * </ol>
         */
        public void jouerTour() {
            // Initialisation
            jeu.log("<div class=\"tour\">Tour de " + toLog() + "</div>");
            // À FAIRE: compléter l'initialisation du tour si nécessaire (mais possiblement
            // rien de spécial à faire)
            boolean aJouer = false;
            boolean finTour = false;
            // Boucle principale
            while (!finTour) {
                carteSeRetire=false;
                List<String> choixPossibles = new ArrayList<>();
                // À FAIRE: préparer la liste des choix possibles
                for (Carte c: main) {
                    // ajoute les noms de toutes les cartes en main
                    choixPossibles.add(c.getNom());
                }
                for (String nomCarte: jeu.getReserve().keySet()) {
                    // ajoute les noms des cartes dans la réserve préfixés de "ACHAT:"
                    choixPossibles.add("ACHAT:" + nomCarte);
                }
                for (int i = 0; i <= 75; i++) {
                    if (peutPoserRail(jeu.getTuile(i))){
                        choixPossibles.add("TUILE:"+i);
                    }
                }
                // Choix de l'action à réaliser
                String choix = choisir(String.format("Tour de %s", this.nom), choixPossibles, null, true);

                // À FAIRE: exécuter l'action demandée par le joueur
                if (choix.startsWith("ACHAT:")) {
                    // prendre une carte dans la réserve
                    String nomCarte = choix.split(":")[1];
                    Carte carte = null;
                    if(!nomCarte.equals("Ferraille")){
                        carte = jeu.prendreDansLaReserve(nomCarte);
                    }
                    if (carte != null) {
                        if (argent >= carte.getCout()){
                            aJouer=true;
                            log("Reçoit " + carte); // affichage dans le log
                            if (listeEffet.contains("Achat carte")){
                                List<String> choixPossiblesAchat = new ArrayList<>();
                                choixPossiblesAchat.add("oui");
                                choixPossiblesAchat.add("non");
                                List<Bouton> listeBoutons = new ArrayList<>();
                                listeBoutons.add(new Bouton("oui","oui"));
                                listeBoutons.add(new Bouton("non","non"));
                                String choixAchat = choisir("Voulez vous mettre cette carte sur votre deck ?", choixPossiblesAchat,listeBoutons,false);
                                if (choixAchat.equals("oui")){
                                    pioche.add(0,carte);
                                }
                                else{
                                    cartesRecues.add(carte);
                                }
                            }
                            else {
                                cartesRecues.add(carte);
                            }
                            argent -= carte.getCout();
                            if (carte.getType()==TypeCarte.VICTOIRE){
                                carte.jouer(this);
                            }
                        }
                        else{
                            mettreDansLaReserve(carte);
                        }
                    }
                } else if (choix.startsWith("TUILE:")) {
                    if (!choix.equals("TUILE:")) {
                        int numTuile = Integer.parseInt(choix.split(":")[1]);
                        boolean assezArgent = true;
                        int argentNecessaire = 0;
                        if (!listeEffet.contains("Surcout")) {
                            if (jeu.getTuile(numTuile) instanceof TuileVille tuile) {
                                argentNecessaire = coutPoseRail(tuile);
                                if (argent < argentNecessaire) {
                                    assezArgent = false;
                                }
                            } else if (jeu.getTuile(numTuile) instanceof TuileEtoile tuile) {
                                argentNecessaire = coutPoseRail(tuile);
                                if (argent < argentNecessaire) {
                                    assezArgent = false;
                                }
                            } else {
                                TuileTerrain tuile = (TuileTerrain) jeu.getTuile(numTuile);
                                argentNecessaire = coutPoseRail(tuile);
                                if (argent < argentNecessaire) {
                                    assezArgent = false;
                                }
                            }
                        }
                        if (assezArgent) {
                            if (!jeu.getTuile(numTuile).getRails().isEmpty() && !listeEffet.contains("Surcout sur rail")) {
                                piocherFerraille();
                            }
                            jeu.getTuile(numTuile).ajouterRail(this);
                            ajouterArgent(-argentNecessaire);
                            pointsRails--;
                            nbJetonsRails--;
                        } else {
                            log("Vous n'avez pas assez d'argent pour poser un rail ici.");
                        }
                    }
                } else if (choix.equals("")) {
                    // terminer le tour
                    finTour = true;
                } else if (choix.equals("Ferraille") && !aJouer){
                    int nbrFerraille = main.count("Ferraille");
                    for (int i = 0; i < nbrFerraille; i++) {
                        mettreDansLaReserve(main.retirer("Ferraille"));
                    }
                    finTour=true;
                } else if (!choix.equals("Ferraille") && main.getCarte(choix).getType() != TypeCarte.VICTOIRE){
                    // jouer une carte de la main
                    Carte carte = main.retirer(choix);
                    log("Joue " + carte); // affichage dans le log
                    carte.jouer(this);  // exécuter l'action de la carte
                    if (!carteSeRetire){
                        cartesEnJeu.add(carte); // mettre la carte en jeu
                    }
                    else{
                        jeu.getCartesEcartees().add(carte);  // Ajouter la carte aux cartes écartées
                        log("Carte écartée : " + carte.getNom());
                    }
                    aJouer=true;
                    ajouterArgent(carte);
                    if (listeEffet.contains("Jouer rail") && carte.getType()==TypeCarte.RAILS){
                        ajouterArgent(2);
                    }
                }
            }
            // Finalisation
            // À FAIRE: compléter la finalisation du tour
            // défausser toutes les cartes
            defausse.addAll(main);
            main.clear();
            defausse.addAll(cartesRecues);
            cartesRecues.clear();
            defausse.addAll(cartesEnJeu);
            cartesEnJeu.clear();
            listeEffet.clear();
            pointsRails = 0;
            argent=0;

            main.addAll(piocher(5)); // piocher 5 cartes en main
        }

        /**
         * Attend une entrée de la part du joueur (au clavier ou sur la websocket) et
         * renvoie le choix du joueur.
         * <p>
         * Cette méthode lit les entrées du jeu ({@code Jeu.lireligne()}) jusqu'à ce
         * qu'un choix valide (un élément de {@code choix} ou la valeur d'un élément de
         * {@code boutons} ou éventuellement la chaîne vide si l'utilisateur est
         * autorisé à passer) soit reçu.
         * Lorsqu'un choix valide est obtenu, il est renvoyé par la fonction.
         * <p>
         * Exemple d'utilisation pour demander à un joueur de répondre à une question
         * par "oui" ou "non" :
         * <p>
         *
         * <pre>{@code
         * List<String> choix = Arrays.asList("oui", "non");
         * String input = choisir("Voulez-vous faire ceci ?", choix, null, false);
         * }</pre>
         * <p>
         * Si par contre on voulait proposer les réponses à l'aide de boutons, on
         * pourrait utiliser :
         *
         * <pre>{@code
         * List<String> boutons = Arrays.asList(new Bouton("Oui !", "oui"), new Bouton("Non !", "non"));
         * String input = choisir("Voulez-vous faire ceci ?", null, boutons, false);
         * }</pre>
         *
         * (ici le premier bouton a le label "Oui !" et envoie la String "oui" s'il est
         * cliqué, le second a le label "Non !" et envoie la String "non" lorsqu'il est
         * cliqué)
         *
         * <p>
         * <b>Remarque :</b> Normalement, si le paramètre {@code peutPasser} est
         * {@code false} le choix
         * {@code ""} n'est pas valide. Cependant s'il n'y a aucun choix proposé (les
         * listes {@code choix} et {@code boutons} sont vides ou {@code null}), le choix
         * {@code ""} est accepté pour éviter un blocage.
         *
         * @param instruction message à afficher à l'écran pour indiquer au joueur la
         *                    nature du choix qui est attendu
         * @param choix       une collection de chaînes de caractères correspondant aux
         *                    choix valides attendus du joueur (ou {@code null})
         * @param boutons     une liste d'objets de type {@code Bouton} définis par deux
         *                    chaînes de caractères (label, valeur) correspondant aux
         *                    choix valides attendus du joueur qui doivent être
         *                    représentés par des boutons sur l'interface graphique (le
         *                    label est affiché sur le bouton, la valeur est ce qui est
         *                    envoyé au jeu quand le bouton est cliqué) ou {@code null}
         * @param peutPasser  booléen indiquant si le joueur a le droit de passer sans
         *                    faire de choix. S'il est autorisé à passer, c'est la
         *                    chaîne de caractères vide ({@code ""}) qui signifie qu'il
         *                    désire passer.
         * @return le choix de l'utilisateur (un élement de {@code choix}, ou la valeur
         *         d'un élément de {@code boutons} ou la chaîne vide)
         */
        public String choisir(
                String instruction,
                Collection<String> choix,
                List<Bouton> boutons,
                boolean peutPasser) {
            if (choix == null)
                choix = new ArrayList<>();
            if (boutons == null)
                boutons = new ArrayList<>();

            HashSet<String> choixDistincts = new HashSet<>(choix);
            choixDistincts.addAll(boutons.stream().map(Bouton::valeur).toList());
            if (peutPasser || choixDistincts.isEmpty()) {
                // si le joueur a le droit de passer ou s'il n'existe aucun choix valide, on
                // ajoute "" à la liste des choix possibles
                choixDistincts.add("");
            }

            String entree;
            // Lit l'entrée de l'utilisateur jusqu'à obtenir un choix valide
            while (true) {
                jeu.prompt(instruction, boutons, peutPasser);
                entree = jeu.lireLigne();
                // si une réponse valide est obtenue, elle est renvoyée
                if (choixDistincts.contains(entree)) {
                    return entree;
                }
            }
        }

        /**
         * Ajoute un message dans le log du jeu
         *
         * @param message message à ajouter dans le log
         */
        public void log(String message) {
            jeu.log(message);
        }

        public void nouvelleCarte(Carte carte){
            cartesRecues.add(carte);
        }

        @Override
        public String toString() {
            // Vous pouvez modifier cette fonction comme bon vous semble pour afficher
            // d'autres informations si nécessaire
            StringJoiner joiner = new StringJoiner("\n");
            joiner.add(String.format("=== %s (%d pts) ===", nom, getScoreTotal()));
            joiner.add(String.format("  Argent: %d  Rails: %d", argent, pointsRails));
            joiner.add("  Cartes en jeu: " + cartesEnJeu);
            joiner.add("  Cartes reçues: " + cartesRecues);
            joiner.add("  Cartes en main: " + main);
            return joiner.toString();
        }

        /**
         * @return une représentation du joueur pour l'affichage dans le log du jeu
         */
        public String toLog() {
            return String.format("<span class=\"joueur %s\">%s</span>", couleur.toString(), nom);
        }

        /**
         * @return une représentation du joueur sous la forme d'un dictionnaire de
         *         valeurs sérialisables (qui sera converti en JSON pour l'envoyer à
         *         l'interface graphique)
         */
        Map<String, Object> dataMap() {
            return Map.ofEntries(
                    Map.entry("nom", nom),
                    Map.entry("couleur", couleur),
                    Map.entry("scoreTotal", getScoreTotal()),
                    Map.entry("argent", argent),
                    Map.entry("rails", pointsRails),
                    Map.entry("nbJetonsRails", nbJetonsRails),
                    Map.entry("main", main.dataMap()),
                    Map.entry("defausse", defausse.dataMap()),
                    Map.entry("cartesEnJeu", cartesEnJeu.dataMap()),
                    Map.entry("cartesRecues", cartesRecues.dataMap()),
                    Map.entry("pioche", pioche.dataMap()),
                    Map.entry("actif", jeu.getJoueurCourant() == this));
        }

        public ListeDeCartes getMain() {
            return main;
        }

        public ListeDeCartes getDefausse() {
            return defausse;
        }

        public void ajouterALaPioche(Carte carte) {
            if (carte != null) {
                pioche.add(0, carte);
            }
        }

        public boolean peutPoserRail(Tuile t ){
            if (t.hasRail(this)){
                return false;
            }
            if(t.getVoisines().stream().noneMatch(tuiles -> tuiles.hasRail(this))){
                return false;
            }
            if (t instanceof TuileMer){
                return false;
            }
            return nbJetonsRails != 0 && pointsRails != 0;
        }

        public int coutPoseRail(TuileVille t){
            int coutPose = 0;
            if (!listeEffet.contains("Surcout sur Rail")){
                coutPose = t.getRails().size();
            }
            if (!listeEffet.contains("Surcout ville")){
                return coutPose+1+t.getNbGares();
            }
            else {
                return coutPose;
            }
        }

        public int coutPoseRail(TuileEtoile t){
            int coutPose = 0;
            if (!listeEffet.contains("Surcout sur Rail")){
                coutPose = t.getRails().size();
            }
            return coutPose + t.getValeur();
        }

        public int coutPoseRail(TuileTerrain t){
            int coutPose = 0;
            if (!listeEffet.contains("Surcout sur Rail")){
                coutPose = t.getRails().size();
            }
            if (t.getType() == TypeTerrain.MONTAGNE && !listeEffet.contains("Surcout montagne")){
                return coutPose+2;
            }
            else if (t.getType() == TypeTerrain.FLEUVE && !listeEffet.contains("Surcout fleuve")){
                return coutPose + 1;
            }
            else{
                return coutPose;
            }
        }

        public Carte regarderLaProchaineCarte() {
            if (pioche.isEmpty()) {
                return null;
            } else {
                return pioche.get(0);
            }
        }

        public Carte choisirCarte(String message, List<Carte> cartes) {
            String carteChoisieNom = choisir(message, cartes.stream().map(Carte::getNom).toList(), null, true);
            return cartes.stream().filter(carte -> carte.getNom().equals(carteChoisieNom)).findFirst().orElse(null);
        }

        public void ajouterArgent(Carte carte) {
            argent += carte.getValeur();
        }

        public void ajouterArgent(int montant) {
            argent += montant;
        }

        public void piocherFerraille(){
            if (!listeEffet.contains("Piocher ferraille")) {
                Carte ferraille = jeu.prendreDansLaReserve("Ferraille");
                if (ferraille != null) {
                    cartesRecues.add(ferraille);
                    log("Carte ferraille piochée");
                } else {
                    log("Reserve de carte ferraille vide");
                }
            }
        }

        public ListeDeCartes getCartesEnJeu() {
            return this.cartesEnJeu;
        }

        public ListeDeCartes getPioche() {
            return pioche;
        }


        public void mettreDansLaReserve(Carte carte) {
            String nomCarte = carte.getNom();
            if (!jeu.getReserve().containsKey(nomCarte)) {
                jeu.getReserve().put(nomCarte, new ListeDeCartes());
            }
            jeu.getReserve().get(nomCarte).add(carte);
            log("La carte " + nomCarte + " a été remise dans la réserve.");
        }

        public void placerRailDebut(){
            ArrayList<String> choixPossibles = new ArrayList<>();
            for (int j = 0; j <= 75; j++) {
                if (!(jeu.getTuiles().get(j) instanceof TuileMer) && !(jeu.getTuiles().get(j) instanceof TuileEtoile) && jeu.getTuiles().get(j).getRails().isEmpty()){
                    choixPossibles.add("TUILE:"+j);
                }
            }
            String choix = choisir(String.format("%s : Choisissez une tuile sur laquelle poser un rail.", this.nom), choixPossibles, null, false);
            int numTuile = Integer.parseInt(choix.split(":")[1]);
            jeu.getTuile(numTuile).ajouterRail(this);
        }
    }

