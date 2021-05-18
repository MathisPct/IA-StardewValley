/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;

/**
 * Action permettant d'acheter des ressources dans le magasin
 * @author Mathis Poncet
 */
public class ActionAcheter extends Action{
    /**
     * Le type de ressource que l'on souhaite acheter dans le magasin
     */
    private TypeRessource typeRessource;
    
    /**
     * Constructeur par initialisation
     * @param typeRessource que l'on souhaite acheter dans le magasin
     */
    public ActionAcheter(TypeRessource typeRessource) {
        this.typeRessource = typeRessource;
    }
    
    @Override
    public String getMessage() {
        return "BUY|" + typeRessource + "|1";
    }

    @Override
    public TypeAction getType() {
        return TypeAction.ACHAT;
    }

    @Override
    public TypeMouvement getDirection() {
        return null;
    }

    @Override
    public TypeRessource getTypeRessource() {
        return typeRessource;
    }
}
