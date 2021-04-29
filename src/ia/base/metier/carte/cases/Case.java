/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.cases;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.objet.Objet;
import java.util.ArrayList;

/**
 *
 * @author mp671721
 */
public abstract class Case  {
    private Coordonnee coordonnee;
    private Objet objet;
    private ArrayList<Case> voisins;
    
    public Case(Coordonnee coordonnees){
        this.voisins = new ArrayList<>();
        this.coordonnee = coordonnees;
    }
    
    abstract public TypeCase getType();

    public void setObjet(Objet objet) {
        this.objet = objet;
    }
    
    public Objet getObjet() {
        return objet;
    }

    public ArrayList<Case> getVoisins() {
        return voisins;
    }
    
    /**
     * ajoute un voisin à la liste des voisins de la case courante
     * @param voisin à ajouter à la liste des voisins de la case
     */
    public void ajouterVoisin(Case voisin){
        this.voisins.add(voisin);
    }

    public Coordonnee getCoordonnee() {
        return coordonnee;
    }
    
    /**
     * permet de savoir si une case herbe ou eau contient un objet bloquant
     * ou non
     * @return true si une case terre ou d'herbe ne contient pas d'objet bloquant 
     */
    public abstract boolean estAccessible();
    
    /**
     * Renvoie le type de mouvement pour aller de la case courante vers la 
     * case arrivée passée en paramètre
     * @param arrivee la case voisine à la case courante
     * @return le type de mouvement à réaliser
     */
    public TypeMouvement getMouvementPourAller(Case arrivee){
        TypeMouvement res = null;
        for(TypeMouvement mouvement : TypeMouvement.values()){
            if(this.coordonnee.getVoisin(mouvement).equals(arrivee.coordonnee)){
                res = mouvement;
            }
        }
        return res;
    }
}
