/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.objet;

import ia.base.metier.carte.cases.Case;

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
}
