package ia.base.modules;

import ia.base.IA;
import ia.base.metier.Joueur;
import ia.base.metier.actions.Action;
import ia.base.metier.actions.TypeAction;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.cases.TypeCase;
import ia.base.metier.carte.objet.Objet;
import ia.base.metier.carte.ressources.TypeRessource;
import java.util.HashMap;

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
     * Inventaire du joueur avec chaque ressource et sa quantité respective
     */
    private HashMap<TypeRessource, Integer> inventaire;
    
    /**
     * 
     */
    private HashMap<TypeRessource, Integer> stockMagasin;
    
    /**
     * Constructeur
     * @param ia l'IA dont ce module fait partie
     */
    public Module_Memoire(IA ia) {
        super(ia);
        this.stockMagasin = null;
        this.inventaire = new HashMap<>();
        //on initialise les différentes ressources de l'inventaire et leur 
        //quantité. Pour l'or, il y a 500 unités
        for (TypeRessource ressource : TypeRessource.values()) {
            if(ressource != TypeRessource.GOLD){
                inventaire.put(ressource, 0); 
            }else{
                inventaire.put(ressource, 500);
            }
        }
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
        if(action.getType() == TypeAction.MOUVEMENT) {
            this.joueur.deplacer(action.getDirection()) ;
        }
        else if(action.getType() == TypeAction.RECOLTE) {
            if(action.getDirection() != null) {
                Case caseDestination = this.carte.getCase(this.getCaseJoueur().
                getCoordonnee().getVoisin(action.getDirection())) ;
                caseDestination.setObjet(null) ;
            }
        }else if(action.getType() == TypeAction.TYPESTATIQUE){
            this.stockMagasin = null;
        }
    }
    
    /**
     * Renvoie la case où se trouve le joueur
     * @return la case où se trouve le joueur
     */
    public Case getCaseJoueur(){
        return this.getCarte().getCase(this.joueur.getCoordonnee());
    }

    /**
     * Ajoute à l'inventaire les ressources obtenues en récoltant l'objet donné
     * @param objet donné dont nous récupérons la valeur en ressource afin de 
     * l'affecter à notre inventaire
     */
    private void recolter(Objet objet){
        if(objet != null){
            this.inventaire.putAll(objet.getLoot());
        }
    }
    
    /**
     * Permet de récupérer la quantité d'une ressource qui est possédé dans 
     * l'inventaire
     * @param type de ressource dont on veut la quantité dans l'inventaire
     * @return la quantité de la ressource passé en paramètre que l'on a dans 
     * l'inventaire
     */
    public int getQuantiteRessource(TypeRessource type){
        return this.inventaire.get(type);
    }
    
    /**
     * Permet de générer le stock du magasin qui est contenu dans la hashmap
     * stock magasin qui faire correspondre une ressource à sa quantité
     * @param nbGrainePanais quantité de panais
     * @param nbGraineChouxFleur quantité de choux fleur
     */
    public void genererStockMagasin(int nbGrainePanais, int nbGraineChouxFleur){
        this.stockMagasin = new HashMap<>();
        stockMagasin.put(TypeRessource.PARSNIPSEED, nbGrainePanais);
        stockMagasin.put(TypeRessource.CAULIFLOWERSEED, nbGraineChouxFleur);
    }
    
    /**
     * Permet de savoir si le magasin a du stock ou non 
     * @return true si le magasin possède du stock
     */
    public boolean hasStockMagasin(){
        boolean res = true;
        if(this.stockMagasin == null){
            res = false;
        }
        return res;
    }
}
