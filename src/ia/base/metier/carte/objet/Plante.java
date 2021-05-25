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
 * @author Mathis Poncet
 */
public abstract class Plante extends Objet{
    /**
     * Permet de savoir si une plante est arrosée ou non
     */
    private boolean estArrose;
    
    /**
     * Permet de connaître l'âge d'une plante afin de gérer sa maturité
     */
    private int age;
    
    public Plante(Case postion) {
        super(postion);
        this.estArrose = false;
        this.age = 0;
    }

    @Override
    public TypeObjet getType() {
        return null;
    }
    
    /**
     * Une plante peut-être récoltée par le joueur
     * @return si l'objet est bloquant 
     */
    @Override
    public boolean estBloquant() {
        return false;
    }

    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        return null;
    }

    public boolean isEstArrose() {
        return estArrose;
    }

    public void setEstArrose(boolean estArrose) {
        this.estArrose = estArrose;
    }
    
    /**
     * Augmente de 1 l'âge de la plante
     */
    public void grandir(){
        age ++;
    }
    
    /**
     * Permet de savoir si une plante est mature ou non 
     * @return true si elle est mature
     */
    public abstract boolean estMature();
    
    protected int getAge(){
        return this.age;
    }
}
