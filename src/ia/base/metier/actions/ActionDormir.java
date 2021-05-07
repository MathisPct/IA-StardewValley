/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;

/**
 *
 * @author Mathis Poncet
 */
public class ActionDormir extends Action{

    @Override
    public String getMessage() {
        return "SLEEP";
    }

    @Override
    public TypeAction getType() {
        return TypeAction.TYPESTATIQUE;
    }

    @Override
    public TypeMouvement getDirection() {
        return null;
    }
    
}
