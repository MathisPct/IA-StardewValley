/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.actions.TypeDemande;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Etat qui permet de demander où se trouve les poules sur la carte
 * @author Mathis Poncet
 */
public class EtatDemandePoules extends Etat{

    public EtatDemandePoules(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        return new EtatRealiserAction(getAutomate());
    }
    
    /**
     * Demande la position des poules pour éviter de rentrer en colision avec 
     * elle
     * @return l'action permettant de demander la position des poules
     */
    @Override
    public Action action() {
        return FabriqueAction.creerDemande(TypeDemande.CHICKEN);
    }
}
