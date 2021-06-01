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
 * Permet de demander tous les oeufs présents sur la carte avant 
 * d'aller les chercher
 * @author Mathis Poncet
 */
public class EtatDemandeOeufs extends Etat{

    public EtatDemandeOeufs(Automate automate) {
        super(automate);
    }

    @Override
    public Etat transition() {
        return new EtatCheckAction(super.getAutomate());
    }
    
    /**
     * Demande la position des oeufs sur la cartes
     * @return l'action permettant de demander la position des oeufs
     */
    @Override
    public Action action() {
        return FabriqueAction.creerDemande(TypeDemande.OEUFS);
    }
    
}
