package ia.base.modules;

import ia.base.IA;
import ia.base.metier.TypeMouvement;
import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.actions.TypeActionStatique;
import ia.base.metier.actions.TypeDemande;
import ia.base.metier.algorithmes.ParcoursLargeur;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.cases.Case;
import java.util.ArrayList;

/**
 * Module en charge de la prise de décision
 * @author Matthieu
 */
public class Module_Decision extends Module {
    private int compteur;
    private ArrayList<Action> listeDesActionsARealiser;
    
    /**
     * Constructeur
     * @param ia l'IA dont ce module fait partie
     */
    public Module_Decision(IA ia) {
        super(ia);
        this.listeDesActionsARealiser = new ArrayList<>();
        this.compteur = 0;
    }
    
    /**
     * Méthode principale de prise de décision
     * @param messageRecu dernier message reçu par l'IUA
     * @return le message à envoyer au serveur
     */
    public String determinerNouvelleAction(String messageRecu) {
        String messageAEnvoye = "END";
        //si le module mémoire ne dispose pas de carte la méthode renvoie "MAP"
        if ( !this.getIA().getModuleMemoire().hasCarte() ){
            this.listeDesActionsARealiser.add(FabriqueAction.creerDemande(TypeDemande.MAP));
        }
        
        //détermine de nouvelles actions si besoin
        if(this.listeDesActionsARealiser.isEmpty()){
            this.determinerNouvellesActions();
        }
        
        //Réalisation de la première action de la liste
        if(!this.listeDesActionsARealiser.isEmpty()){
            messageAEnvoye = this.listeDesActionsARealiser.get(0).getMessage();
            super.getIA().getModuleMemoire().effectuerAction(listeDesActionsARealiser.get(0));
            this.listeDesActionsARealiser.remove(0);
        }else{
            allerDormir();
            messageAEnvoye = this.listeDesActionsARealiser.get(0).getMessage();
            super.getIA().getModuleMemoire().effectuerAction(listeDesActionsARealiser.get(0));
            this.listeDesActionsARealiser.remove(0);
        } 
        return messageAEnvoye;
    }
    
    private void determinerNouvellesActions(){
        int ligne = (int) (Math.random() * super.getIA().getModuleMemoire().getCarte().getTaille());
        int colonne = (int) (Math.random() * super.getIA().getModuleMemoire().getCarte().getTaille());
        Coordonnee c = new Coordonnee(ligne, colonne);
        seDeplacerEn(c);
    }
    
    private void seDeplacerEn(Coordonnee coordonnee){
        System.out.println("---Je veux aller en " + coordonnee + "---");
        Carte carte = super.getIA().getModuleMemoire().getCarte();
        ParcoursLargeur parcoursLargeur = new ParcoursLargeur(carte);
        parcoursLargeur.calculerDistancesDepuis( super.getIA().getModuleMemoire().getCaseJoueur());
        
        Case caseDestination = carte.getCase(coordonnee);
        ArrayList<TypeMouvement> mouvements = parcoursLargeur.getChemin(caseDestination);
        for (TypeMouvement mouvement : mouvements) {
            this.listeDesActionsARealiser.add(FabriqueAction.creerMouvement(mouvement));
        }
    }
    
    /**
     * Déplace le joueur à la case de départ et le fait dormir
     */
    private void allerDormir(){
        this.seDeplacerEn(this.getIA().getModuleMemoire().getCarte().getCoordonneeDepart());
        this.listeDesActionsARealiser.add(FabriqueAction.creerActionStatique(TypeActionStatique.DORMIR));
    }
    
    private String script(){
        String message = "";
        final ArrayList<String> listeMov = new ArrayList<>(); 
        listeMov.add("MOVE|RIGHT");
        listeMov.add("MOVE|LEFT");
        listeMov.add("MOVE|BOTTOM");
        listeMov.add("MOVE|TOP");
        message = "MOVE|BOTTOM"; //quand le compteur est <5 le perso se déplace vers 
        //déplacement aléatoire à partir du 5 ème déplacement
        if (compteur>=5){
            message = listeMov.get((int) (Math.random()*4)); //déplacement aléatoire
        }
        compteur ++;
        return message;
    }
}
