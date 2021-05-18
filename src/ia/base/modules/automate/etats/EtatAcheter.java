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
 * Va acheter des graines
 * @author Mathis Poncet
 */
public class EtatAcheter extends Etat{

    public EtatAcheter(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        Etat etat = new EtatDemandeMagasin(this.getAutomate());
        if(super.getAutomate().getModuleMemoire().hasStockMagasin()){
            etat = new EtatAllerDormir(getAutomate());
        }
        return etat;
    }

    @Override
    public Action action() {
        return null;
    }
    
}
