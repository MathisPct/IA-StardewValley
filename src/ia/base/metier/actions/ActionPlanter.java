/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;

/**
 * Action servant à planter des graines 
 * @author Mathis Poncet
 */
public class ActionPlanter extends Action{
    /**
     * Type de graine palntée 
     */
    private TypeRessource graine;

    /**
     * Constructeur par initialisation
     * @param graine à planter par l'ia
     */
    public ActionPlanter(TypeRessource graine) {
        this.graine = graine;
    }
    
    @Override
    public String getMessage() {
        return "PLANT|" + graine;
    }

    @Override
    public TypeAction getType() {
        return TypeAction.PLANTER;
    }

    @Override
    public TypeMouvement getDirection() {
        return null;
    }

    @Override
    public TypeRessource getTypeRessource() {
        return null;
    }
    
}
