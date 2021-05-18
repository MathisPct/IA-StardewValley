/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;

/**
 *
 * @author Mathis Poncet
 */
public abstract class Action {
    public abstract String getMessage();
    
    public abstract TypeAction getType();
    
    public abstract TypeMouvement getDirection();
    
    /**
     * Permet de savoir le type de ressource qui a été acheté
     * @return le type de ressource acheté
     */
    public abstract TypeRessource getTypeRessource();
}
