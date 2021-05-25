/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;

/**
 * Action permettant de remplir l'arrosoir lorsqu'il est vide
 * @author Mathis Poncet
 */
public class ActionRemplir extends Action{
    /**
     * Direction vers laquelle il faut remplir l'arrosoir
     */
    private TypeMouvement direction;

    /**
     * Constructeur par initialisation
     * @param direction la direction vers laquelle il faut pencher son arrosoir
     */
    public ActionRemplir(TypeMouvement direction) {
        this.direction = direction;
    }
    
    @Override
    public String getMessage() {
        return "FILL|" + direction;
    }

    @Override
    public TypeAction getType() {
        return TypeAction.REMPLIR;
    }

    @Override
    public TypeMouvement getDirection() {
        return direction;
    }

    @Override
    public TypeRessource getTypeRessource() {
        return null;
    }
    
}
