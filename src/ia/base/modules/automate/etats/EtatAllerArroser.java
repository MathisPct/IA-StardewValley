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
 * Va arroser ses plantes
 * @author Mathis Poncet
 */
public class EtatAllerArroser extends Etat{

    public EtatAllerArroser(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Action action() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
