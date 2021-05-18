/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.objet;

import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.ressources.TypeRessource;
import java.util.HashMap;

/**
 *
 * @author mp671721
 */
public abstract class Objet {
    private Case position;
    
    public Objet(Case postion){
        this.position = postion;
    }
    
    public Case getPosition() {
        return position;
    }
    
    public abstract TypeObjet getType();
    
    /**
     * permet de savoir si un objet bloque le passage de Abigail
     * @return true pour les arbres et la maison et renvoie false pour les autres
     * objets
     */
    public abstract boolean estBloquant();
    
    /**
     * Permet de savoir si un objet est récoltable par Abigail ou non
     * @return true si l'objet est récoltable
     */
    public boolean estRecoltable(){
        return false;
    }
    
    /**
     * Le coût de la récolte d'un objet
     * @return par défaut -1 unité
     */
    public int coutRecolte(){
        return -1;
    }
    
    /**
     * Méthode spécifiant la quantité d'une ressource pouvant être récupéré sur 
     * une case
     * @return une hashmap mettant en relation une ressource et sa quantité 
     * respective
     */
    public abstract HashMap<TypeRessource, Integer> getLoot();
}