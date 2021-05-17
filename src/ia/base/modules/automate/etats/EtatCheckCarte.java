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
 * Test si l'ia possède la carte ou non
 * Etat de réflexion
 * @author Mathis Poncet
 */
public class EtatCheckCarte extends Etat{

    public EtatCheckCarte(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        Etat etat = new EtatCheckAction(super.getAutomate());
        if(!super.getAutomate().getModuleMemoire().hasCarte()){
            etat = new EtatDemanderCarte(super.getAutomate());
        }
        return etat;
    }

    @Override
    public Action action() {
        return null;
    }
    
}
