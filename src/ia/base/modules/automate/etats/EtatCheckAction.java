/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Regarde si une action est déjà planifiée
 * @author Mathis Poncet
 */
public class EtatCheckAction extends Etat{

    public EtatCheckAction(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        Etat etat = new EtatRealiserAction(getAutomate());
        if (getAutomate().getListeDesActionsARealiser().isEmpty()){
            //etat = new EtatAllerVersArbre(getAutomate());
            //etat = new EtatAcheter(getAutomate());
            etat = new EtatAllerRecolter(getAutomate());
        }
        return etat;
    }

    @Override
    public Action action() {
        return null;
    }
    
}
