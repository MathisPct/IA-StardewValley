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
public class ActionDemande extends Action{
    private String message;
    
    public ActionDemande(String message) {
        this.message = message;
    }
    
    @Override
    public TypeMouvement getDirection() {
        return null;
    }

    @Override
    public TypeAction getType() {
        return TypeAction.DEMANDE;
    }
    
    @Override
    public String getMessage() {
        return message;
    }   

    @Override
    public TypeRessource getTypeRessource() {
        return null;
    }
}
