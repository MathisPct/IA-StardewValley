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
 * Point de départ de l'automate
 * @author Mathis Poncet
 */
public class EtatInitial extends Etat{

    public EtatInitial(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        return new EtatCheckCarte(super.getAutomate());
    }

    @Override
    public Action action() {
        return null;
    }
    
}
