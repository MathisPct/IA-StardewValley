package ia.base.modules;

import ia.base.IA;
import ia.base.metier.Joueur;
import ia.base.metier.actions.Action;
import ia.base.metier.actions.TypeAction;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.cases.CaseHerbe;
import ia.base.metier.carte.cases.TypeCase;
import ia.base.metier.carte.objet.FabriqueObjet;
import ia.base.metier.carte.objet.Objet;
import ia.base.metier.carte.objet.Oeuf;
import ia.base.metier.carte.objet.Plante;
import ia.base.metier.carte.objet.Poule;
import ia.base.metier.carte.objet.TypeObjet;
import ia.base.metier.carte.ressources.TypeRessource;
import java.util.ArrayList;
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
     * Liste des plantes que l'ia a récolté
     */
    private ArrayList<Plante> listePlantes;
    
    private ArrayList<Oeuf> listeOeufs;
    
    private ArrayList<Poule> listePoules;
    
    /**
     * Permet de savoir si les poules ont pondus (elles pondent à chaque 
     * nouvelle nuit)
     */
    private boolean oeufsMature;
    
    /**
     * La case vers laquelle le joueur est censée allé
     */
    private Case caseDestinationFinale;
    
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
                inventaire.put(ressource, 5000);
            }
        }
        this.inventaire.put(TypeRessource.EAU, 20);
        this.listePlantes = new ArrayList<>();
        this.listePoules = new ArrayList<>();
        this.oeufsMature = false;
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
        if(null != action.getType()) switch (action.getType()) {
            case MOUVEMENT:
                this.joueur.deplacer(action.getDirection());
                break;
            case RECOLTE:
                if(action.getDirection() != null) {
                    Case caseDestination = this.carte.getCase(this.getCaseJoueur().
                            getCoordonnee().getVoisin(action.getDirection())) ;
                    caseDestination.setObjet(null) ;
                }   
                break;
            case TYPESTATIQUE:
                this.stockMagasin = null;
                for (Plante p : listePlantes) {
                    p.grandir(); //permet de faire grandir la plante
                    p.setEstArrose(false);
                }  
                if(this.getQuantiteRessource(TypeRessource.CHICKEN) >= 1) this.oeufsMature = true;
                this.listeOeufs = null;
                break;
            case ACHAT:
                if(action.getTypeRessource() == TypeRessource.PARSNIPSEED){
                    this.inventaire.put(TypeRessource.GOLD, inventaire.get(TypeRessource.GOLD) - 20);
                    this.inventaire.put(TypeRessource.PARSNIPSEED, inventaire.get(TypeRessource.PARSNIPSEED) + 1);
                    this.stockMagasin.put(TypeRessource.PARSNIPSEED, stockMagasin.get(TypeRessource.PARSNIPSEED) - 1 );
                }else if(action.getTypeRessource() == TypeRessource.CAULIFLOWERSEED){
                    this.inventaire.put(TypeRessource.GOLD, inventaire.get(TypeRessource.GOLD) - 80);
                    this.inventaire.put(TypeRessource.CAULIFLOWERSEED, inventaire.get(TypeRessource.CAULIFLOWERSEED) + 1);
                    this.stockMagasin.put(TypeRessource.CAULIFLOWERSEED, stockMagasin.get(TypeRessource.CAULIFLOWERSEED) - 1);
                }else if(action.getTypeRessource() == TypeRessource.CHICKEN){
                    this.inventaire.put(TypeRessource.GOLD, inventaire.get(TypeRessource.GOLD) - 1000);
                    this.inventaire.put(TypeRessource.CHICKEN, inventaire.get(TypeRessource.CHICKEN) + 1);
                    this.stockMagasin.put(TypeRessource.CHICKEN, stockMagasin.get(TypeRessource.CHICKEN) - 1);
                }   
                break;
            case PLANTER:
                if(action.getTypeRessource() == TypeRessource.PARSNIPSEED){
                    addPlante(TypeObjet.PANAIS);
                    this.inventaire.put(TypeRessource.PARSNIPSEED, inventaire.get(TypeRessource.PARSNIPSEED) - 1);
                }else if(action.getTypeRessource() == TypeRessource.CAULIFLOWERSEED){
                    addPlante(TypeObjet.CHOUFLEUR);
                    this.inventaire.put(TypeRessource.CAULIFLOWERSEED, inventaire.get(TypeRessource.CAULIFLOWERSEED) - 1);
                }   
                break;
            case ARROSER:
                this.inventaire.put(TypeRessource.EAU, inventaire.get(TypeRessource.EAU) - 1);
                for (Plante p : listePlantes) {
                    if(p.getPosition() == getCaseJoueur()){
                        p.setEstArrose(true);
                    }
                }   
                break;
            case REMPLIR:
                this.inventaire.put(TypeRessource.EAU, 20);
                break;
            case CUEILLIR:
                recolterPlante();
                break;
            case RECOLTEOEUF:
                recolterOeuf();
                this.oeufsMature = false; //les oeufs ont tous été récoltés
                break;
            case VENTE:
                if(action.getTypeRessource() == TypeRessource.PARSNIPMATURE){
                    this.inventaire.put(TypeRessource.GOLD, inventaire.get(TypeRessource.GOLD) + 35);
                    this.inventaire.put(TypeRessource.PARSNIPMATURE, inventaire.get(TypeRessource.PARSNIPMATURE) - 1);
                }else if(action.getTypeRessource() == TypeRessource.CAULIFLOWERMATURE){
                    this.inventaire.put(TypeRessource.GOLD, inventaire.get(TypeRessource.GOLD) + 175);
                    this.inventaire.put(TypeRessource.CAULIFLOWERMATURE, inventaire.get(TypeRessource.CAULIFLOWERMATURE) - 1);
                }else if(action.getTypeRessource() == TypeRessource.EGG){
                    this.inventaire.put(TypeRessource.GOLD, inventaire.get(TypeRessource.GOLD) + 50);
                    this.inventaire.put(TypeRessource.EGG, inventaire.get(TypeRessource.EGG) - 1);
                }   
                break;
            default:
                break;
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
            for (TypeRessource ressourceInven: inventaire.keySet()) {
                for (TypeRessource ressourceObjet : objet.getLoot().keySet()) {
                   if(ressourceInven == ressourceObjet){
                       this.inventaire.put(ressourceObjet, inventaire.get(ressourceObjet) + objet.getLoot().get(ressourceObjet));
                   } 
                }
            }
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
        stockMagasin.put(TypeRessource.CHICKEN, 5);
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
    
    /**
     * Permet de savoir si la recherches des oeufs a été faite sur la carte
     * @return true si la recherche a été effectuée
     */
    public boolean hasOeufsCarte(){
        boolean res = true;
        if(this.listeOeufs == null) res = false;
        return res;
    }
    
    /**
     * Permet de savoir la quantité d'une ressource que possède le magasin
     * @param type de ressource dont on veut savoir la quantité
     * @return la quantité de la ressource passée en paramètre présente dans 
     * le magasin
     */
    public int getStockMagasin(TypeRessource type){
        return this.stockMagasin.get(type);
    }
    
    /**
     * demande d'ajouter une plante à la carte
     * @param type de la plante 
     */
    private void addPlante(TypeObjet type){
        Plante plante = FabriqueObjet.creerPlante(getCaseJoueur(), type);
        this.listePlantes.add(plante);
        getCaseJoueur().setObjet(plante);
    }
    
    /**
     * Récolte l'objet situé à la case du joueur, le retire de la 
     * liste des plantes et supprime l'objet de la case
     * 
     */
    private void recolterPlante(){
        Case c = getCarte().getCase(getCaseJoueur().getCoordonnee());
        recolter(c.getObjet());
        this.listePlantes.remove(c.getObjet());
        getCarte().getCase(c.getCoordonnee()).setObjet(null);
    }
    
    /**
     * Permet de retirer l'oeuf d'une case de la carte dès que le joueur
     * l'a récupéré
     */
    private void recolterOeuf(){
        Case c = getCarte().getCase(getCaseJoueur().getCoordonnee());
        recolter(c.getObjet());
        this.listeOeufs.remove(c.getObjet());
        getCarte().getCase(c.getCoordonnee()).setObjet(null);
    }

    public ArrayList<Plante> getListePlantes() {
        return listePlantes;
    }

    public ArrayList<Oeuf> getListeOeufs() {
        return listeOeufs;   
    }
    
    /**
     * Permet d'initialiser la liste des oeufs lorsqu'il y en a sur la carte
     */
    public void initListeOeufs(){
        this.listeOeufs = new ArrayList<>();
    }
    
    /**
     * Permet de générer la liste qui contient tous les oeufs qu'il y a sur 
     * la carte
     * @param ligne de l'oeuf
     * @param col de l'oeuf
     */
    public void genererOeufsCarte(int ligne, int col){
        Coordonnee coordOeuf = new Coordonnee(ligne, col);
        Case cOeuf = getCarte().getCase(coordOeuf);
        Oeuf oeuf = new Oeuf(cOeuf);
        getCarte().getCase(coordOeuf).setObjet(oeuf);
        this.listeOeufs.add(oeuf);
    }
    
    /**
     * Réinitialise la liste des poules à chaque déplacement d'abigail et enlève
     * les poules sur les cases de la carte
     */
    public void initListePoules(){
        if(!this.listePoules.isEmpty()){
            for (Case c : getCarte().getCases()) {
                for (Poule p : listePoules) {
                    if(c == p.getPosition()){
                        c.setObjet(null);
                    }
                }
            }
        }
        this.listePoules = new ArrayList<>();
    }
    
    /**
     * Permet de générer une poule sur une case de la carte et l'ajoute à la liste
     * des poules de la carte
     * @param ligne de la poule 
     * @param col de la poule
     */
    public void genererPoulesCarte(int ligne, int col){
        Coordonnee coordPoule = new Coordonnee(ligne, col);
        Case cPoule = getCarte().getCase(coordPoule);
        Poule poule = new Poule(cPoule);
        getCarte().getCase(coordPoule).setObjet(poule);
        this.listePoules.add(poule);
    }
    
    public boolean isOeufMatures() {
        return oeufsMature;
    }

    public Case getCaseDestinationFinale() {
        return caseDestinationFinale;
    }

    public void setCaseDestinationFinale(Case caseDestinationFinale) {
        this.caseDestinationFinale = caseDestinationFinale;
    } 
}
