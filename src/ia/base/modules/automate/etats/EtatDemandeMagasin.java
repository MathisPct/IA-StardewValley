/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.TypeMouvement;
import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.actions.TypeActionRecolte;
import ia.base.metier.actions.TypeDemande;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Demande le stock du magasin
 * @author Mathis Poncet
 */
public class EtatDemandeMagasin extends Etat{

    public EtatDemandeMagasin(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        return new EtatCheckAction(this.getAutomate());
    }

    @Override
    public Action action() {
        return FabriqueAction.creerDemande(TypeDemande.MAGASIN);
    }
    
}
