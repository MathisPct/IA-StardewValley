package ia.base.modules;

import ia.base.IA;
import ia.base.metier.Joueur;
import ia.base.metier.actions.Action;
import ia.base.metier.actions.TypeAction;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.cases.TypeCase;

/**
 * Module en charge de la mémorisation et de la restitution des informations obtenues
 * @author Matthieu
 */
public class Module_Memoire extends Module  {
    private Carte carte;
    /**
     * Joueur courant du jeu
     */
    private Joueur joueur;
    
    /**
     * Constructeur
     * @param ia l'IA dont ce module fait partie
     */
    public Module_Memoire(IA ia) {
        super(ia);
    }
    
    /**
     * Creer la carte en fonction du messageRecu 
     * @param messageRecu 
     */
    public void genererCarte(String messageRecu){
        this.carte = new Carte(messageRecu);
        genererJoueur(carte.getCoordonneeDepart());
        System.out.println("Coordonnée joueur: " + this.joueur.getCoordonnee());
    }
    
    /**
     * Retourne si la carte a été créer ou non
     * @return 
     */
    public boolean hasCarte(){
        return this.carte != null;
    }

    public Carte getCarte() {
        return carte;
    }

    public Joueur getJoueur() {
        return joueur;
    }
    
    public boolean hasJoueur(){
        return this.joueur != null;
    }
    
    public void genererJoueur(Coordonnee coordonnee){
        //si le joueur n'est pas encore crée
        if (!hasJoueur()){
            joueur = new Joueur(coordonnee);
        }
    }
    
    public void effectuerAction(Action action){
        if(action.getType()== TypeAction.MOUVEMENT){
            joueur.deplacer(action.getDirection());
        }
    }
    
    /**
     * Renvoie la case où se trouve le joueur
     * @return la case où se trouve le joueur
     */
    public Case getCaseJoueur(){
        return this.getCarte().getCase(this.joueur.getCoordonnee());
    }
}
