/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;

/**
 * Permet de vendre des ressources
 * @author Mathis Poncet
 */
public class ActionVendre extends Action{
    /**
     * Le type de ressource que l'on souaite vendre dans le magasin
     */
    private TypeRessource typeRessource;
    
    /**
     * Constructeur par initialisation
     * @param typeRessource que l'on souhaite vendre dans le magasin
     */
    public ActionVendre(TypeRessource typeRessource) {
        this.typeRessource = typeRessource;
    }
    
    @Override
    public String getMessage() {
        return "SELL|" + typeRessource + "|1";
    }

    @Override
    public TypeAction getType() {
        return TypeAction.VENTE;
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
